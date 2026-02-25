package raisetech.studentmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raisetech.studentmanagement.controller.converter.StudentConverter;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentApiController {
    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentApiController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/students")
    public List<StudentDetail> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchStudentCoursesList();

        return converter.convertStudentDetails(students, studentsCourses);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable int id,
                                                             @RequestBody @Valid StudentDetail studentDetail) {
        studentDetail.getStudent().setId(id);
        service.updateStudent(studentDetail);

        Map<String, String> response = new java.util.HashMap<>();
        response.put("message", "更新処理が成功しました。");
        return ResponseEntity.ok(response);
    }
}