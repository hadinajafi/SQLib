package query;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class QueryImpTest {

    @Mock
    private QueryImp queryImp;
//    private QueryBuilder builder;

    @BeforeEach
    void setup(){
//        builder = new QueryBuilder();
        queryImp = new QueryImp();
    }

    @Test
    void createEmptyQueryShouldReturnsEmpty(){
//        query = builder.createQuery("");
        assertEquals("", queryImp.getQueryString());
    }

    @Test
    void selectQueryShouldReturnCorrectResult() throws Exception {
//        query = builder.createQuery();
        queryImp.select();
        assertEquals("SELECT", queryImp.getQueryString().trim());
    }

    @Test
    void selectTwiceShouldThrowException() throws Exception{
        Exception ex = assertThrows(Exception.class, ()->{queryImp.select().select();});
        assertEquals("SELECT statement used in wrong order!", ex.getMessage());
    }

    @Test
    void fromWithoutTableShouldThrowsException() throws Exception {
        Exception ex = assertThrows(Exception.class, ()->{queryImp.from("");});
        assertEquals("Required parameter missed!", ex.getMessage());
    }

    @Test
    void selectFromATableShouldReturnCorrect() throws Exception {
        queryImp.select().all().from("table");
        assertEquals("SELECT * FROM table ", queryImp.getQueryString());
    }

    @Test
    void fromBeforeSelectShouldThrowException(){
        Exception ex = assertThrows(Exception.class, ()->{
            queryImp.from("table").select();
        });
        assertEquals("FROM statement can't come before SELECT statement.", ex.getMessage());
    }

    @Test
    void withouSelectionShouldThrowException(){
        Exception ex = assertThrows(Exception.class, ()->{
            queryImp.select().from("table");
        });
        assertEquals("No column selected!", ex.getMessage());
    }

    @Test
    void selectAllColumnsWithoutSelectStatementShouldThrowException(){
        Exception ex = assertThrows(Exception.class, ()->{
            queryImp.all();
        });
        assertEquals("SELECT statement missed!", ex.getMessage());
    }

}