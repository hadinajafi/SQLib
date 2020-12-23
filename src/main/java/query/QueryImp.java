package query;

import common.exception.StatementValidator;
import criteria.Selection;
import criteria.SelectionImp;

import static common.exception.ExceptionMessageBuilder.messageBuilder;
import static common.util.Constants.*;

public class QueryImp implements Query {

    private String querySoFar;
    private Selection selection;

    private final StringBuilder stringBuilder;

    private QueryImp() {
        querySoFar = "";
        stringBuilder = new StringBuilder();
        stringBuilder.append(querySoFar);
        selection = new SelectionImp();
    }

    public static QueryImp createQuery(){
        return new QueryImp();
    }

    /**
     * @return Query string so far created by builder
     */
    @Override
    public String getQueryString() throws Exception {
        StatementValidator.verifyStatement(querySoFar);
        return querySoFar;
    }

    /**
     * @return This
     */
    @Override
    public Query select(String... columns) {
        querySoFar = stringBuilder.append(selection.select(columns).getQueryString()).toString();
        return this;
    }

    @Override
    public Query select(Class clazz) {
        fieldsOf(clazz);
        return this;
    }

    private Query fieldsOf(Class object) {
        querySoFar = stringBuilder.append(selection.select(object).getQueryString()).toString();
        return this;
    }

    /**
     * @param table Required table name
     * @return This
     */
    @Override
    public Query from(String table) throws Exception {
        querySoFar = stringBuilder.append(FROM).append(" ").append(table).append(" ").toString();
        StatementValidator.verifyStatement(querySoFar);
        return this;
    }

    @Override
    public Query condition(String condition) {
        //TODO: create this based on expression & make option for AND or OR situations
        return this;
    }
}
