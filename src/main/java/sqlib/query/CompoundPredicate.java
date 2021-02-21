package sqlib.query;


import sqlib.query.Predicate;

import static sqlib.query.internal.Conjunction.AND;
import static sqlib.query.internal.Conjunction.OR;

/**
 * author: Hadi Najafi
 */

public class CompoundPredicate extends Predicate {

    public Predicate and(Predicate... predicates) {
        condition.createPredicateClause(AND, predicates);
        return this;
    }

    public Predicate or(Predicate... predicates) {
        condition.createPredicateClause(OR, predicates);
        return this;
    }
}
