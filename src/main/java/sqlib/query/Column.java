package sqlib.query;

/**
 * author: Hadi Najafi
 */

public class Column {

    private String name;
    private String type;

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Column(String name){
        this.name = name;
    }

    /**
     * @return Column name
     */
    public String getName() {
        return name;
    }

    public static Column set(String name, String type) {
        return new Column(name, type);
    }

    public String getType() {
        return type;
    }
}
