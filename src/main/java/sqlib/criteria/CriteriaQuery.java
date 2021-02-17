package sqlib.criteria;

import sqlib.query.Column;
import sqlib.query.Select;
import sqlib.query.Where;
import sqlib.query.functions.SqlFunctions;

/**
 * author: Hadi Najafi
 */

public class CriteriaQuery {

    private final Select selection = new Select();
    private String querySoFar;

    public CriteriaQuery select(Result result) {
        querySoFar = selection.select(result.getClassName(), result.getColumnNames()).getQueryString();
        return this;
    }

    public CriteriaQuery selectAny(Result result) {
        querySoFar = selection.select(result.getClassName(), "*").getQueryString();
        return this;
    }

    public CriteriaQuery where(String compoundPredicate) {
        querySoFar += Where.where(compoundPredicate);
        return this;
    }

    public CriteriaQuery selectMin(String tableName, Column column) {
        querySoFar = selection.select(tableName, SqlFunctions.MIN(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectMax(String tableName, Column column) {
        querySoFar = selection.select(tableName, SqlFunctions.MAX(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectCount(String tableName, Column column) {
        querySoFar = selection.select(tableName, SqlFunctions.COUNT(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectAvg(String tableName, Column column) {
        querySoFar = selection.select(tableName, SqlFunctions.AVG(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectSum(String tableName, Column column) {
        querySoFar = selection.select(tableName, SqlFunctions.SUM(column)).getQueryString();
        return this;
    }

    public String getQueryString() {
        return querySoFar;
    }

}