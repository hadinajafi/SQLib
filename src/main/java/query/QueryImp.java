package query;

import static common.exception.ExceptionMessageBuilder.messageBuilder;
import static common.util.Constants.*;

public class QueryImp implements Query {

    private String querySoFar;
    EntityManager<?> em;

    private final StringBuilder stringBuilder;

    public QueryImp() {
        querySoFar = "";
        stringBuilder = new StringBuilder();
        stringBuilder.append(querySoFar);
    }

    /**
     * @return Query string so far created by builder
     */
    @Override
    public String getQueryString() {
        return querySoFar;
    }

    /**
     * @return This
     */
    @Override
    public QueryImp select() throws Exception {
        if (querySoFar.contains(SELECT))
            throw new Exception(messageBuilder(WRONG_ORDER, SELECT));
        querySoFar = stringBuilder.append(SELECT).append(" ").toString();
        return this;
    }

    @Override
    public QueryImp all() throws Exception {
        if (!querySoFar.contains(SELECT))
            throw new Exception(messageBuilder(IS_MISSING, SELECT));
        querySoFar = stringBuilder.append("* ").toString();
        return this;
    }

    /**
     * @param table Required table name
     * @return This
     */
    @Override
    public QueryImp from(String table) throws Exception {
        if (table == null || table.equals(""))
            throw new Exception(REQ_PARAM_MISSING);
        if (!querySoFar.contains(SELECT))
            throw new Exception(messageBuilder(STATEMENT_BEFORE_ANOTHER, FROM, SELECT));
//        if (this.getSelectedColumns() == null)
//            throw new Exception(NO_COLUMN_SELECTED);
        querySoFar = stringBuilder.append(FROM).append(" ").append(table).append(" ").toString();
        return this;
    }

    @Override
    public Query fieldsOf(Class<?> clazz) throws Exception {
        if (!querySoFar.contains(SELECT))
            throw new Exception(messageBuilder(IS_MISSING, SELECT));
        em = new EntityManager<>(clazz);
        for(int i = 0; i < em.getFields().length; i++){
            querySoFar = stringBuilder.append(em.getFields()[i].getName()).toString();
            if( i != em.getFields().length - 1)
                querySoFar = stringBuilder.append(", ").toString();
            else
                querySoFar = stringBuilder.append(" ").toString();
        }
        return this;
    }

//    private String getSelectedColumns() {
//        if (querySoFar.contains("*"))
//            return "*";
//        return null;
//    }
}
