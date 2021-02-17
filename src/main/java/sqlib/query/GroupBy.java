package sqlib.query;

import common.util.SQLConstants;

public class GroupBy extends Query{

    public String groupBy(String... columns){
        if(columns == null || columns.length == 0)
            return "";
        return builder.append(SQLConstants.GROUP_BY).append(" ")
                .append(generateStatement(columns)).toString();
    }
}
