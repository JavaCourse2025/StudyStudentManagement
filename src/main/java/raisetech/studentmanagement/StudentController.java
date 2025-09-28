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
        return "Mayuka Sasaki";
    }

    @GetMapping("/studentInfo")
    public String getStudentInfo() {
        return "Mayuka Sasaki 31歳";
    }

    @GetMapping("/scores")
    public Map<String, String> getScore() {
        return studentService.getScores();
    }

    @GetMapping("/studentInfoWithScore")
    public String getInfoWithScore() {
        return "Mayuka Sasaki 31歳 | 成績: {math=85, english=92}";

    }


    @PostMapping("/studentName")
    public String updateStudentName(@RequestParam String newName) {
        return "名前が「" + newName + "」に更新されました。";
    }

    @PostMapping("/studentAge")
    public String updateStudentAge(@RequestParam String newAge) {
        return "年齢が「" + newAge + "」に更新されました";
    }

    @PostMapping("/reset")
    public String resetData() {
        return "データを初期化（Mayuka Sasaki , 31歳、成績もリセットしました。）";

    }

    @PostMapping("/scores/update")
    public String updateScore(
            @RequestParam String subject,
            @RequestParam String score
    ) {
        return "成績が更新されました：" + subject + "=" + score;
    }
}