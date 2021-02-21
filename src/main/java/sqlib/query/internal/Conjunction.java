package sqlib.query.internal;

public enum Conjunction {
    EQUAL("="),
    GREATER_THAN(">"),
    LESSER_THAN("<"),
    GREATER_EQUAL_THAN(">="),
    LESSER_EQUAL_THAN("<="),
    NOT_EQUAL("<>"),
    LIKE("LIKE"),
    BETWEEN("BETWEEN"),
    AND("AND"),
    OR("OR");

    public final String value;

    Conjunction(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }
}
