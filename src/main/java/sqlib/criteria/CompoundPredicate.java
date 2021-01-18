package sqlib.criteria;

import static common.util.Constants.AND;
import static common.util.Constants.OR;

/**
 * author: Hadi Najafi
 */

public class CompoundPredicate extends Predicate {

    public Predicate and(Predicate predicate1, Predicate predicate2) {
        conditionString = condition.createPredicateClause(AND, predicate1.toString(), predicate2.toString())
                .getConditionString();
        return this;
    }

    public Predicate or(String... parameters) {
        conditionString = condition.createPredicateClause(OR, parameters).getConditionString();
        return this;
    }
}
