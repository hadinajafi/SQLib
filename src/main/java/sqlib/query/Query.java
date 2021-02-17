package sqlib.query;

import common.exception.SQLibException;
import sqlib.criteria.Result;

import static java.util.stream.IntStream.range;

/**
 * author: Hadi Najafi
 */

public abstract class Query {

    protected StringBuilder builder = new StringBuilder();
    protected String querySoFar;

    public String getQueryString() {
        return querySoFar;
    }

    protected void checkIfHasSchema(Result result) throws SQLibException {
        if (!result.hasFields())
            throw new SQLibException("The provided class has no attributes, selection failed!");
    }

    protected void validateParametersLength(Object[] columns, Object[] values){
        if(columns.length != values.length)
            throw new SQLibException("Number of values is not same as number of columns");
    }

    protected String generateStatement(Result result) {
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

    protected String generateStatement(String... columns){
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
}
