package game.observer;

import game.model.Coordinates;
import game.model.Player;
import game.model.ShotResult;
import game.util.GameLogger;

public class ConsoleGameObserver implements GameObserver {
    @Override
    public void onGameStart() {
        System.out.println("=== ГРА ПОЧАЛАСЯ ===");
    }

    @Override
    public void onShot(Player shooter, Coordinates coordinates, ShotResult result) {
        System.out.println(shooter.getName() + " стріляє по " + coordinates + " -> " + result);

        GameLogger.getInstance().log(shooter.getName() + " shot at " + coordinates + ": " + result);
    }

    @Override
    public void onGameOver(Player winner) {
        System.out.println("\n🎉🎉🎉 ГРА ЗАКІНЧЕНА! Переміг: " + winner.getName());
        GameLogger.getInstance().log("Game Over. Winner: " + winner.getName());
    }
}
