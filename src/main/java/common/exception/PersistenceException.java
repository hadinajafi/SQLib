package common.exception;

public class PersistenceException extends SQLibException{

    public PersistenceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
