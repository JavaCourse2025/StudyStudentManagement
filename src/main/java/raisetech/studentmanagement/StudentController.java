package raisetech.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/name")
    public String hello() {
        return studentService.getName();
    }

    @GetMapping("/studentInfo")
    public String getStudentInfo() {
        return studentService.getName() + " " + studentService.getAge() + "歳";
    }

    @GetMapping("/scores")
    public Map<String, String> getScore() {
        return studentService.getScores();
    }

    @GetMapping("/studentInfoWithScore")
    public String getInfoWithScore() {
        String info = studentService.getName() + " " + studentService.getAge() + "歳";
        info += " | 成績: " + studentService.getScores().toString();
        return info;
    }


    @PostMapping("/studentName")
    public String updateStudentName(@RequestParam String newName) {
        studentService.updateName(newName);
        return "名前が「" + newName + "」に更新されました。";
    }

    @PostMapping("/studentAge")
    public String updateStudentAge(@RequestParam String newAge) {
        studentService.updateAge(newAge);
        return "年齢が「" + newAge + "」に更新されました";
    }

    @PostMapping("/reset")
    public String resetData() {
        studentService.resetData();
        return "データを初期化（" + studentService.getName() + " , " + studentService.getAge() + "歳、成績もリセットしました。）";
    }

    @PostMapping("/scores/update")
    public String updateScore(
            @RequestParam String subject,
            @RequestParam String score
    ) {
        studentService.updateScore(subject, score);
        return "成績が更新されました：" + subject + "=" + score;
    }
}