public class NoJokeException extends Exception {
    Exception cause;

    NoJokeException(Exception cause){
        this.cause = cause;
    }
}
