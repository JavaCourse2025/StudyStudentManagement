package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.*;
import raisetech.studentmanagement.data.Student;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> search();

    @Insert("INSERT INTO students (full_name, furigana, nickname, email, region, age, gender, remark, is_deleted) " +
            "VALUES (#{fullName}, #{furigana}, #{nickName}, #{email}, #{region}, #{age}, #{gender}, #{remark}, false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertStudent(Student student);

    // StudentRepository の中に書くイメージ
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(int id);

    @Update("UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nickname = #{nickName}, " +
            "email = #{email}, region = #{region}, age = #{age}, gender = #{gender}, remark = #{remark} " +
            "WHERE id = #{id}")
    void updateStudent(Student student);
}




