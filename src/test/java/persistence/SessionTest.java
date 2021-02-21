package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqlib.criteria.CriteriaBuilder;
import sqlib.query.Predicate;
import sqlib.persistence.Session;
import sqlib.query.internal.Column;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sqlib.criteria.CriteriaBuilder.createBuilder;
import static sqlib.query.Predicate.createPredicate;

public class SessionTest {

    private Session session;
    private String[] columns = {"name", "age", "average"};
    private Object[] values = {"Hadi", 12, 23.4};

    @BeforeEach
    void init() {
        session = new Session();
    }

    @Test
    void insertIntoWithValues() {
        session.insert("Student", 12, "Hadi", 1.2);
        assertEquals("INSERT INTO Student VALUES (12, 'Hadi', 1.2 )",
                session.getQueryString());
    }

    @Test
    void insertIntoWithSelectedColumns() {
        session.insert("Student", columns, values);
        assertEquals("INSERT INTO Student (name, age, average ) VALUES ('Hadi', 12, 23.4 )",
                session.getQueryString());
    }

    @Test
    void updateWithoutWhereClause() {
        session.update("Student", columns, values);
        assertEquals("UPDATE Student SET name='Hadi', age=12, average=23.4 ",
                session.getQueryString());
    }

    @Test
    void updateWithWhere() {
        CriteriaBuilder builder = createBuilder();
        Predicate predicate1 = createPredicate().equal(new Column("age"), 20);
        Predicate predicate2 = createPredicate().greaterThan(new Column("average"), 13.4);
        String whereClause = builder.and(predicate1, predicate2).getCompoundPredicateQueryString();

        assertEquals("UPDATE Student SET name='Hadi', age=12, " +
                        "average=23.4 WHERE age = 20 AND average > 13.4",
                session.update("Student", columns, values)
                        .where(whereClause).getQueryString());
    }

    @Test
    void deleteAllRecords() {
        assertEquals("DELETE FROM Student", session.delete("Student").getQueryString());
    }

    @Test
    void deleteWithCondition() {
        CriteriaBuilder builder = createBuilder();
        Predicate predicate1 = createPredicate().equal(new Column("age"), 20);
        String whereClause = predicate1.toString();

        assertEquals("DELETE FROM Student WHERE age = 20",
                session.delete("Student").where(whereClause).getQueryString());
    }

}