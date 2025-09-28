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

    // TODO: ステートレス化のため現在は空実装。将来的に外部ストレージとの連携ロジックをここに実装する。
    public void updateName(String newName) {
    }

    // TODO: ステートレス化のため現在は空実装。
    public void updateAge(String newAge) { // ★ 追加
    }

    // TODO: ステートレス化のため現在は空実装。
    public void updateScore(String subject, String score) {
    }

    // TODO: ステートレス化のため現在は空実装。
    public void resetData() {


    }
}

