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


    public String getStudentInfo() {
        return getName() + " " + getAge() + "歳";
    }

    // GET /studentInfoWithScore の文字列生成を委譲
    public String getStudentInfoWithScore() {
        // TODO: 現在 no-op。将来永続化/状態管理を実装予定。
        return getStudentInfo() + " | 成績: " + getScores();
    }

    // POST /studentName のメッセージ生成を委譲 (Stringを返すことで型を解決)
    public String updateStudentName(String newName) {
        // TODO: 現在 no-op。将来永続化/状態管理を実装予定。
        return "名前が「" + newName + "」に更新されました。";
    }

    // POST /studentAge のメッセージ生成を委譲 (Stringを返すことで型を解決)
    public String updateStudentAge(String newAge) {
        // TODO: 現在 no-op。将来永続化/状態管理を実装予定。
        return "年齢が「" + newAge + "」に更新されました";
    }

    // POST /reset のメッセージ生成を委譲 (Stringを返すことで型を解決)
    public String resetData() {
        // TODO: 現在 no-op。将来永続化/状態管理を実装予定。
        return "データを初期化しました。"; // Controllerが期待するメッセージ
    }

    // POST /scores/update のメッセージ生成を委譲 (Stringを返すことで型を解決)
    public String updateScore(String subject, String score) {
        // TODO: 現在 no-op。将来永続化/状態管理を実装予定。
        return "成績が更新されました：" + subject + "=" + score;
    }
}


