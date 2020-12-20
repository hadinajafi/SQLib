package query;

public interface Query {

    Query select() throws Exception;

    Query from(String table) throws Exception;

    Query all() throws Exception;

    Query fieldsOf(Class<?> clazz) throws Exception;

    String getQueryString();
}
