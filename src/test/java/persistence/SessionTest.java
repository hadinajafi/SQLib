package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqlib.persistence.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

    private Session session;

    @BeforeEach
    void init() {
        session = new Session();
    }

    @Test
    void insertIntoWithValues() {
        session.insert("Student", 12, "Hadi", 1.2);
        assertEquals("INSERT INTO Student VALUES (12, 'Hadi', 1.2 )",
                session.queryString());
    }

    @Test
    void insertIntoWithSelectedColumns() {
        session.insert("Student", new String[]{"name", "age", "average"},
                new Object[]{"Hadi", 12, 23.4});
        assertEquals("INSERT INTO Student (name, age, average ) VALUES ('Hadi', 12, 23.4 )", session.queryString());
    }

}