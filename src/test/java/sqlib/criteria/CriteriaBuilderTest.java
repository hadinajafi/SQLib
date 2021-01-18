package sqlib.criteria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sqlib.connection.DbConnection;
import sqlib.connection.DbConnectionImp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CriteriaBuilderTest {

    private CriteriaBuilder builder;
    private DbConnection connection;
    private Result<TestClass> result;
    private CriteriaQuery query;

    @BeforeEach
    void init() {
        connection = new DbConnectionImp("test.db");
        builder = CriteriaBuilder.createConnection();
        result = new Result<>(TestClass.class);
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

        System.out.println(query.selectAny().where(builder.and(predicate1, predicate2)
                .getCompoundPredicateQueryString()).getQueryString());

    }

    class TestClass {
        private int id;
    }

}