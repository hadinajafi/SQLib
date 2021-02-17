package sqlib.query;

public enum Options {

    DISTINCT("DISTINCT");

    public final String option;

    Options(String value) {
        this.option = value;
    }


    @Override
    public String toString() {
        return option;
    }
}
