package sqlib.query;

import common.exception.SQLibException;
import sqlib.criteria.Result;

import static common.util.SQLConstants.FROM;
import static common.util.SQLConstants.SELECT;

public class Select extends Query {

    public Select select(String tableName, String[] columns, Options options) {
        if (columns == null || columns.length == 0)
            throw new SQLibException("Columns parameter was empty, failed to select data.");

        if (options == null)
            builder.append(SELECT).append(" ");
        else
            builder.append(SELECT).append(" ").append(options.option).append(" ");
        querySoFar = builder.append(generateStatement(columns)).toString();
        addFromClause(tableName);
        return this;
    }

    public Select select(String tableName, String... columns) {
        if (columns == null || columns.length == 0)
            throw new SQLibException("Columns parameter was empty, failed to select data.");
        querySoFar = builder.append(SELECT).append(" ")
                .append(generateStatement(columns)).toString();
        addFromClause(tableName);
        return this;
    }

    public Select select(Result result, Options options) {
        checkIfHasSchema(result);
        if (options == null)
            builder.append(SELECT).append(" ");
        else
            builder.append(SELECT).append(" ").append(options.option).append(" ");

        querySoFar = builder.append(generateStatement(result)).toString();
        addFromClause(result.getClassName());
        return this;
    }

    public Select select(Result result) {
        checkIfHasSchema(result);
        querySoFar = builder.append(SELECT).append(" ")
                .append(generateStatement(result)).toString();
        addFromClause(result.getClassName());
        return this;
    }

    private void addFromClause(String tableName) {
        querySoFar = builder.append(FROM).append(" ").append(tableName).append(" ").toString();
//        StatementValidator.verifyStatement(querySoFar);
    }
}
