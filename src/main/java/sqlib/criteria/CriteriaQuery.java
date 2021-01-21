package sqlib.criteria;

import sqlib.query.Select;
import sqlib.query.SelectImp;
import sqlib.query.Where;

/**
 * author: Hadi Najafi
 */

public class CriteriaQuery {

    private final Select selection = new SelectImp();
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
