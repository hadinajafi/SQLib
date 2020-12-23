package criteria;

import query.EntityManager;

import static common.util.Constants.SELECT;
import static java.util.stream.IntStream.range;
import static query.EntityManager.createEntityManager;

public class SelectionImp implements Selection {

    private String querySoFar;
    private StringBuilder builder;
    private EntityManager entityManager;

    public SelectionImp() {
        this.querySoFar = "";
        this.builder = new StringBuilder();
    }

    @Override
    public Selection select(String... columns) {
        builder.append(SELECT).append(" ");
        range(0, columns.length).forEach(i -> {
            builder.append(columns[i]);
            if (i != columns.length - 1)
                builder.append(", ");
            else
                builder.append(" ");
        });
        querySoFar = builder.toString();
        return this;
    }

    @Override
    public Selection select(Class clazz) {
        entityManager = createEntityManager(clazz);
        builder.append(SELECT).append(" ");
        range(0, entityManager.getFields().length).forEach(i -> {
            builder.append(entityManager.getFields()[i].getName()).toString();
            if (i != entityManager.getFields().length - 1)
                builder.append(", ").toString();
            else
                builder.append(" ").toString();
        });
        querySoFar = builder.toString();
        return this;
    }

    @Override
    public String getQueryString() {
        return querySoFar;
    }
}
