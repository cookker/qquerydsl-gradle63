package ms.me.qquerydsl.exqquerydsl.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping
    public String getStudent1() {
        return "name";
    }


    @GetMapping("/student2")
    public ResponseEntity<String> getStudent2() {
        return ResponseEntity.ok("name");
    }
}
