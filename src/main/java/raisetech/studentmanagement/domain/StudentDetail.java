package raisetech.studentmanagement.domain;

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

    private Student student;
    private List<StudentsCourses> studentsCourses;

}
