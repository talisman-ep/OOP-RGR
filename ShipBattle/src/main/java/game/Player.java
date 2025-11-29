package game;

public class Player {

    private final String name;
    private final Board board;

    public Player(String name) {
        this.name = name;
        this.board = new Board(10, 10);
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
