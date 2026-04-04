package raisetech.studentmanagement.repository;

import org.apache.ibatis.annotations.*;
import raisetech.studentmanagement.data.Student;

import java.util.List;

@Mapper
public interface StudentRepository {
    /**
     * 受講生の全件検索を行います。
     *
     * @return 受講生一覧（全件）
     */
    @Select("SELECT * FROM students")
    List<Student> search();

    /**
     * 受講生の新規登録を行います。
     * IDは自動採番されます。
     *
     * @param student 受講生情報
     */
    @Insert("INSERT INTO students (full_name, furigana, nickname, email, region, age, gender, remark, is_deleted) " +
            "VALUES (#{fullName}, #{furigana}, #{nickName}, #{email}, #{region}, #{age}, #{gender}, #{remark}, false)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertStudent(Student student);

    /**
     * 指定されたIDに一致する受講生を1件検索します。
     *
     * @param id 受講生ID
     * @return 受講生情報
     */
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student searchStudent(int id);

    /**
     * 受講生の基本情報を更新します。
     *
     * @param student 更新する受講生情報
     */
    @Update("UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nickname = #{nickName}, " +
            "email = #{email}, region = #{region}, age = #{age}, gender = #{gender}, remark = #{remark} " +
            "WHERE id = #{id}")
    void updateStudent(Student student);
}




