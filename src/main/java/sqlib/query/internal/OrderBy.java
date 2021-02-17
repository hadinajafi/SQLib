package sqlib.query.internal;

import common.exception.SQLibException;
import sqlib.query.Query;

import static common.util.SQLConstants.ORDER_BY;

public class OrderBy extends Query {

    public OrderBy orderBy(Order order, String... columns) {
        StringBuilder builder = new StringBuilder();
        if (order == null || columns == null || columns.length == 0)
            throw new SQLibException("OrderBy query is malformed. missing parameters!");

        querySoFar = builder.append(ORDER_BY).append(" ")
                .append(generateStatement(columns)).append(order.order).append(" ").toString();
        return this;
    }

    public OrderBy orderByWithoutKeyWord(Order order, String... columns){
        StringBuilder builder = new StringBuilder();
        if (order == null || columns == null || columns.length == 0)
            throw new SQLibException("OrderBy query is malformed. missing parameters!");
        querySoFar = builder.append(", ").append(generateStatement(columns))
                .append(order.order).append(" ").toString();
        return this;
    }
}
