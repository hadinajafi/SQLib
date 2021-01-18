package sqlib.criteria;

import static common.util.Constants.*;

/**
 * author: Hadi Najafi
 */

public class Predicate {

    protected Condition condition = new Condition();
    ;
    protected String conditionString = "";

    public Predicate() {

    }

    public static enum BooleanOperator {
        AND, OR
    }

    public Predicate equal(Object object1, Object object2) {
        conditionString = condition.createPredicateClause(EQUAL, object1, object2).getConditionString();
        return this;
    }

    public Predicate notEqual(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(NOT_EQUAL, param1, param2).getConditionString();
        return this;
    }

    public Predicate like(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(LIKE, param1, param2).getConditionString();
        return this;
    }

    public Predicate between(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(BETWEEN, param1, param2).getConditionString();
        return this;
    }

    public Predicate greaterThan(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(GREATER_THAN, param1, param2).getConditionString();
        return this;
    }

    public Predicate lesserThan(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(LESSER_THAN, param1, param2).getConditionString();
        return this;
    }

    public Predicate greaterEqualThan(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(GREATER_EQUAL_THAN, param1, param2).getConditionString();
        return this;
    }

    public Predicate lesserEqualThan(Object param1, Object param2) {
        conditionString = condition.createPredicateClause(LESSER_EQUAL_THAN, param1, param2).getConditionString();
        return this;
    }

    public String getConditionString() {
        return conditionString;
    }

    @Override
    public String toString() {
        return conditionString;
    }
}
