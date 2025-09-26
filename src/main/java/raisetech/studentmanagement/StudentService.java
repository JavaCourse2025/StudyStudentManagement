package raisetech.studentmanagement;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StudentService {

    private String name = "Mayuka Sasaki";
    private String age = "31";
    private final Map<String, String> studentScores;

    public StudentService() {
        this.studentScores = new ConcurrentHashMap<>();
        this.studentScores.put("math", "85");
        this.studentScores.put("english", "92");
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Map<String, String> getScores() {
        return Collections.unmodifiableMap(this.studentScores); // ★ 修正
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateAge(String newAge) { // ★ 追加
        this.age = newAge;
    }

    public void updateScore(String subject, String score) {
        this.studentScores.put(subject, score);
    }

    public void resetData() {
        this.name = "Mayuka Sasaki";
        this.age = "31";
        this.studentScores.clear();
        this.studentScores.put("math", "85");
        this.studentScores.put("english", "92");
    }


}

