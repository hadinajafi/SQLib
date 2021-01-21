package sqlib;

import java.lang.reflect.Field;

public class EntityManager {

    private Class clazz;
    private Field[] fields;

    private EntityManager(Class clazz) {
        this.clazz = clazz;
        this.fields = clazz.getDeclaredFields();
    }

    public static EntityManager createEntityManager(Class clazz) {
        return new EntityManager(clazz);
    }

    public Class getClazz() {
        return clazz;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public String getClassName() {
        return this.clazz.getSimpleName();
    }
}
