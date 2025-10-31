package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.studentmanagement.data.Student;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("SELECT id, full_name, furigana, nickname, email, region, gender, age FROM students WHERE age BETWEEN 30 AND 39")
    List<Student> search();


}
