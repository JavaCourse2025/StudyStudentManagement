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

    public List<Student> searchStudentList() {
        //何かしらの処理
        return studentRepository.search();
    }

    public List<StudentsCourses> searchStudentCoursesList() {
        //何かしらの処理
        return studentCoursesRepository.search();
    }

    @Transactional
    public void registerStudent(StudentDetail studentDetail) {
        //生徒登録
        studentRepository.insertStudent(studentDetail.getStudent());
        //コース情報を取り出して処理
        studentDetail.getStudentsCourses().forEach(courses -> {
            courses.setStudentId(studentDetail.getStudent().getId());
            courses.setStartDate(LocalDate.now());
            courses.setScheduledEndDate(LocalDate.now().plusYears(1));
            studentCoursesRepository.insertCourse(courses);

        });
    }

    public StudentDetail searchStudent(int id) {
        Student student = studentRepository.searchStudent(id);
        List<StudentsCourses> studentsCourses = studentCoursesRepository.searchStudentCourse(id);
        return new StudentDetail(student, studentsCourses);
    }

    @Transactional
    public void updateStudent(StudentDetail studentDetail) {
        studentRepository.updateStudent(studentDetail.getStudent());
        for (StudentsCourses course : studentDetail.getStudentsCourses()) {
            if (course.getId() == 0) {
                course.setStudentId(studentDetail.getStudent().getId());
                course.setStartDate(LocalDate.now());
                course.setScheduledEndDate(LocalDate.now().plusYears(1));
                studentCoursesRepository.insertCourse(course);
            } else {
                studentCoursesRepository.updateStudentCourse(course);
            }

        }
    }
}

