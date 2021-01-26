package common.exception;

import java.util.regex.Pattern;

/**
 * This class basically verifies if the statement is correct based on provided regular expressions
 */

public class StatementValidator {

    /**
     * Samples:
     * <p>
     * select * from table
     * select id from table
     * select id, age, name from table
     * select id2 from table
     * select id2, name from table
     */
    private final static String selectionRegex =
            "(SELECT|UPDATE|INSERT|DELETE) ((\\w+[,]|\\w+[,] )+\\w+|\\w+|\\w+[0-9]+|[*]) (FROM).*";

    public static void verifyStatement(String statement){
        if(!Pattern.matches(selectionRegex, statement))
            throw new SQLibException(statement, true);
    }
}
