package sqlib.query;

public interface Select {

    Select select(String... columns);

    Select select(Class clazz);

    String getQueryString();
}
