package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.studentmanagement.data.StudentsCourses;

import java.util.List;

@Mapper
public interface StudentCoursesRepository {
    @Select(" SELECT * FROM students_courses")
    List<StudentsCourses> search();
}
