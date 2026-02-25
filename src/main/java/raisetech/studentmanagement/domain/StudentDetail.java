package raisetech.studentmanagement.domain;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor//からっぽの箱も作れるように
@AllArgsConstructor//中身を全部詰めた箱も作れるように

public class StudentDetail {

    @Valid
    private Student student;
    private List<StudentsCourses> studentsCourses;

}
