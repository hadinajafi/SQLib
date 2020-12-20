package query;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

@Setter
@Getter
public class EntityManager<T> {

    private Class<T> clazz;
    private Field[] fields;

    public EntityManager(Class<T> clazz) {
        this.clazz = clazz;
        this.fields = clazz.getDeclaredFields();
    }
}
