package criteria;

import static common.util.Constants.PARAM_MISSING;
import static common.util.Constants.WHERE;

public class ConditionImp implements Condition {
    private final StringBuilder builder;

    private ConditionImp() {
        this.builder = new StringBuilder();
        builder.append(WHERE).append(" ");
    }

    public static Condition createConditions(){
        return new ConditionImp();
    }

    @Override
    public Condition equalTo(Object ob1, Object ob2) {
        checkParametersNullability(ob1);
        checkParametersNullability(ob2);
        builder.append(equalQueryGenerator(ob1, ob2));
        return this;
    }

    @Override
    public Condition greaterThan(Object object1, Object object2) {
        checkParametersNullability(object1);
        checkParametersNullability(object2);
        builder.append(greaterThanQueryGenerator(object1, object2));
        return this;
    }

    @Override
    public Condition lowerThan(Object object1, Object object2) {
        checkParametersNullability(object1);
        checkParametersNullability(object2);
        builder.append(lowerThanQueryGenerator(object1, object2));
        return this;
    }

    @Override
    public Condition greaterThanEqual(Object object1, Object object2) {
        checkParametersNullability(object1);
        checkParametersNullability(object2);
        builder.append(greaterThanEqualQueryGenerator(object1, object2));
        return this;
    }

    @Override
    public Condition lowerThanEqual(Object object1, Object object2) {
        checkParametersNullability(object1);
        checkParametersNullability(object2);
        builder.append(lowerThanEqualQueryGenerator(object1, object2));
        return this;
    }

    @Override
    public Condition like(Object object1, Object object2) {
        return null;
    }

    @Override
    public Condition between(Object item, Object object1, Object object2) {
        checkParametersNullability(object1);
        checkParametersNullability(object2);
        checkParametersNullability(item);
        builder.append(betweenQueryGenerator(item, object1, object2));
        return this;
    }

    @Override
    public String getConditionString() {
        return builder.toString();
    }

    private void checkParametersNullability(Object object) {
        if (object == null)
            throw new NullPointerException(PARAM_MISSING);
    }

    private String equalQueryGenerator(Object o1, Object o2) {
        return String.format("%s = %s ", o1, o2);
    }

    private String greaterThanQueryGenerator(Object ob1, Object ob2) {
        return String.format("%s > %s", ob1, ob2);
    }

    private String lowerThanQueryGenerator(Object o1, Object o2) {
        return String.format("%s < %s ", o1, o2);
    }

    private String lowerThanEqualQueryGenerator(Object o1, Object o2) {
        return String.format("%s <= %s ", o1, o2);
    }

    private String greaterThanEqualQueryGenerator(Object o1, Object o2) {
        return String.format("%s >= %s ", o1, o2);
    }

    private String betweenQueryGenerator(Object item, Object o1, Object o2) {
        return String.format("%s BETWEEN %s AND %s", item, o1, o2);
    }
}
