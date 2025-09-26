package raisetech.studentmanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class StudentController {
    private String name = "Mayuka Sasaki";
    private String age = "31";
    private final Map<String, String> studentScores;

    public StudentController() {
        this.studentScores = new ConcurrentHashMap<>();
        this.studentScores.put("math", "85");
        this.studentScores.put("english", "92");
    }

    @GetMapping("/name")
    public String hello() {
        return name;
    }

    @GetMapping("/studentInfo")
    public String getStudentInfo() {
        return name + " " + age + "歳";

    }

    //成績全体の取得
    @GetMapping("/scores")
    public Map<String, String> getScore() {
        return studentScores;
    }

    // 基本情報に成績も合わせて表示
    @GetMapping("studentInfoWithScore")
    public String getInfoWithScore() {
        String info = name + " " + age + "歳";
        info += " | 成績: " + studentScores.toString();
        return info;
    }

    @PostMapping("/studentName")
    public String updateStudentName(@RequestParam String newName) {
        this.name = newName;
        return "名前が「" + newName + "」に更新されました。";


    }

    @PostMapping("/studentAge")
    public String updateStudentAge(@RequestParam String newAge) {
        this.age = newAge;
        return "年齢が「" + newAge + "」に更新されました";


    }

    @PostMapping("/reset")
    public String resetData() {
        this.name = "Mayuka Sasaki";
        this.age = "31";
        this.studentScores.clear();
        this.studentScores.put("math", "85");
        this.studentScores.put("english", "92");
        return "データを初期化（" + "Mayuka Sasaki" + " , " + 31 + "歳、成績）もリセットしました。";


    }

    // 個別成績の更新
    @PostMapping("/scores/update")
    public String updateScore(
            @RequestParam String subject,
            @RequestParam String score
    ) {
        this.studentScores.put(subject, score);
        return "成績が更新されました：" + subject + "=" + score;

    }
}