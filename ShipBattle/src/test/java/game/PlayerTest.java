package game;

import game.model.Board;
import game.model.Coordinates;
import game.model.Player;
import game.model.ShotResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void shoulCreatePlayerWithCorrectName() {
        Player player = new Player("Player 1");

        assertEquals("Player 1", player.getName());
    }

    @Test
    void playerShouldHaveABoard() {
        Player player = new Player("Player 1");
        Board playerboard = player.getBoard();

        assertNotNull(playerboard);
    }

    @Test
    void playerShouldBeAbleToShotOpponentBoard() {
        Player attacker = new Player("Player 1");
        Player opponent = new Player("Player 2");
        Coordinates target = new Coordinates(5, 5);

        ShotResult result = attacker.getShot(opponent, target);

    assertEquals(ShotResult.MISS, result);
    }

}