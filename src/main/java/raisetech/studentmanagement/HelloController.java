package raisetech.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private StudentRepository repository;


    @GetMapping("/thank")
    public String hello() {
        return "Thank,you!";
    }

    @GetMapping("/studentList")
    public List<Student> getStudentList() {
        return repository.search();


    }

}

