package raisetech.studentmanagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

    private String id;
    private String fullName;
    private String furigana;
    private String nickName;
    private String email;
    private String region;
    private String gender;
    private int age;
    //kadai　DB上のstudentsテーブルに追加＋値を引っ張ってこれるようにオブジェクトも変更
    //private String remark;
    //private boolean isDeleted;
    //DB項目追加はALTER TABLEを使う
}

