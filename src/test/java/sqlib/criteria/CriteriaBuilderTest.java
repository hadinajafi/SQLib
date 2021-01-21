package sqlib.criteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CriteriaBuilderTest {

    private CriteriaBuilder builder;
    private Result<Student> result;
    private CriteriaQuery query;

    @BeforeEach
    void init() {
        builder = CriteriaBuilder.createConnection();
        result = new Result<>(Student.class);
        query = new CriteriaQuery();
    }

    @Test
    void test() {
        Predicate predicate1 = new Predicate();
        predicate1.equal("id", 1000);
        assertEquals("id = 1000 ", predicate1.getConditionString());

        Predicate predicate2 = new Predicate();
        predicate2.greaterThan("id", 3000);
        assertEquals("id > 3000 ", predicate2.getConditionString());

        System.out.println(query.selectAny(result).where(builder.and(predicate1, predicate2)
                .getCompoundPredicateQueryString()).getQueryString());

    }

    @Test
    void withoutPredicateShouldWork() {
        String q = query.selectAny(result).getQueryString();
        assertEquals("SELECT * FROM Student", q.trim());
    }

    @Test
    void reInitResultWithChildClassShouldWork() {
        var result = new Result<>(Child.class);
        assertEquals("SELECT * FROM Child ", query.selectAny(result).getQueryString());
    }

    @Test
    void selectingWithProperColumns() {
        assertEquals("SELECT id, name, age, average FROM Student", query
                .select(result).getQueryString().trim());
    }

    @Test
    void selectingProperColumnsOfChildClass() {
        var fromChild = new Result<>(Child.class);
        assertEquals("SELECT code FROM Child", query
                .select(fromChild).getQueryString().trim());
    }

    @Test
    void selectingWithOnePredication() {
        Predicate p = new Predicate();
        String condition = p.equal(result.get("name"), "Hadi").getConditionString();
        assertEquals("SELECT * FROM Student WHERE name = 'Hadi'", query
                .selectAny(result).where(condition).getQueryString().trim());
    }

    @Test
    void selectingWithTwoPredicates() {
        Predicate predicate1 = new Predicate().equal(result.get("age"), 20);
        Predicate predicate2 = new Predicate().greaterThan(result.get("average"), 13.4);
        String condition = builder.and(predicate1, predicate2).getCompoundPredicateQueryString();
        assertEquals("SELECT * FROM Student WHERE age = 20 AND average > 13.4",
                query.selectAny(result).where(condition).getQueryString());
    }

    static class Student {
        private int id;
        private String name;
        private Integer age;
        private double average;
    }

    static class Child extends Student {
        private String code;
    }

}