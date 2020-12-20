package query;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class QueryImpTest {

    private Query query;
//    private QueryBuilder builder;

    @BeforeEach
    void setup(){
//        builder = new QueryBuilder();
        query = new QueryImp();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmptyQueryShouldReturnsEmpty(){
//        query = builder.createQuery("");
        assertEquals("", query.getQueryString());
    }

    @Test
    void selectQueryShouldReturnCorrectResult() throws Exception {
//        query = builder.createQuery();
        query.select();
        assertEquals("SELECT", query.getQueryString().trim());
    }

    @Test
    void selectTwiceShouldThrowException() {
        Exception ex = assertThrows(Exception.class, ()-> query.select().select());
        assertEquals("SELECT statement used in wrong order!", ex.getMessage());
    }

    @Test
    void fromWithoutTableShouldThrowsException() {
        Exception ex = assertThrows(Exception.class, ()-> query.from(""));
        assertEquals("Required parameter is missing!", ex.getMessage());
    }

    @Test
    void selectFromATableShouldReturnCorrect() throws Exception {
        query.select().all().from("table");
        assertEquals("SELECT * FROM table ", query.getQueryString());
    }

    @Test
    void fromBeforeSelectShouldThrowException(){
        Exception ex = assertThrows(Exception.class, ()-> query.from("table").select());
        assertEquals("FROM statement can't come before SELECT statement!", ex.getMessage());
    }

    @Test
    @Disabled
    void withoutColumnSelectionShouldThrowException(){
        Exception ex = assertThrows(Exception.class, ()-> query.select().from("table"));
        assertEquals("No column selected!", ex.getMessage());
    }

    @Test
    void selectAllColumnsWithoutSelectStatementShouldThrowException(){
        Exception ex = assertThrows(Exception.class, ()-> query.all());
        assertEquals("SELECT statement is missing!", ex.getMessage());
    }

    @Test
    void selectionWithCustomClassShouldReturnCorrectResult() throws Exception {
        query.select().fieldsOf(test.class).from("table");
        assertEquals("SELECT id, name FROM table ", query.getQueryString());
    }

    @Test
    void selectionWithCustomClassWithoutSelectStatement() {
        Exception ex = assertThrows(Exception.class, ()-> query.fieldsOf(test.class));
        assertEquals("SELECT statement is missing!", ex.getMessage());
    }

    @Data
    static class test{
        private int id;
        private String name;
    }

}