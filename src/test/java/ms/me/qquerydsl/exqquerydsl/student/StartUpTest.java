package ms.me.qquerydsl.exqquerydsl.student;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Commit
class StartUpTest {

    @PersistenceContext
    EntityManager entityManager;

    JPAQueryFactory queryFactory;
    QStudent student = QStudent.student;

    @BeforeEach
    void init(){
        queryFactory = new JPAQueryFactory(entityManager);
    }



    @Test
    @DisplayName("dto조회")
    void test1(){
        final List<StudentDto> fetch = queryFactory.select(Projections.bean(StudentDto.class, student.name, student.id))
                .from(student)
                .fetch();

        System.out.println("fetch = " + fetch);


    }

    @Test
    @DisplayName("dto조회")
    void test2(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        final List<StudentDto> fetch = queryFactory.select(Projections.fields(StudentDto.class, student.name, student.id))
                .from(student)
                .fetch();

        System.out.println("fetch = " + fetch);
    }

    @Test
    @DisplayName("dto조회")
    void test3(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        final List<StudentDto> fetch = queryFactory.select(
                    Projections.fields(StudentDto.class,
                            student.name.as("name"),
                            student.id.castToNum(Integer.class).as("age"))
                )
                .from(student)
                .fetch();

        System.out.println("fetch = " + fetch);
    }

    @Test
    @DisplayName("dto조회")
    void test4(){

        QStudent subStudent = new QStudent("subStudent");

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        final List<StudentDto> fetch = queryFactory.select(
                Projections.fields(StudentDto.class, student.name.as("name"),
                        ExpressionUtils.as(JPAExpressions
                            .select(subStudent.id.max().castToNum(Integer.class))
                                .from(subStudent), "age"))
                )
                .from(student)
                .fetch();

        System.out.println("fetch = " + fetch);
    }

    @Test
    @DisplayName("다이나믹 쿼리")
    void test5() {

        BooleanBuilder builder = new BooleanBuilder();
        String name = "다이나믹쿼리 파라미터";

        if(name != null) {
            builder.and(student.name.eq(name));
        }

        queryFactory.selectFrom(student)
                .where(builder)
                .fetchOne();
    }

    @Test
    @DisplayName("다이나믹 쿼리-두번")
    void test6() {
        String name = "다이나믹쿼리 파라미터";
        int age = 0;

        queryFactory.selectFrom(student)
                .where(nameEq(name), ageEq(age)) //where절에 있는 null조건은 모두 무시된다.
                .fetchOne();
    }

    private Predicate nameEq(String name){
        return name == null ? null : student.name.eq(name);
    }

    private Predicate ageEq(int age){
        return age == 0 ? null : student.age.eq(age);
    }


}