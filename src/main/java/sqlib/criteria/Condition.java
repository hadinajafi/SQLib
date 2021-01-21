package sqlib.criteria;

import sqlib.Column;

import java.util.Arrays;
import java.util.Objects;

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
    public Condition createPredicateClause(String conjunction, Object... parameters) {
        checkParametersNullability(conjunction, parameters);
        clause = builder.append(clauseGenerator(conjunction, parameters)).toString();
        return this;
    }

    private void checkParametersNullability(Object... objects) {
        Arrays.stream(objects).filter(Objects::isNull).forEach(o -> {
            throw new NullPointerException(PARAM_MISSING);
        });
    }

    private String clauseGenerator(String conjunction, Object... parameters) {
        String clause = "";
        String[] stringParameters = stringifyParameters(parameters);
        for (int i = 0; i < stringParameters.length; i++) {
            if (i == stringParameters.length - 1)
                clause += stringParameters[i];
            else
                clause += stringParameters[i] + " " + conjunction + " ";
            clause.concat(" ");
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
