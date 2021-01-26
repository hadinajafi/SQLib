package sqlib.query;

import common.exception.SQLibException;
import sqlib.criteria.Result;

import static common.util.Constants.SELECT;
import static java.util.stream.IntStream.range;

public class Select extends Query{

    public Select select(String tableName, String... columns) {
        if (columns == null || columns.length == 0)
            throw new SQLibException("Columns parameter was empty, failed to select data.");
        builder.append(SELECT).append(" ");
        range(0, columns.length).forEach(i -> {
            builder.append(columns[i]);
            if (i != columns.length - 1)
                builder.append(", ");
            else
                builder.append(" ");
        });
        querySoFar = builder.toString();
        addFromClause(tableName);
        return this;
    }

    public Select select(Result result) {
        if (!result.hasFields())
            throw new SQLibException("The provided class has no attributes, selection failed!");
        builder.append(SELECT).append(" ");
        range(0, result.size()).forEach(i -> {
            builder.append(result.getColumnNames()[i]);
            if (i != result.size() - 1)
                builder.append(", ");
            else
                builder.append(" ");
        });
        querySoFar = builder.toString();
        addFromClause(result.getClassName());
        return this;
    }
}
