package sqlib.persistence;

import sqlib.query.Insert;

/**
 * author: Hadi Najafi
 */

public class Session {
    private Insert insert;

    public Session() {
        this.insert = new Insert();
    }

    /**
     * @param tableName Insert values into the table with the name of <b>tableName</b>
     * @param values    Values that will save into the table. number of values must be same as table columns
     * @throws PersistenceException Exception could happen through inserting
     */
    public void insert(String tableName, Object... values) throws PersistenceException {
        //TODO: CHECK open
        insert.insertInto(tableName, StringifyValues(values));
    }

    public void insert(String table, String[] columns, Object[] values) throws PersistenceException {
        //TODO: check open
        insert.insertInto(table, columns, StringifyValues(values));
    }

    public String queryString() {
        return insert.getQueryString();
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
