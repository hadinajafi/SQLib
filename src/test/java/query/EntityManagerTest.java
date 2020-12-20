package query;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityManagerTest {

    private EntityManager<test> em;

    @BeforeEach
    void init(){
        em = new EntityManager<>(test.class);
    }

    @Test
    void getClassShouldWork(){
        assertEquals(test.class, em.getClazz());
    }

    @Test
    void setClassShouldWork(){
        em.setClazz(test.class);
        assertEquals(test.class, em.getClazz());
    }

    @Test
    void getFields(){
        assertEquals("id", em.getFields()[0].getName());
    }

    @Data
    public static class test{
        private int id;
    }
}