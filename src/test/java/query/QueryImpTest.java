package query;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryImpTest {

    private Query query;

    @BeforeEach
    void setup() {
        query = QueryImp.createQuery();
    }

    @Test
    @Disabled
    void createEmptyQueryShouldReturnsEmpty() throws Exception {
        assertEquals("", query.getQueryString());
    }

    @Test
    void selectTwiceShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.select(new String[]{"*"}).select(new String[]{"*"}).getQueryString());
        assertEquals("Invalid query statement!", ex.getMessage());
    }

    @Test
    void fromWithoutTableShouldThrowsException() {
        Exception ex = assertThrows(Exception.class, () -> query.from(""));
        assertEquals("Invalid query statement!", ex.getMessage());
    }

    @Test
    void selectFromATableShouldReturnCorrect() throws Exception {
        query.select(new String[]{"*"}).from("table");
        assertEquals("SELECT * FROM table ", query.getQueryString());
    }

    @Test
    void fromBeforeSelectShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.from("table").select(new String[]{"*"}));
        assertEquals("Invalid query statement!", ex.getMessage());
    }

    @Test
    void selectionWithPuttingStringColumns() throws Exception {
        assertEquals("SELECT id, name FROM table ", query
                .select(new String[]{"id", "name"}).from("table").getQueryString());
    }

    @Test
    void withoutColumnSelectionShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.select(new String[]{""}).from("table"));
        assertEquals("Invalid query statement!", ex.getMessage());
    }

    @Test
    void selectAllColumnsWithoutFromStatementShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.select(new String[]{"*"}).getQueryString());
        assertEquals("Invalid query statement!", ex.getMessage());
    }

    @Test
    void selectionWithCustomClassShouldReturnCorrectResult() throws Exception {
        query.select(test.class).from("table");
        assertEquals("SELECT id, name FROM table ", query.getQueryString());
    }

    @Test
    void selectionWithClassAndWhereConditions() throws Exception {
        query.select(test.class).from("table").condition("");

    }

    @Data
    @AllArgsConstructor
    static class test {
        private int id;
        private String name;
    }

}