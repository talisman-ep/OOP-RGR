package game.observer;

import game.model.Coordinates;
import game.model.Player;
import game.model.ShotResult;

public interface GameObserver {
    void onGameStart();
    void onShot(Player shooter, Coordinates coordinates, ShotResult result);
    void onGameOver(Player winner);
}
