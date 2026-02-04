package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentmanagement.data.StudentsCourses;

import java.util.List;

@Mapper
public interface StudentCoursesRepository {
    @Select(" SELECT * FROM students_courses WHERE is_deleted = false")
    List<StudentsCourses> search();

    @Insert("INSERT INTO students_courses (student_id, course_name, start_date, scheduled_end_date)" +
            "VALUES (#{studentId}, #{courseName}, #{startDate}, #{scheduledEndDate})")
    void insertCourse(StudentsCourses courses);

    @Select("SELECT * FROM students_courses WHERE student_id = #{id} AND is_deleted = false")
    List<StudentsCourses> searchStudentCourse(int id);

    @Update("UPDATE students_courses SET course_name = #{courseName} " +
            "WHERE id = #{id}")
    void updateCourse(StudentsCourses course);

    @Update("UPDATE students_courses SET course_name = #{courseName}, is_deleted = #{deleted} WHERE id = #{id}")
    void updateStudentCourse(StudentsCourses course);
}
