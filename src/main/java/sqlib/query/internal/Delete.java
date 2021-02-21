package sqlib.query.internal;

import sqlib.query.Query;

import static common.util.SQLConstants.DELETE;
import static common.util.SQLConstants.FROM;

public class Delete extends Query {

    public void deleteFrom(String tableName) {
        querySoFar = builder.append(DELETE).append(" ").append(FROM).append(" ")
                .append(tableName).toString();
        System.out.println(querySoFar);
    }
}
