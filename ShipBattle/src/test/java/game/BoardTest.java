package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void boardShouldBeCreatedWithCorrectDimensions() {
        var board = new Board(10, 10);

        assertEquals (10, board.getWidth());
        assertEquals (10, board.getHeight());
    }

    @Test
    void shipIsOnTheRigntPlace() {
        var board = new Board(10, 10);

        var ship = new Ship(1);
        var coordinates = new Coordinates(0, 0);

        boolean result = board.placeShip(ship, coordinates, Orientation.HORIZONTAL);

        assertTrue(result);
    }

    @Test
    void shouldNotAllowPlacingShipOutsideTheBoard() {
        var board = new Board(10, 10);
        var ship = new Ship(1);
        Coordinates outOfBoundsCoordinate = new Coordinates(10, 10);

        boolean result = board.placeShip(ship, outOfBoundsCoordinate, Orientation.HORIZONTAL);

        assertFalse(result);
    }

    @Test
    void shouldNotAllowPlacingTwoShipsOnTheSameCoordinate() {
        var board = new Board(10, 10);
        var ship = new Ship(1);
        var coordinates = new Coordinates(5, 5);

        board.placeShip(ship, coordinates, Orientation.HORIZONTAL);

        var secondShip = new Ship(1);
        boolean result = board.placeShip(secondShip, coordinates, Orientation.HORIZONTAL);

        assertFalse(result);
    }

    @Test
    void shouldNotTouchesShipsWithAngles() {
        var board = new Board(10, 10);
        var ship = new Ship(1);
        var coordinates = new Coordinates(5, 5);

        board.placeShip(ship, coordinates, Orientation.HORIZONTAL);

        var secondShip = new Ship(1);
        var diagonalCoordinates = new Coordinates(5, 5);
        boolean result = board.placeShip(secondShip, diagonalCoordinates, Orientation.HORIZONTAL);

        assertFalse(result);
    }

    @Test
    void shouldAllowPlacingMultiDeckShipHorizontally(){
        var board = new Board(10, 10);
        var ship = new Ship(2);
        var startCoordinates = new Coordinates(2, 2);

        Orientation orientation = Orientation.HORIZONTAL;

        boolean result = board.placeShip(ship, startCoordinates, orientation);
        assertTrue(result);
    }

    @Test
    void shoulResultInMissWhenFiringAtEmptyPosition(){
        var board = new Board(10, 10);
        var emptyCoordinate = new Coordinates(5, 5);

        ShotResult result = board.fireAT(emptyCoordinate);
        assertEquals(ShotResult.MISS, result);
    }

    @Test
    void shoulResultInSunkWhenLastPartOfShipIsHit(){
        var board = new Board(10, 10);
        var singledeckShip = new Ship(1);
        var coordinates = new Coordinates(3, 3);

        board.placeShip(singledeckShip, coordinates, Orientation.HORIZONTAL);

        ShotResult result = board.fireAT(coordinates);

        assertEquals(ShotResult.SUNK, result);
    }

    @Test
    void shoulReportGameIsOverWhenAllShipsSunk(){
        var board = new Board(10, 10);
        var ship = new Ship(1);
        var coordinates = new Coordinates(3, 3);
        board.placeShip(ship, coordinates, Orientation.HORIZONTAL);

        board.fireAT(coordinates);

        assertTrue(board.allShipsAreSunk());
    }
}