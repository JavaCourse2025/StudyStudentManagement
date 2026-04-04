package raisetech.studentmanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.studentmanagement.controller.converter.StudentConverter;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.service.StudentService;

@Controller
public class StudentController {
    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
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

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute @Valid StudentDetail studentDetail, BindingResult result, Model model) {
        // もし入力ミス（バリデーションエラー）があったら、更新せずに編集画面に戻る
        if (result.hasErrors()) {
            return "updateStudent";
        }
        service.updateStudent(studentDetail);
        return "redirect:/studentList";
    }
}