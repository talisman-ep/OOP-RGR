package game.strategy;

import game.model.Board;
import game.model.Coordinates;

import java.util.Random;

public class RandomShootingStrategy implements ShootingStrategy {
    private final Random random = new Random();

    @Override
    public Coordinates chooseTarget(Board enemyBoard) {
        int x = random.nextInt(enemyBoard.getWidth());
        int y = random.nextInt(enemyBoard.getHeight());
        return new Coordinates(x, y);
    }
}
