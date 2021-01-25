package sqlib.criteria;

import common.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sqlib.criteria.Predicate.createPredicate;

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
        Predicate p = createPredicate();
        String condition = p.equal(result.get("name"), "Hadi").getConditionString();
        assertEquals("SELECT * FROM Student WHERE name = 'Hadi'", query
                .selectAny(result).where(condition).getQueryString());
    }

    @Test
    void selectingWithTwoPredicates() {
        Predicate predicate1 = createPredicate().equal(result.get("age"), 20);
        Predicate predicate2 = createPredicate().greaterThan(result.get("average"), 13.4);
        String condition = builder.and(predicate1, predicate2).getCompoundPredicateQueryString();
        assertEquals("SELECT * FROM Student WHERE age = 20 AND average > 13.4",
                query.selectAny(result).where(condition).getQueryString());
    }

    @Test
    void twoChainActionWithSamePredicateInstance() {
        Predicate predicate = createPredicate().equal(result.get("id"), 20);
        assertEquals("id = 20", predicate.getConditionString());
        //add another chain action should override the first one.
        predicate.greaterThan(result.get("id"), 10);
        assertEquals("id > 10", predicate.getConditionString());
    }

    @Test
    void sendNullForPredicateShouldThrowError() {
        NullPointerException ex =
                assertThrows(NullPointerException.class, () -> createPredicate().equal(result.get("id"), null));
        assertEquals(Constants.PARAM_MISSING, ex.getMessage());
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