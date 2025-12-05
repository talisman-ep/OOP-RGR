package game.strategy;

import game.model.Board;
import game.model.Coordinates;

public interface ShootingStrategy {
    Coordinates chooseTarget(Board enemyBoard);
}
