public class NoJokeException extends Exception {
    Exception cause;
    String message;

    NoJokeException(Exception cause){
        this.cause = cause;
    }
    NoJokeException(String message) {
        this.message = message;
    }
}
