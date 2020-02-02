/**
 * GitException for record exception in git operations
 */
public final class GitException extends RuntimeException{

    public GitException (String error, Throwable cause) {
        super(error, cause);
    }

    public GitException(String error) {
        super(error);
    }

    public GitException(Throwable cause) {
        super(cause);
    }
}
