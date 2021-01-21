package sqlib.query;

public interface Select {

    Select select(String table, String... columns);

    Select select(Class clazz);

    String getQueryString();
}
