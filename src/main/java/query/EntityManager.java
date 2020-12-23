package query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

@Setter
@Getter
public class EntityManager {

    private Class clazz;
    private Field[] fields;

    private EntityManager(Class clazz) {
        this.clazz = clazz;
        this.fields = clazz.getDeclaredFields();
    }

    public static EntityManager createEntityManager(Class clazz){
        return new EntityManager(clazz);
    }
}
