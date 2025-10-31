package raisetech.studentmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.service.StudentService;

import java.util.List;

@RestController
public class StudentCoursesController {

    private StudentService service;

    public StudentCoursesController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/studentsCoursesList")
    public List<StudentsCourses> getStudentCourses() {
        return service.searchStudentCourses();

    }
}
