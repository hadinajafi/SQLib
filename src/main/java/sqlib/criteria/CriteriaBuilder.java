package sqlib.criteria;

/**
 * author: Hadi Najafi
 */

public class CriteriaBuilder {

    private final Predicate predicate = new Predicate();
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

    public CriteriaBuilder and(Predicate predicate1, Predicate predicate2) {
//        this.compoundPredicate.and(predicate1, predicate2);
        compoundPredicateQuery.append(compoundPredicate.and(predicate1, predicate2).getConditionString());
        return this;
    }

    public CriteriaBuilder or(String... parameters) {
        this.compoundPredicate.or(parameters);
        return this;
    }

    public String getCompoundPredicateQueryString() {
        return compoundPredicateQuery.toString();
    }

}
