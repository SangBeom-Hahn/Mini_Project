package hello.ex.exception;

public class NoSuchIdPlzReTryException extends Exception{
    public NoSuchIdPlzReTryException(String message) {
        super(message);
    }
}
