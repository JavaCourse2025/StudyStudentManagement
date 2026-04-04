package raisetech.studentmanagement.controller.converter;

import org.springframework.stereotype.Component;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.domain.StudentDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 受講生詳細を受講生や受講生コース情報、もしくはその逆を変換するコンバーターです。
 */
@Component
public class StudentConverter {
    /**
     * 受講生に紐づく受講生コース情報をマッピングします。
     * 受講生コース情報は受講生に対して複数存在するので、ループを回して受講生詳細情報を組み立てます。
     *
     * @param students        受講生一覧
     * @param studentsCourses 受講生コース情報の全一覧
     * @return 受講生詳細情報のリスト
     */
    public List<StudentDetail> convertStudentDetails(List<Student> students, List<StudentsCourses> studentsCourses) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        students.forEach(student -> {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);

            List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
                    .filter(studentsCourse -> student.getId() == studentsCourse.getStudentId())
                    .collect(Collectors.toList());
            studentDetail.setStudentsCourses(convertStudentCourses);
            studentDetails.add(studentDetail);
        });
        return studentDetails;
    }

}
