package game;

import game.creation.PlayerBuilder;
import game.creation.ShipFactory;
import game.exception.BattleshipGameException;
import game.model.*;
import game.observer.ConsoleGameObserver;
import game.strategy.ConsoleShootingStrategy;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== МОРСЬКИЙ БІЙ ===");
        Player p1 = new PlayerBuilder()
                .setName("Гравець 1 (Людина)")
                .setBoardSize(10, 10)
                .build();
        p1.setStrategy(new ConsoleShootingStrategy());

        Player p2 = new PlayerBuilder()
                .setName("Комп'ютер")
                .setBoardSize(10, 10)
                .build();

        Game game = new Game(p1, p2);

        game.addObserver(new ConsoleGameObserver());

        game.start();

        setupShipsAutomatically(p1);
        setupShipsAutomatically(p2);

        System.out.println("Гра почалася!");

        while (true) {
            Player currentPlayer = game.getCurrentPlayer();
            Player opponent = game.getOpponent(currentPlayer);

            System.out.println("\n--------------------------------");
            System.out.println("Хід гравця: " + currentPlayer.getName());

            System.out.println("Поле супротивника (" + opponent.getName() + "):");
            printBoard(opponent.getBoard(), false);

            ShotResult result = game.makeMove();

            System.out.println("Результат: " + result);

            if (opponent.getBoard().allShipsAreSunk()) {
                System.out.println("\n🎉🎉🎉 ГРА ЗАКІНЧЕНА! 🎉🎉🎉");
                System.out.println("Переміг: " + currentPlayer.getName());
                break;
            }
        }
    }

    private static void printBoard(Board board, boolean showShips) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        int currentY = 0;
        System.out.print("0 ");

        for (Coordinates coord : board) {
            if (coord.y() > currentY) {
                currentY = coord.y();
                System.out.println();
                System.out.print(currentY + " ");
            }

            char symbol = board.getCellStatus(coord.x(), coord.y(), showShips);
            System.out.print(symbol + " ");
        }
        System.out.println();
    }

    private static void setupShipsAutomatically(Player player) {
        Board b = player.getBoard();
        try {
            b.placeShip(ShipFactory.createShip(ShipType.CRUISER), new Coordinates(0, 0), Orientation.HORIZONTAL);
            b.placeShip(ShipFactory.createShip(ShipType.DESTROYER), new Coordinates(5, 5), Orientation.VERTICAL);
            b.placeShip(ShipFactory.createShip(ShipType.TORPEDO_BOAT), new Coordinates(9, 9), Orientation.HORIZONTAL);
        } catch (BattleshipGameException e) {
            System.err.println("Помилка при авто-розстановці кораблів для " + player.getName() + ": " + e.getMessage());
        }
    }
}