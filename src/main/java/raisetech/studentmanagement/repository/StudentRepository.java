package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
}




