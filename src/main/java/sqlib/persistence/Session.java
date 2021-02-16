package sqlib.persistence;

import sqlib.criteria.CriteriaQuery;
import sqlib.query.Insert;
import sqlib.query.Update;
import sqlib.query.Where;

/**
 * author: Hadi Najafi
 */

public class Session {
    private Insert insert;
    private Update update;
    private String queryStatement = "";

    public Session() {
        insert = new Insert();
        update = new Update();
    }

    /**
     * @param tableName Insert values into the table with the name of <b>tableName</b>
     * @param values    Values that will save into the table. number of values must be same as table columns
     * @throws PersistenceException Exception could happen through inserting
     */
    public void insert(String tableName, Object... values) throws PersistenceException {
        //TODO: CHECK open
        insert.insertInto(tableName, StringifyValues(values));
        queryStatement = insert.getQueryString();
    }

    /**
     *
     * @param tableName Insert values into table with this name.
     * @param columns only selected columns will affected.
     * @param values values must have the same length as columns.
     * @throws PersistenceException Exception could happen through inserting
     */
    public void insert(String tableName, String[] columns, Object[] values) throws PersistenceException {
        //TODO: check open
        insert.insertInto(tableName, columns, StringifyValues(values));
        queryStatement = update.getQueryString();
    }

    /**
     *
     * @param tableName Update values into table with this name.
     * @param columns only selected columns will be affected.
     * @param values values must have the same length as columns.
     * @throws PersistenceException Exception could happen through inserting
     */
    public Session update(String tableName, String[] columns, Object[] values) throws PersistenceException{
        update.update(tableName, columns, StringifyValues(values));
        queryStatement = update.getQueryString();
        return this;
    }

    public Session where(String compoundPredicate) {
        queryStatement += Where.where(compoundPredicate);
        return this;
    }

    public String getQueryString() {
        return queryStatement;
    }

    private String[] StringifyValues(Object[] values) {
        String[] valuesAsString = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof String)
                valuesAsString[i] = String.format("'%s'", values[i]);
            else
                valuesAsString[i] = String.valueOf(values[i]);
        }
        return valuesAsString;
    }

}
