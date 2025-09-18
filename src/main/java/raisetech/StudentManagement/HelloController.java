package raisetech.StudentManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/thank")
    public String hello(){
        return "Thank,you!";
    }
}