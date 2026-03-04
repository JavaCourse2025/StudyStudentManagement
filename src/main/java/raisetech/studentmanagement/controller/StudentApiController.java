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
    public ResponseEntity<ApiResponse> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchStudentCoursesList();
        List<StudentDetail> details = converter.convertStudentDetails(students, studentsCourses);

        ApiResponse response = new ApiResponse(true, "全件取得に成功しました", details);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<ApiResponse> getStudent(@PathVariable int id) {
        StudentDetail detail = service.searchStudent(id);

        ApiResponse response = new ApiResponse(true, "受講生の取得に成功しました。", detail);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/students")
    public ResponseEntity<ApiResponse> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
        StudentDetail registerDetail = service.registerStudent(studentDetail);
        ApiResponse response = new ApiResponse(true, "受講生の登録に成功しました。", registerDetail);
        return ResponseEntity.status(201).body(response);
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