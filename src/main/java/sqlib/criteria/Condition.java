package sqlib.criteria;

import common.exception.SQLibException;
import sqlib.query.Column;

import static common.util.Constants.PARAM_MISSING;

public class Condition {
    private final StringBuilder builder = new StringBuilder();
    private String clause = "";

    public Condition() {

    }

    /**
     * @param conjunction The conjunction parameter like AND & OR
     * @param parameters  The parameters wanted to combine with the provided conjunction
     * @return This to combine multiple conjunctions, like combining AND & OR
     */
    public Condition createPredicateClause(Conjunction conjunction, Object... parameters) {
        checkParametersNullability(parameters);
        clause = clauseGenerator(conjunction, parameters);
        return this;
    }

    private void checkParametersNullability(Object... objects) {
        for (Object o : objects) {
            if (o == null) {
                throw new SQLibException(PARAM_MISSING);
            }
        }
    }

    private String clauseGenerator(Conjunction conjunction, Object... parameters) {
        String clause = "";
        String[] stringParameters = stringifyParameters(parameters);
        for (int i = 0; i < stringParameters.length; i++) {
            if (i == stringParameters.length - 1)
                clause += stringParameters[i];
            else
                clause += stringParameters[i] + " " + conjunction.toString() + " ";
        }
        return clause;
    }

    private String[] stringifyParameters(Object[] parameters) {
        String[] stringified = new String[parameters.length];
        {
        }
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] instanceof String && !(parameters[i] instanceof Predicate))
                stringified[i] = applyTypePreference((String) parameters[i]);
            else if (parameters[i] instanceof Column)
                stringified[i] = ((Column) parameters[i]).getName();
            else
                stringified[i] = String.valueOf(parameters[i]);
        }
        return stringified;
    }

    private String applyTypePreference(String parameter) {
        return String.format("'%s'", parameter);
    }

    /**
     * @return generated condition string
     */
    public String getConditionString() {
        return clause;
    }
}
