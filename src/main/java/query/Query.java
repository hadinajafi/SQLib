package query;

import criteria.Selection;

public interface Query {

    Query select(String[] columns);

    Query select(Class clazz);

    Query from(String table) throws Exception;

    String getQueryString() throws Exception;

    Query condition(String condition);
}
