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
        return studentService.getStudentInfoWithScore();

    }


    @PostMapping("/studentName")
    public String updateStudentName(@RequestParam String newName) {
        return studentService.updateStudentName(newName);
    }

    @PostMapping("/studentAge")
    public String updateStudentAge(@RequestParam String newAge) {
        return studentService.updateStudentAge(newAge);
    }

    @PostMapping("/reset")
    public String resetData() {
        return studentService.resetData();

    }

    @PostMapping("/scores/update")
    public String updateScore(
            @RequestParam String subject,
            @RequestParam String score
    ) {
        return studentService.updateScore(subject, score);
    }
}