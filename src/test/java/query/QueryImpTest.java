package query;

import common.exception.InvalidQueryException;
import criteria.ConditionImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static common.util.Constants.*;
import static criteria.ConditionImp.createConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryImpTest {

    private Query query;

    @BeforeEach
    void setup() {
        query = QueryImp.createQuery();
    }

    @Test
    void createEmptyQueryShouldReturnsEmpty() {
        Exception ex = assertThrows(InvalidQueryException.class, () -> query.getQueryString());
        assertEquals(MISSING_SELECTION_PROCEDURES, ex.getMessage());
    }

    @Test
    void selectTwiceShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.select(new String[]{"*"}).select(new String[]{"*"}).getQueryString());
        assertEquals(MISSING_FROM_PROCEDURE, ex.getMessage());
    }

    @Test
    void queryWithoutSelectionShouldThrowsException() {
        Exception ex = assertThrows(InvalidQueryException.class, () -> query.from(""));
        assertEquals(MISSING_SELECTION_PROCEDURES, ex.getMessage());
    }

    @Test
    void selectFromATableShouldReturnCorrect() throws Exception {
        query.select(new String[]{"*"}).from("table");
        assertEquals("SELECT * FROM table ", query.getQueryString());
    }

    @Test
    void fromBeforeSelectShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.from("table").select(new String[]{"*"}));
        assertEquals(MISSING_SELECTION_PROCEDURES, ex.getMessage());
    }

    @Test
    void selectionWithPuttingStringColumns() throws Exception {
        assertEquals("SELECT id, name FROM table ", query
                .select(new String[]{"id", "name"}).from("table").getQueryString());
    }

    @Test
    void withoutColumnSelectionShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.select(new String[]{""}).from("table"));
        assertEquals(INVALID_QUERY, ex.getMessage());
    }

    @Test
    void selectAllColumnsWithoutFromStatementShouldThrowException() {
        Exception ex = assertThrows(Exception.class, () -> query.select(new String[]{"*"}).getQueryString());
        assertEquals(MISSING_FROM_PROCEDURE, ex.getMessage());
    }

    @Test
    void selectionWithCustomClassShouldReturnCorrectResult() throws Exception {
        query.select(test.class).from("table");
        assertEquals("SELECT id, name FROM table ", query.getQueryString());
    }

    @Test
    void selectionWithClassAndWhereConditions() throws Exception {
        assertEquals("SELECT id, name FROM table WHERE id = 10 ", query
                .select(test.class).from("table").where(createConditions().equalTo("id", 10)).getQueryString());
    }

    @Test
    void selectionWithNullConditionParameterShouldThrowsException() throws NullPointerException {
        Exception ex = assertThrows(NullPointerException.class, () -> query
                .select(test.class).from("table").where(createConditions().equalTo("id", null)));
        assertEquals(PARAM_MISSING, ex.getMessage());
    }



    @Data
    @AllArgsConstructor
    static class test {
        private int id;
        private String name;
    }

}