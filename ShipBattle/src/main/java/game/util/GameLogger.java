package game.util;

import java.util.ArrayList;
import java.util.List;

public class GameLogger {
    private static GameLogger instance;

    private final List<String> logs = new ArrayList<>();

    private GameLogger() {}

    public static GameLogger getInstance() {
        if (instance == null) {
            instance = new GameLogger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
        System.out.println("[LOG]: " + message);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
