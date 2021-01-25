package sqlib.query;

import sqlib.criteria.Result;

public interface Select {

    Select select(String table, String... columns);

    Select select(Result clazz);

    String getQueryString();
}
