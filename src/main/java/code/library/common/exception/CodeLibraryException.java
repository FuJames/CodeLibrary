package code.library.common.exception;

/**
 * Created by fuqianzhong on 17/9/12.
 */
public class CodeLibraryException extends RuntimeException {

    public CodeLibraryException() {
        super();
    }

    public CodeLibraryException(String message) {
        super(message);
    }

    public CodeLibraryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeLibraryException(Throwable cause) {
        super(cause);
    }
}
