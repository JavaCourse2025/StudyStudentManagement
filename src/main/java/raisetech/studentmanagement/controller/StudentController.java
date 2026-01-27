package raisetech.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        //リクエストの加工処理、入力チェックとか
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchStudentCoursesList();

        return convertStudentDetails(students, studentsCourses);


    }

    private List<StudentDetail> convertStudentDetails(List<Student> students, List<StudentsCourses> studentsCourses) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        students.forEach(student -> {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);

            List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
                    .filter(studentsCourse -> student.getId().equals(String.valueOf(studentsCourse.getStudentId())))
                    .collect(Collectors.toList());
            studentDetail.setStudentsCourses(convertStudentCourses);
            studentDetails.add(studentDetail);
        });
        return studentDetails;
    }

}

