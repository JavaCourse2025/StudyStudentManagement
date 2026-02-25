package raisetech.studentmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raisetech.studentmanagement.controller.converter.StudentConverter;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.domain.ApiResponse;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.service.StudentService;

import java.util.List;

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
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable int id,
                                                     @RequestBody @Valid StudentDetail studentDetail) {
        studentDetail.getStudent().setId(id);
        service.updateStudent(studentDetail);

        ApiResponse response = new ApiResponse(true, "更新処理が成功しました。", null);
        return ResponseEntity.ok(response);
    }
}