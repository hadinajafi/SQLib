package sqlib.query.internal;

import common.util.SQLConstants;

public class Having {
    public static String having(String compoundPredicate) {
        return SQLConstants.HAVING + " " + compoundPredicate;
        //TODO: prevent SQL injection here
    }
}
