package sqlib.criteria;

import lombok.SneakyThrows;
import sqlib.Column;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * author: Hadi Najafi
 */

public class Result<T> {

    private final Class<T> clazz;
    private Column[] columns;

    public Result(Class<T> clazz) {
        this.clazz = clazz;
        setColumns();
    }

    private void setColumns() {
        int totalFields = clazz.getDeclaredFields().length;
        columns = new Column[totalFields - 1];
        setColumnNames(clazz.getDeclaredFields());
    }

    private void setColumnNames(Field[] fields) {
        Arrays.setAll(columns, i -> Column.set(fields[i].getName()));
    }

    public String[] getColumnNames() {
        var names = new String[columns.length];
        Arrays.setAll(names, i -> columns[i].getName());
        return names;
    }

    @SneakyThrows
    public String get(String attribute) {
        for (Column col : columns)
            if (col.getName().equals(attribute)) {
                return attribute;
            }
        throw new Exception("Column " + attribute + " is not defined in this class!");
    }
}
