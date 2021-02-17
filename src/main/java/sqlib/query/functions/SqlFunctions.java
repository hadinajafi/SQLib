package sqlib.query.functions;

import sqlib.query.Column;

import static common.util.SQLConstants.*;

public class SqlFunctions {

    public static String MIN(Column column) {
        return String.format("%s(%s)", MIN, column.getName());
    }

    public static String MAX(Column column) {
        return String.format("%s(%s)", MAX, column.getName());
    }

    public static String COUNT(Column column){
        return String.format("%s(%s)", COUNT, column.getName());
    }

    public static String AVG(Column column){
        return String.format("%s(%s)", AVG, column.getName());
    }

    public static String SUM(Column column){
        return String.format("%s(%s)", SUM, column.getName());
    }
}
