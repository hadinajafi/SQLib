package sqlib.query;

import common.exception.SQLibException;
import sqlib.criteria.Result;

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
}
