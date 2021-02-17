package sqlib.criteria;

import sqlib.query.*;
import sqlib.query.internal.*;

import static sqlib.query.functions.SqlFunctions.*;

/**
 * author: Hadi Najafi
 */

public class CriteriaQuery {

    private final Select selection = new Select();
    private final OrderBy orderBy = new OrderBy();
    private final GroupBy group = new GroupBy();
    private String querySoFar;

    public CriteriaQuery select(Result result) {
        querySoFar = selection.select(result.getClassName(), result.getColumnNames()).getQueryString();
        return this;
    }

    public CriteriaQuery select(String tableName, String... columns) {
        querySoFar = selection.select(tableName, columns).getQueryString();
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

    public CriteriaQuery selectDistinct(String tableName, String... columns) {
        querySoFar = selection.select(tableName, columns, Options.DISTINCT).getQueryString();
        return this;
    }

    public CriteriaQuery orderBy(Order order, String... columns) {
        if (!querySoFar.contains("ORDER BY"))
            querySoFar += orderBy.orderBy(order, columns).getQueryString();
        else
            querySoFar += orderBy.orderByWithoutKeyWord(order, columns).getQueryString();
        return this;
    }

    public CriteriaQuery groupBy(String... columns) {
        querySoFar += group.groupBy(columns);
        return this;
    }

    public CriteriaQuery having (String compoundPredicate){
        querySoFar += Having.having(compoundPredicate);
        return this;
    }

    public String getQueryString() {
        return querySoFar;
    }

}