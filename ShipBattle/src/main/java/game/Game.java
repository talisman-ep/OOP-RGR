package game;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player curentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.curentPlayer = player1;
    }

    public Player getCurrentPlayer() {
        return curentPlayer;
    }
}
