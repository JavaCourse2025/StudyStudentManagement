package raisetech.studentmanagement;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentsCourses {
    private int id;
    private int studentId;
    private String courseName;
    private LocalDate startDate;
    private LocalDate scheduledEndDate;
}
