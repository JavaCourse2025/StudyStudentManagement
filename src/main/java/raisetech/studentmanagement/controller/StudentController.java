package raisetech.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import raisetech.studentmanagement.controller.converter.StudentConverter;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.service.StudentService;

import java.util.List;

@RestController
public class StudentController {
    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchStudentCoursesList();

        return converter.convertStudentDetails(students, studentsCourses);

    }

    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(new Student());
        studentDetail.setStudentsCourses(new java.util.ArrayList<>());
        studentDetail.getStudentsCourses().add(new StudentsCourses());
        model.addAttribute("studentDetail", studentDetail);
        return "registerStudent";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
        if (result.hasErrors()) {
            return "registerStudent";
        }
        service.registerStudent(studentDetail);

        System.out.println(studentDetail.getStudent().getFullName() + "さんが新規受講生として登録されました。");
        return "redirect:/studentList";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(Student student, Model model) {

        StudentDetail studentDetail = service.searchStudent(student.getId());
        studentDetail.getStudentsCourses().add(new StudentsCourses());
        model.addAttribute("studentDetail", studentDetail);
        return "updateStudent";
    }

    @PostMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }
}