package sqlib.query;

import sqlib.query.internal.Condition;

import static sqlib.query.internal.Conjunction.*;

/**
 * author: Hadi Najafi
 */

public class Predicate {

    protected Condition condition = new Condition();

    public static Predicate createPredicate() {
        return new Predicate();
    }

    public Predicate equal(Object object1, Object object2) {
        condition.createPredicateClause(EQUAL, object1, object2);
        return this;
    }

    public Predicate notEqual(Object param1, Object param2) {
        condition.createPredicateClause(NOT_EQUAL, param1, param2);
        return this;
    }

    public Predicate like(Object param1, Object param2) {
        condition.createPredicateClause(LIKE, param1, param2);
        return this;
    }

    public Predicate between(Object param1, Object param2) {
        condition.createPredicateClause(BETWEEN, param1, param2);
        return this;
    }

    public Predicate greaterThan(Object param1, Object param2) {
        condition.createPredicateClause(GREATER_THAN, param1, param2);
        return this;
    }

    public Predicate lesserThan(Object param1, Object param2) {
        condition.createPredicateClause(LESSER_THAN, param1, param2);
        return this;
    }

    public Predicate greaterEqualThan(Object param1, Object param2) {
        condition.createPredicateClause(GREATER_EQUAL_THAN, param1, param2);
        return this;
    }

    public Predicate lesserEqualThan(Object param1, Object param2) {
        condition.createPredicateClause(LESSER_EQUAL_THAN, param1, param2);
        return this;
    }

    public String getConditionString() {
        return condition.getConditionString();
    }

    @Override
    public String toString() {
        return condition.getConditionString();
    }
}
