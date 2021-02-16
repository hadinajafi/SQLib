package sqlib.query;

import static common.util.SQLConstants.SET;
import static common.util.SQLConstants.UPDATE;
import static java.util.stream.IntStream.range;

public class Update extends Query {

    public void update(String table, String[] columns, String[] values) {
        validateParametersLength(columns, values);
        builder.append(UPDATE).append(" ").append(table).append(" ")
                .append(SET).append(" ");
        range(0, columns.length).forEach(i -> {
            builder.append(columns[i]).append("=").append(values[i]);
            if (i != columns.length - 1)
                builder.append(", ");
            else
                builder.append(" ");
        });
        querySoFar = builder.toString();
    }
}
