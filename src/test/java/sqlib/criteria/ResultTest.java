package sqlib.criteria;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    @Test
    void getColumnNamesShouldWork() {
        var result = new Result<>(TestClazz.class);
        assertEquals("[id, age, name]", Arrays.toString(result.getColumnNames()));
    }

    class TestClazz {
        private long id;
        private Integer age;
        private String name;
    }

}