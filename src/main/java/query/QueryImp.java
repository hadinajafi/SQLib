package query;

import common.exception.InvalidQueryException;
import common.exception.StatementValidator;
import criteria.Condition;
import criteria.Selection;
import criteria.SelectionImp;

import static common.util.Constants.FROM;

public class QueryImp implements Query {

    private final Selection selection;

    private final StringBuilder stringBuilder;

    private QueryImp() {
        stringBuilder = new StringBuilder();
        selection = new SelectionImp();
    }

    public static QueryImp createQuery() {
        return new QueryImp();
    }

    /**
     * @return Query string so far created by builder
     */
    @Override
    public String getQueryString() {
        String querySoFar = stringBuilder.toString();
        StatementValidator.verifyStatement(querySoFar);
        return querySoFar;
    }

    /**
     * @return This
     */
    @Override
    public Query select(String... columns) {
        stringBuilder.append(selection.select(columns).getQueryString()).toString();
        return this;
    }

    @Override
    public Query select(Class clazz) {
        fieldsOf(clazz);
        return this;
    }

    private void fieldsOf(Class object) {
        stringBuilder.append(selection.select(object).getQueryString()).toString();
    }

    /**
     * @param table Required table name
     * @return This
     */
    @Override
    public Query from(String table) throws InvalidQueryException {
        stringBuilder.append(FROM).append(" ").append(table).append(" ").toString();
        StatementValidator.verifyStatement(stringBuilder.toString());
        return this;
    }

    @Override
    public Query where(Condition condition) {
        stringBuilder.append(condition.getConditionString()).toString();
        return this;
    }
}
