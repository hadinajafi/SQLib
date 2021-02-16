package sqlib.criteria;

import sqlib.query.Column;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * author: Hadi Najafi
 */

public class Result<T> {

    private final Class<T> clazz;
    private Column[] columns;
    private final String className;

    public Result(Class<T> clazz) {
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        setColumns();
    }

    private void setColumns() {
        int totalFields = clazz.getDeclaredFields().length;
        columns = new Column[totalFields];
        setColumnNames(clazz.getDeclaredFields());
    }

    private void setColumnNames(Field[] fields) {
        Arrays.setAll(columns, i -> Column.set(fields[i].getName(), fields[i].getType().getSimpleName()));
    }

    public String[] getColumnNames() {
        var names = new String[columns.length];
        Arrays.setAll(names, i -> columns[i].getName());
        return names;
    }

    public Column get(String attribute) throws Exception {
        for (Column col : columns)
            if (col.getName().equals(attribute)) {
                return col;
            }
        throw new Exception("Column " + attribute + " is not defined in this class!");
    }

    /**
     * Used for table name
     * @return class name
     */
    public String getClassName() {
        return className;
    }

    public boolean hasFields() {
        return columns.length != 0;
    }

    public int size() {
        return columns.length;
    }
}
