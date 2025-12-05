package game;

import game.creation.ShipFactory;
import game.exception.InvalidCoordinateException;
import game.exception.ShipPlacementException;
import game.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void boardShouldBeCreatedWithCorrectDimensions() {
        var board = new Board(10, 10);

        assertEquals(10, board.getWidth());
        assertEquals(10, board.getHeight());
    }

    @Test
    void shipIsOnTheRightPlace() {
        var board = new Board(10, 10);
        // Використовуємо фабрику (якщо ти вже реалізував її)
        var ship = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        var coordinates = new Coordinates(0, 0);

        assertDoesNotThrow(() -> board.placeShip(ship, coordinates, Orientation.HORIZONTAL));
    }

    @Test
    void shouldThrowExceptionWhenPlacingShipOutsideTheBoard() {
        var board = new Board(10, 10);
        var ship = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        Coordinates outOfBoundsCoordinate = new Coordinates(10, 10);
        assertThrows(InvalidCoordinateException.class, () ->
                board.placeShip(ship, outOfBoundsCoordinate, Orientation.HORIZONTAL)
        );
    }

    @Test
    void shouldThrowExceptionWhenPlacingTwoShipsOnTheSameCoordinate() {
        var board = new Board(10, 10);
        var ship1 = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        var coordinates = new Coordinates(5, 5);

        assertDoesNotThrow(() -> board.placeShip(ship1, coordinates, Orientation.HORIZONTAL));

        var ship2 = ShipFactory.createShip(ShipType.TORPEDO_BOAT);

        assertThrows(ShipPlacementException.class, () ->
                board.placeShip(ship2, coordinates, Orientation.HORIZONTAL)
        );
    }

    @Test
    void shouldThrowExceptionWhenShipsTouch() {
        var board = new Board(10, 10);
        var ship1 = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        var coordinates = new Coordinates(5, 5);

        board.placeShip(ship1, coordinates, Orientation.HORIZONTAL);

        var ship2 = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        var diagonalCoordinates = new Coordinates(6, 6);

        assertThrows(ShipPlacementException.class, () ->
                board.placeShip(ship2, diagonalCoordinates, Orientation.HORIZONTAL)
        );
    }

    @Test
    void shouldAllowPlacingMultiDeckShipHorizontally() {
        var board = new Board(10, 10);
        var ship = ShipFactory.createShip(ShipType.DESTROYER);
        var startCoordinates = new Coordinates(2, 2);

        assertDoesNotThrow(() ->
                board.placeShip(ship, startCoordinates, Orientation.HORIZONTAL)
        );
    }

    @Test
    void shoulResultInMissWhenFiringAtEmptyPosition() {
        var board = new Board(10, 10);
        var emptyCoordinate = new Coordinates(5, 5);

        ShotResult result = board.fireAT(emptyCoordinate);
        assertEquals(ShotResult.MISS, result);
    }

    @Test
    void shoulResultInSunkWhenLastPartOfShipIsHit() {
        var board = new Board(10, 10);
        var singleDeckShip = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        var coordinates = new Coordinates(3, 3);

        board.placeShip(singleDeckShip, coordinates, Orientation.HORIZONTAL);

        ShotResult result = board.fireAT(coordinates);

        assertEquals(ShotResult.SUNK, result);
    }

    @Test
    void shoulReportGameIsOverWhenAllShipsSunk() {
        var board = new Board(10, 10);
        var ship = ShipFactory.createShip(ShipType.TORPEDO_BOAT);
        var coordinates = new Coordinates(3, 3);

        board.placeShip(ship, coordinates, Orientation.HORIZONTAL);
        board.fireAT(coordinates);

        assertTrue(board.allShipsAreSunk());
    }
}