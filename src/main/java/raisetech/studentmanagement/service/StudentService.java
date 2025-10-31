package raisetech.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.repository.StudentCoursesRepository;
import raisetech.studentmanagement.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        //DBから全学生データ取得
        List<Student> allStudents = studentRepository.search();
        //kadai 絞り込み検索30代のみ抽出。リストをコントローラーに返す。

        return allStudents.stream()
                .filter(student -> student.getAge() >= 30 && student.getAge() <= 39)
                .collect(Collectors.toList());
    }

    public List<StudentsCourses> searchStudentCourses() {
        //DBから全コースデータ取得
        List<StudentsCourses> allCourses = studentCoursesRepository.search();
        //kadai2絞り込み検索『Javaコース』のコース情報のみ抽出しリストをコントローラーに返す。
        return allCourses.stream()
                .filter(studentsCourses -> "Javaコース".equals(studentsCourses.getCourseName()))
                .collect(Collectors.toList());
    }
}
