package exceptions;

public class NoAvailableRoomException extends Exception {
    public NoAvailableRoomException(String message) {
        super(message);
    }
}
