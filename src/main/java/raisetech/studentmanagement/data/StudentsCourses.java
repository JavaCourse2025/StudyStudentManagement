package raisetech.studentmanagement.data;

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
    private boolean isDeleted;

    // StudentsCourses.java の中に貼り付け
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
