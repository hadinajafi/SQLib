package common.util;

public class Constants {
//    Errors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static String IS_MISSING = "%s statement is missing!";
    public static String NO_COLUMN_SELECTED = "No column selected!";
    public static String PARAM_MISSING = "Required parameter is missing!";
    public static String STATEMENT_BEFORE_ANOTHER = "%s statement can't come before %s statement!";
    public static String WRONG_ORDER = "%s statement used in wrong order!";
    public static String MISSING_SELECTION_PROCEDURES = "Query is missing SELECT|UPDATE|INSERT|DELETE procedures";
    public static String MISSING_FROM_PROCEDURE = "Query is missing FROM procedure";
    public static String INVALID_QUERY = "Query is Invalid!";

    //    Query ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static String SELECT = "SELECT";
    public static String FROM = "FROM";
    public static String WHERE = "WHERE";

    //    Predicate ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static String EQUAL = "=";
    public static String GREATER_THAN = ">";
    public static String LESSER_THAN = "<";
    public static String GREATER_EQUAL_THAN = ">=";
    public static String LESSER_EQUAL_THAN = "<=";
    public static String NOT_EQUAL = "<>";
    public static String LIKE = "LIKE";
    public static String BETWEEN = "BETWEEN";
    public static String AND = "AND";
    public static String OR = "OR";
}
