package sqlib.query.internal;

import common.util.SQLConstants;
import sqlib.query.Query;

public class GroupBy extends Query {

    public String groupBy(String... columns){
        if(columns == null || columns.length == 0)
            return "";
        return builder.append(SQLConstants.GROUP_BY).append(" ")
                .append(generateStatement(columns)).toString();
    }
}
