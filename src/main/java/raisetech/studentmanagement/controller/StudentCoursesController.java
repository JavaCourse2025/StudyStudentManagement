package raisetech.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.repository.StudentCoursesRepository;

import java.util.List;

@RestController
public class StudentCoursesController {
    @Autowired
    private StudentCoursesRepository repository;

    @GetMapping("/studentsCoursesList")
    public List<StudentsCourses> getStudentCourses() {
        return repository.search();

    }
}
