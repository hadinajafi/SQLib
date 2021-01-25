package sqlib.criteria;


import static sqlib.criteria.Conjunction.AND;
import static sqlib.criteria.Conjunction.OR;

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
