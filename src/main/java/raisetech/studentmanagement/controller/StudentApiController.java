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

/**
 * 受講生の検索、登録、更新などを行うためのREST APIコントローラーです。
 * 全てのリクエストに対して、成功/失敗のメッセージを含むApiResponseを返却します。
 */
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

    /**
     * 受講生の一覧を全件取得します。
     * 受講生の情報と、それぞれが受講しているコース情報を紐付けて返却します。
     *
     * @return 受講生詳細情報のリスト含むApiResponse
     */
    @GetMapping("/students")
    public ResponseEntity<ApiResponse> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchStudentCoursesList();
        List<StudentDetail> details = converter.convertStudentDetails(students, studentsCourses);

        ApiResponse response = new ApiResponse(true, "全件取得に成功しました", details);
        return ResponseEntity.ok(response);
    }

    /**
     * 指定されたIDに合致する受講生を1件取得します。
     * *@param id 受講生ID
     *
     * @return 取得した受講生詳細情報を含むApiResponse
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<ApiResponse> getStudent(@PathVariable int id) {
        StudentDetail detail = service.searchStudent(id);

        ApiResponse response = new ApiResponse(true, "受講生の取得に成功しました。", detail);
        return ResponseEntity.ok(response);
    }

    /**
     * 受講生の新規登録を行います。
     * 受講生情報とコース情報を同時に登録し、完了後に登録されたデータを返却します。
     * * @param studentDetail 登録する受講生詳細
     *
     * @return 登録完了後の受講生詳細情報を含むApiResponse
     */
    @PostMapping("/students")
    public ResponseEntity<ApiResponse> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
        StudentDetail registerDetail = service.registerStudent(studentDetail);
        ApiResponse response = new ApiResponse(true, "受講生の登録に成功しました。", registerDetail);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * 既存の受講生情報を更新します。
     * パスで指定されたIDを元に対象を特定し、送られたデータで上書きします。
     * * @param id　更新対象の受講生ID
     *
     * @param studentDetail 更新する受講生詳細
     * @return 更新成功のメッセージを含むApiResponse
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable int id,
                                                     @RequestBody @Valid StudentDetail studentDetail) {
        studentDetail.getStudent().setId(id);
        service.updateStudent(studentDetail);

        ApiResponse response = new ApiResponse(true, "更新処理が成功しました。", null);
        return ResponseEntity.ok(response);
    }
}