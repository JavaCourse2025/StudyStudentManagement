package raisetech.studentmanagement;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService {


    public String getName() {
        return "Mayuka Sasaki";
    }

    public String getAge() {
        return "31";
    }

    public Map<String, String> getScores() {
        return Map.of("math", "85", "english", "92");

    }

    public void updateName(String newName) {
    }

    public void updateAge(String newAge) { // ★ 追加
    }

    public void updateScore(String subject, String score) {
    }

    public void resetData() {


    }
}

