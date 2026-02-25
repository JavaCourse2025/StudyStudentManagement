package raisetech.studentmanagement.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

    private int id;
    @NotBlank(message = "名前を入力してください")
    private String fullName;
    @NotBlank(message = "フリガナを入力してください")
    private String furigana;
    private String nickName;
    private String email;
    private String region;
    private String gender;
    private int age;
    private String remark;
    private boolean deleted;
}


