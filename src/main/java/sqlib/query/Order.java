package sqlib.query;

public enum Order {
    ASC("ASC"),
    DESC("DESC");

    public final String order;

    Order(String order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return order;
    }
}
