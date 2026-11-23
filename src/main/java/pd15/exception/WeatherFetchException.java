package pd15.exception;

public class WeatherFetchException extends RuntimeException {
    public WeatherFetchException(String message) {
        super(message);
    }

    public WeatherFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
