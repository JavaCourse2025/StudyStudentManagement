package raisetech.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.repository.StudentCoursesRepository;
import raisetech.studentmanagement.repository.StudentRepository;

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
        //ここで何かしらの処理を行う
        //kadai 絞り込み検索30代のみ抽出。リストをコントローラーに返す。
        return studentRepository.search();
    }

    public List<StudentsCourses> searchStudentCourses() {
        //ここで何かしらの処理を行う
        //kadai2絞り込み検索『Javaコース』のコース情報のみ抽出しリストをコントローラーに返す。
        return studentCoursesRepository.search();
    }
}
