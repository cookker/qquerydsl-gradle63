package ms.me.qquerydsl.exqquerydsl;

import ms.me.qquerydsl.exqquerydsl.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class ExQquerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExQquerydslApplication.class, args);
    }
}
