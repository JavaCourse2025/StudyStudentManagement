package raisetech.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.repository.StudentCoursesRepository;
import raisetech.studentmanagement.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * 受講生の情報操作を行うクラスです。
 * 受講生の登録、更新、検索などの処理を管理します。
 */
@Service
public class StudentService {

    private StudentRepository studentRepository;
    private StudentCoursesRepository studentCoursesRepository;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            StudentCoursesRepository studentCoursesRepository) {
        this.studentRepository = studentRepository;
        this.studentCoursesRepository = studentCoursesRepository;
    }

    /**
     * 受講生一覧検索です。
     * 全件検索を行うので、条件指定は行いません。
     *
     * @return 受講生一覧（全件）
     */
    public List<Student> searchStudentList() {
        return studentRepository.search();
    }

    /**
     * 受講生コース情報の一覧検索です。
     *
     * @return 受講生コース一覧
     */
    public List<StudentsCourses> searchStudentCoursesList() {
        return studentCoursesRepository.search();
    }

    /**
     * 受講生検索です。
     * IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
     *
     * @param id 受講生ID
     * @return 受講生詳細情報
     */
    public StudentDetail searchStudent(int id) {
        Student student = studentRepository.searchStudent(id);
        List<StudentsCourses> studentsCourses = studentCoursesRepository.searchStudentCourse(id);
        return new StudentDetail(student, studentsCourses);
    }

    /**
     * 受講生情報とコース情報を一括で登録します。
     * 登録時にコースの開始日と終了予定日（1年後）を自動設定します。
     *
     * @param studentDetail 受講生詳細
     * @return 登録完了後の受講生詳細
     */
    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        studentRepository.insertStudent(studentDetail.getStudent());
        studentDetail.getStudentsCourses().forEach(course -> {
            initCourseData(studentDetail, course);
            studentCoursesRepository.insertCourse(course);
        });
        return studentDetail;
    }

    /**
     * 受講生情報とコース情報を更新します。
     * コース情報が存在しない（IDが0）場合は新規登録を行い、存在する場合は更新します。
     *
     * @param studentDetail 受講生詳細
     */
    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        studentRepository.updateStudent(studentDetail.getStudent());
        for (StudentsCourses course : studentDetail.getStudentsCourses()) {
            if (course.getCourseName() == null || course.getCourseName().isBlank()) {
                continue;
            }
            if (course.getId() == 0) {
                // 💡 ここでも共通メソッドを呼び出す
                initCourseData(studentDetail, course);
                studentCoursesRepository.insertCourse(course);
            } else {
                studentCoursesRepository.updateStudentCourse(course);
            }
        }
    }

    /**
     * 受講生コース情報の初期設定を行います。
     * 受講生IDの紐付けと、開始日・終了予定日の設定を共通化しています。
     *
     * @param studentDetail 受講生詳細
     * @param course        設定対象のコース情報
     */
    private void initCourseData(StudentDetail studentDetail, StudentsCourses course) {
        course.setStudentId(studentDetail.getStudent().getId());
        course.setStartDate(LocalDate.now());
        course.setScheduledEndDate(LocalDate.now().plusYears(1));
    }
}