package sqlib.criteria;

import static common.util.Constants.AND;
import static common.util.Constants.OR;

/**
 * author: Hadi Najafi
 */

public class CompoundPredicate extends Predicate {

    public Predicate and(Predicate... predicates) {
        conditionString = condition.createPredicateClause(AND, predicates).getConditionString();
        return this;
    }

    public Predicate or(Predicate... predicates) {
        conditionString = condition.createPredicateClause(OR, predicates).getConditionString();
        return this;
    }
}
