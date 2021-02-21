package sqlib.criteria;

import sqlib.query.CompoundPredicate;
import sqlib.query.Predicate;

/**
 * author: Hadi Najafi
 */

public class CriteriaBuilder {

    private final Predicate predicate;
    private final CompoundPredicate compoundPredicate;
    private final StringBuilder compoundPredicateQuery;

    private CriteriaBuilder() {
        predicate = Predicate.createPredicate();
        compoundPredicate = new CompoundPredicate();
        compoundPredicateQuery = new StringBuilder();
    }

    public static CriteriaBuilder createBuilder() {
        return new CriteriaBuilder();
    }

    public CriteriaBuilder and(Predicate... predicates) {
        compoundPredicateQuery.append(compoundPredicate.and(predicates).getConditionString());
        return this;
    }

    public CriteriaBuilder or(Predicate... predicates) {
        this.compoundPredicate.or(predicates);
        return this;
    }

    public String getCompoundPredicateQueryString() {
        return compoundPredicateQuery.toString();
    }

}
