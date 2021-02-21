package sqlib.criteria;

import common.exception.SQLibException;
import common.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqlib.query.Result;
import sqlib.query.internal.Column;
import sqlib.query.Predicate;
import sqlib.query.internal.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sqlib.query.Predicate.createPredicate;
import static sqlib.query.functions.SqlFunctions.COUNT;

class CriteriaBuilderTest {

    private CriteriaBuilder builder;
    private Result<Student> result;
    private CriteriaQuery query;

    @BeforeEach
    void init() {
        builder = CriteriaBuilder.createBuilder();
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
    void test() {
        assertEquals("SELECT name, family FROM Student ", query.select("Student", "name", "family").getQueryString());
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
        SQLibException ex =
                assertThrows(SQLibException.class, () -> createPredicate().equal(result.get("id"), null));
        assertEquals(Constants.PARAM_MISSING, ex.getMessage());
    }

    @Test
    void selectMaxOfColumn() {
        assertEquals("SELECT MAX(age) FROM Student ",
                query.selectMax("Student", new Column("age")).getQueryString());
    }

    @Test
    void selectMinOfColumn() {
        assertEquals("SELECT MIN(income) FROM student ",
                query.selectMin("student", new Column("income")).getQueryString());
    }

    @Test
    void selectDistinctTest() {
        assertEquals("SELECT DISTINCT age, name FROM Student ",
                query.selectDistinct("Student", "age", "name").getQueryString());
    }

    @Test
    void selectOrderByTestASC() {
        assertEquals("SELECT age, name FROM Student ORDER BY avg ASC ",
                query.select("Student", "age", "name")
                        .orderBy(Order.ASC, "avg").getQueryString());
    }

    @Test
    void selectOrderByMultipleOrders() {
        assertEquals("SELECT age, name FROM Student ORDER BY grade, name ASC , age DESC ",
                query.select("Student", "age", "name")
                        .orderBy(Order.ASC, "grade", "name")
                        .orderBy(Order.DESC, "age").getQueryString());
    }

    @Test
    void groupByTest() {
        assertEquals("SELECT age, COUNT(name) FROM Student GROUP BY age ",
                query.select("Student", "age", COUNT(new Column("name")))
                        .groupBy("age").getQueryString());
    }

    @Test
    void groupByAndOrderByTest() {
        assertEquals("SELECT age FROM Student GROUP BY age ORDER BY COUNT(age) DESC ",
                query.select("Student", "age")
                        .groupBy("age").orderBy(Order.DESC, COUNT(new Column("age"))).getQueryString());
    }

    @Test
    void havingTest() {
        Predicate predicate1 = createPredicate().greaterThan(COUNT(new Column("age")), 5);

        assertEquals("SELECT COUNT(age), name FROM Student GROUP BY age HAVING COUNT(age) > 5",
                query.select("Student", COUNT(new Column("age")), "name")
                        .groupBy("age").having(predicate1.getConditionString()).getQueryString());
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