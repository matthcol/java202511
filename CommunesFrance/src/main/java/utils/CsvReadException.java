package utils;

public class CsvReadException extends RuntimeException {
    public CsvReadException() {
    }

    public CsvReadException(String message) {
        super(message);
    }

    public CsvReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvReadException(Throwable cause) {
        super(cause);
    }
}
