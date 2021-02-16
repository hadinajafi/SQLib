package sqlib.query;

import sqlib.persistence.PersistenceException;

import static common.util.SQLConstants.INSERT;
import static common.util.SQLConstants.VALUES;
import static java.util.stream.IntStream.range;

/**
 * author: Hadi Najafi
 */

public class Insert extends Query {

    public void insertInto(String table, String... values) {
        querySoFar = builder.append(INSERT).append(" ")
                .append(table).append(" ").append(VALUES)
                .append(generateValuesStatement(values)).toString();
    }

    public void insertInto(String table, String[] columns, String[] values) {
        insertValidation(columns, values);
        querySoFar = builder.append(INSERT).append(" ").append(table).append(" ")
                .append(generateValuesStatement(columns)).append(VALUES)
                .append(generateValuesStatement(values)) .toString();

    }

    private String generateValuesStatement(String[] parameter) {
        StringBuilder statement = new StringBuilder();
        statement.append(" (");
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

    private void insertValidation(String[] columns, String[] values) throws PersistenceException {
        if (columns.length != values.length)
            throw new PersistenceException("Number of values is not same as number of columns");
    }
}
