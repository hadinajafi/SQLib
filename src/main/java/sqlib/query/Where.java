package sqlib.query;

import common.util.Constants;

/**
 * author: Hadi Najafi
 */

public class Where {

    public static String where(String compoundPredicate) {
        return Constants.WHERE + " " + compoundPredicate;
    }
}
