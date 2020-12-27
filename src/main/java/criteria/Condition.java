package criteria;

public interface Condition {

    Condition equalTo(Object object1, Object object2);

    Condition greaterThan(Object object1, Object object2);

    Condition lowerThan(Object object1, Object object2);

    Condition greaterThanEqual(Object object1, Object object2);

    Condition lowerThanEqual(Object object1, Object object2);

    Condition like(Object object1, Object object2);

    Condition between(Object item, Object object1, Object object2);

    String getConditionString();
}
