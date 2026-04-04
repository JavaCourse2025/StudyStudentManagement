package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.*;
import raisetech.studentmanagement.data.StudentsCourses;

import java.util.List;

@Mapper
public interface StudentCoursesRepository {
    /**
     * 受講生のコース情報の全件検索を行います。
     *
     * @return 全ての受講生コース情報一覧
     */
    @Select(" SELECT * FROM students_courses WHERE is_deleted = false")
    List<StudentsCourses> search();

    /**
     * コース情報の全件検索を行います。
     *
     * @param courses 登録するコース情報
     */
    @Insert("INSERT INTO students_courses (student_id, course_name, start_date, scheduled_end_date, is_deleted)" +
            "VALUES (#{studentId}, #{courseName}, #{startDate}, #{scheduledEndDate},false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertCourse(StudentsCourses courses);

    /**
     * 受講生IDに紐づくコース情報を検索します。
     *
     * @param id 受講生ID
     * @return 受講生に紐づくコース情報の一覧
     */
    @Select("SELECT * FROM students_courses WHERE student_id = #{id}")
    List<StudentsCourses> searchStudentCourse(int id);

    /**
     * コース情報の更新を行います。
     *
     * @param course 更新するコース情報
     */

    @Update("UPDATE students_courses SET course_name = #{courseName}, is_deleted = #{deleted} WHERE id = #{id}")
    void updateStudentCourse(StudentsCourses course);
}
