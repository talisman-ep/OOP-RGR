package game.creation;

import game.model.*;

public class ShipFactory {
    public static Ship createShip(ShipType type) {
        switch (type) {
            case TORPEDO_BOAT:
                return new TorpedoBoat();
            case DESTROYER:
                return new Destroyer();
            case CRUISER:
                return new Cruiser();
            case BATTLESHIP:
                return new Battleship();
            default:
                throw new IllegalArgumentException("Unknown ship type: " + type);
        }
    }
}
