package criteria;

public interface Selection {

    Selection select(String... columns);

    Selection select(Class clazz);

    String getQueryString();
}
