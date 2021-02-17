package sqlib.query;

import common.exception.SQLibException;
import sqlib.criteria.Result;

import java.util.Optional;

import static common.util.SQLConstants.*;
import static java.util.stream.IntStream.range;

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

    private String generateStatement(Result result) {
        StringBuilder statementBuilder = new StringBuilder();
        range(0, result.size()).forEach(i -> {
            statementBuilder.append(result.getColumnNames()[i]);
            if (i != result.size() - 1)
                statementBuilder.append(", ");
            else
                statementBuilder.append(" ");
        });
        return statementBuilder.toString();
    }

    private String generateStatement(String... columns){
        StringBuilder statementBuilder = new StringBuilder();
        range(0, columns.length).forEach(i -> {
            statementBuilder.append(columns[i]);
            if (i != columns.length - 1)
                statementBuilder.append(", ");
            else
                statementBuilder.append(" ");
        });
        return statementBuilder.toString();
    }

    private void addFromClause(String tableName) {
        querySoFar = builder.append(FROM).append(" ").append(tableName).append(" ").toString();
//        StatementValidator.verifyStatement(querySoFar);
    }
}
