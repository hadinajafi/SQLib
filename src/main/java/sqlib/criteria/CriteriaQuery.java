package sqlib.criteria;

import sqlib.query.Column;
import sqlib.query.Options;
import sqlib.query.Select;
import sqlib.query.Where;
import static sqlib.query.functions.SqlFunctions.*;

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
        querySoFar = selection.select(tableName, MIN(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectMax(String tableName, Column column) {
        querySoFar = selection.select(tableName, MAX(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectCount(String tableName, Column column) {
        querySoFar = selection.select(tableName, COUNT(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectAvg(String tableName, Column column) {
        querySoFar = selection.select(tableName, AVG(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectSum(String tableName, Column column) {
        querySoFar = selection.select(tableName, SUM(column)).getQueryString();
        return this;
    }

    public CriteriaQuery selectDistinct(String tableName, String... columns){
        querySoFar = selection.select(tableName, columns, Options.DISTINCT).getQueryString();
        return this;
    }

    public String getQueryString() {
        return querySoFar;
    }

}