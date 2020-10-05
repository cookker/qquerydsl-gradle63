package ms.me.qquerydsl.exqquerydsl.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class StartUp  {
    private final EntityManager entityManager;




}
