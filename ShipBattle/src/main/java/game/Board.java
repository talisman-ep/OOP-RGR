package game;

import java.util.*;

public class Board {

    private final int width;
    private final int height;
    private final Map<Coordinates, Ship> shipsByCoordinates = new HashMap<>();
    private final Set<Coordinates> firedShots = new HashSet<>();
    private final List<Ship> allShips = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean placeShip(Ship ship, Coordinates startCoordinate, Orientation orientation) {
        List<Coordinates> shipCoordinates = new ArrayList<>();
        for (int i = 0; i < ship.getSize(); i++) {
            if (orientation == Orientation.HORIZONTAL) {
                shipCoordinates.add(new Coordinates(startCoordinate.x() + i, startCoordinate.y()));
            } else {
                shipCoordinates.add(new Coordinates(startCoordinate.x(), startCoordinate.y() + i));
            }
        }
        for (Coordinates coord : shipCoordinates) {
            if (coord.x() < 0 || coord.x() >= width || coord.y() < 0 || coord.y() >= height) {
                return false;
            }
            if (neighborOccupied(coord)) {
                return false;
            }
        }

        allShips.add(ship);
        for (Coordinates coord : shipCoordinates) {
            shipsByCoordinates.put(coord, ship);
        }

        return true;
    }

    public boolean neighborOccupied(Coordinates coordinates) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Coordinates neigbor = new Coordinates(coordinates.x() + dx, coordinates.y() + dy);
                if (shipsByCoordinates.containsKey(neigbor)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ShotResult fireAT(Coordinates coordinates) {
        if (firedShots.contains(coordinates)) {
            // Можна додати логіку для повторного пострілу, але поки що залишимо так
        }
        firedShots.add(coordinates);

        if (shipsByCoordinates.containsKey(coordinates)) {
            Ship hitShip = shipsByCoordinates.get(coordinates);
            hitShip.hit();

            if (hitShip.isSunk()) {
                return ShotResult.SUNK;
            } else {
                return ShotResult.HIT;
            }
        } else {
            return ShotResult.MISS;
        }
    }

    public boolean allShipsAreSunk() {
        if(allShips.isEmpty()) {
            return false;
        }
        return allShips.stream().allMatch(Ship::isSunk);
    }

}
