package zl5;

public class OperationResult<T> {
    private final boolean success;
    private final T value;
    private final String message;

    OperationResult(boolean success, T value, String message) {
        this.success = success;
        this.value = value;
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static <T> OperationResult<T> success(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null!");
        }
        return new OperationResult<>(true, value, null);
    }

    public static <T> OperationResult<T> failure(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be empty!");
        }
        return new OperationResult<>(false, null, message);
    }
}