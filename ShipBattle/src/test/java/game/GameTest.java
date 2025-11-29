package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void shoulStartGameWithTwoPlayers() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        Game game = new Game(player1, player2);

        assertEquals(player1, game.getCurrentPlayer());
    }
}