package game.model;

import game.strategy.RandomShootingStrategy;
import game.strategy.ShootingStrategy;

public class Player {

    private final String name;
    private final Board board;
    private ShootingStrategy strategy;

    public Player(String name, int boardWidth, int boardHeight) {
        this.name = name;
        this.board = new Board(boardWidth, boardHeight);
        this.strategy = new RandomShootingStrategy();
    }

    public Player(String name) {
        this.name = name;
        this.board = new Board(10, 10);
    }

    public void setStrategy(ShootingStrategy strategy) {
        this.strategy = strategy;
    }

    public Coordinates makeDecision(Board enemyBoard) {
        return strategy.chooseTarget(enemyBoard);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public ShotResult getShot(Player opponent, Coordinates coordinates) {
        return opponent.getBoard().fireAT(coordinates);
    }

}
