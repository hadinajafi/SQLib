package sqlib.criteria;

/**
 * author: Hadi Najafi
 */

public class CriteriaBuilder {

    private final Predicate predicate = Predicate.createPredicate();
    private final CompoundPredicate compoundPredicate = new CompoundPredicate();
    private final StringBuilder compoundPredicateQuery = new StringBuilder();

    private CriteriaBuilder() {

    }

    public static CriteriaBuilder createConnection() {
        return new CriteriaBuilder();
    }

    public CriteriaQuery createQuery() {
        return null;
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
