package common.exception;

public class ExceptionMessageBuilder {
    public static String messageBuilder(String message, String param1, String param2) {
        return String.format(message, param1, param2);
    }

    public static String messageBuilder(String message, String param){
        return String.format(message, param);
    }


}
