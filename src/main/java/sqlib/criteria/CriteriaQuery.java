package sqlib.criteria;

import common.util.SQLConstants;
import sqlib.query.Select;

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

    public String getQueryString() {
        return querySoFar;
    }

}

class Where {

    public static String where(String compoundPredicate) {
        return SQLConstants.WHERE + " " + compoundPredicate;
    }
}