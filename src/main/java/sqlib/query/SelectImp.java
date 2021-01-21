package sqlib.query;

import common.exception.StatementValidator;
import sqlib.EntityManager;

import static common.util.Constants.FROM;
import static common.util.Constants.SELECT;
import static java.util.stream.IntStream.range;
import static sqlib.EntityManager.createEntityManager;

public class SelectImp implements Select {

    private String querySoFar;
    private final StringBuilder builder;
    private EntityManager entityManager;

    public SelectImp() {
        this.querySoFar = "";
        this.builder = new StringBuilder();
    }

    @Override
    public Select select(String tableName, String... columns) {
        if (columns == null || columns.length == 0)
            throw new RuntimeException("Columns parameter was empty, failed to select data.");
        builder.append(SELECT).append(" ");
        range(0, columns.length).forEach(i -> {
            builder.append(columns[i]);
            if (i != columns.length - 1)
                builder.append(", ");
            else
                builder.append(" ");
        });
        querySoFar = builder.toString();
        addFromClause(tableName);
        return this;
    }

    @Override
    public Select select(Class clazz) {
        entityManager = createEntityManager(clazz);
        if (entityManager.getFields().length == 0)
            throw new RuntimeException("The provided class has no attributes, selection failed!");
        builder.append(SELECT).append(" ");
        range(0, entityManager.getFields().length).forEach(i -> {
            builder.append(entityManager.getFields()[i].getName());
            if (i != entityManager.getFields().length - 1)
                builder.append(", ");
            else
                builder.append(" ");
        });
        querySoFar = builder.toString();
        addFromClause(entityManager.getClassName());
        return this;
    }

    @Override
    public String getQueryString() {
        return querySoFar;
    }

    private void addFromClause(String tableName) {
        querySoFar = builder.append(FROM).append(" ").append(tableName).append(" ").toString();
        StatementValidator.verifyStatement(querySoFar);
    }
}
