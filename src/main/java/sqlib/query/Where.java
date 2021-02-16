package sqlib.query;

import common.util.SQLConstants;

public class Where {
    public static String where(String compoundPredicate) {
        return SQLConstants.WHERE + " " + compoundPredicate;
    }
}
