package sqlib;

/**
 * author: Hadi Najafi
 */

public class Column {

    private String name;

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Column set(String name) {
        return new Column(name);
    }
}
