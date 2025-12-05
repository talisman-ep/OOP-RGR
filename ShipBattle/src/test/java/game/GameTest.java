package game;

import game.model.Player;
import game.model.ShotResult;
import game.util.GameLogger;
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


    @Test
    void shouldSwitchToNextPlayer() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Game game = new Game(player1, player2);

        game.switchTurn();

        assertEquals(player2, game.getCurrentPlayer(), "Turn should switch to Player 2");

        game.switchTurn();

        assertEquals(player1, game.getCurrentPlayer(), "Turn should switch back to Player 1");
    }

    @Test
    void shouldReturnCorrectOpponent() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Game game = new Game(player1, player2);

        assertEquals(player1, game.getOpponent(player2));
        assertEquals(player2, game.getOpponent(player1));
    }

    @Test
    void shoulSwitchTurnAfterMiss() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Game game = new Game(player1, player2);

        ShotResult result = game.makeMove();
        assertEquals(ShotResult.MISS, result);
        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    void loggerShouldBeSingleton() {
        GameLogger logger1 = GameLogger.getInstance();
        GameLogger logger2 = GameLogger.getInstance();

        assertSame(logger1, logger2);
    }
}