package common.exception;

import static common.util.Constants.*;

public class InvalidQueryException extends RuntimeException{

    private String message;
    private String query;

    public InvalidQueryException(String query) {
        this.query = query;
    }

    @Override
    public String getMessage() {
        return messageGenerator();
    }

    private String messageGenerator() {
        if(!query.contains("SELECT") && !query.contains("UPDATE") && !query.contains("DELETE") &&
                !query.contains("INSERT"))
            message = MISSING_SELECTION_PROCEDURES;
        else if(!query.contains("FROM"))
            message = MISSING_FROM_PROCEDURE;
        else
            message = INVALID_QUERY;
        return message;
    }
}
