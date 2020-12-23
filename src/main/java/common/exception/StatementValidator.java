package common.exception;

import java.util.regex.Pattern;

/**
 * This class basically verifies if the statement is correct based on provided regular expressions
 */

public class StatementValidator {

    /**
     * Samples:
     *
     * select * from table
     * select id from table
     * select id, age, name from table
     * select id2 from table
     * select id2, name from table
     */
    private static String selectionRegex =
            "(SELECT|INSERT|DELETE|UPDATE).([*]|([A-z ]+[0-9]*)+|([A-z ]+[0-9]*[,]+[A-z ]+[0-9]*)+).(FROM).*";

    public static void verifyStatement(String statement) throws Exception{
        if(!Pattern.matches(selectionRegex, statement))
            throw new Exception("Invalid query statement!");
    }
}
