package game.exception;

public class ShipPlacementException extends InvalidCoordinateException {
    public ShipPlacementException(String message) {
        super(message);
    }
}
