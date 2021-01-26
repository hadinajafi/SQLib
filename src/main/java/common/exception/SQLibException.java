package common.exception;

import static common.util.Constants.*;

public class SQLibException extends RuntimeException {

    private String message;
    private String query;

    public SQLibException(String message) {
        this.message = message;
    }

    public SQLibException(String str, boolean isMessageQuery) {
        if (isMessageQuery)
            this.query = str;
        else
            message = str;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getSQLMessage() {
        return messageGenerator();
    }

    private String messageGenerator() {
        if (!query.contains("SELECT") && !query.contains("UPDATE") && !query.contains("DELETE") &&
                !query.contains("INSERT"))
            message = MISSING_SELECTION_PROCEDURES;
        else if (!query.contains("FROM"))
            message = MISSING_FROM_PROCEDURE;
        else
            message = INVALID_QUERY;
        return message;
    }
}
