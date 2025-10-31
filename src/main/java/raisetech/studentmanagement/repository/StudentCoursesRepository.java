package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.studentmanagement.data.StudentsCourses;

import java.util.List;

@Mapper
public interface StudentCoursesRepository {
    @Select(" SELECT id, student_id, course_name, start_date, scheduled_end_date FROM students_courses WHERE TRIM(course_name) = 'Javaコース'")
    List<StudentsCourses> search();
}
