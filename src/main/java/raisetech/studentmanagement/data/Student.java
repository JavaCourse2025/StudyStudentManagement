package raisetech.studentmanagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

    private int id;
    private String fullName;
    private String furigana;
    private String nickName;
    private String email;
    private String region;
    private String gender;
    private int age;
    private String remark;
    private boolean deleted;
}


