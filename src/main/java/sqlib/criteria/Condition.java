package sqlib.criteria;

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
                clause += stringParameters[i] + " ";
            else
                clause += stringParameters[i] + " " + conjunction + " ";
        }
        return clause;
    }

    private String[] stringifyParameters(Object[] parameters) {
        return Arrays.stream(parameters).map(String::valueOf).toArray(String[]::new);
    }

    /**
     * @return generated condition string
     */
    public String getConditionString() {
        return clause;
    }
}
