package sqlib.query;

import static common.util.SQLConstants.INSERT;
import static common.util.SQLConstants.VALUES;
import static java.util.stream.IntStream.range;

/**
 * author: Hadi Najafi
 */

public class Insert extends Query {

    public void insertInto(String table, String... values) {
        querySoFar = builder.append(INSERT).append(" ")
                .append(table).append(" ").append(VALUES).append(" ")
                .append(generateValuesStatement(values)).toString();
    }

    public void insertInto(String table, String[] columns, String[] values) {
        validateParametersLength(columns, values);
        querySoFar = builder.append(INSERT).append(" ").append(table).append(" ")
                .append(generateValuesStatement(columns)).append(" ").append(VALUES)
                .append(" ").append(generateValuesStatement(values)).toString();
    }

    private String generateValuesStatement(String[] parameter) {
        StringBuilder statement = new StringBuilder();
        statement.append("(");
        range(0, parameter.length).forEach(i -> {
            statement.append(parameter[i]);
            if (i != parameter.length - 1)
                statement.append(", ");
            else
                statement.append(" ");
        });
        statement.append(")");
        return statement.toString();
    }
}
