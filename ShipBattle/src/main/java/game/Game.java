package game;

import game.command.ShootCommand;
import game.model.Coordinates;
import game.model.Player;
import game.model.ShotResult;
import game.observer.GameObserver;
import game.util.GameLogger;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player curentPlayer;
    private final List<GameObserver> observers = new ArrayList<>();

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.curentPlayer = player1;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public Player getCurrentPlayer() {
        return curentPlayer;
    }

    public void start() {
        for (GameObserver obs : observers) {
            obs.onGameStart();
        }
    }

    public void switchTurn() {
        if (curentPlayer == player1) {
            curentPlayer = player2;
        }
        else {
            curentPlayer = player1;
        }
    }

    public Player getOpponent(Player player) {
        if (player == player1) {
            return player2;
        }
        return player1;
    }

    public ShotResult makeMove() {
        Player opponent = getOpponent(curentPlayer);

        Coordinates target = curentPlayer.makeDecision(opponent.getBoard());

        GameLogger.getInstance().log(curentPlayer.getName() + " стріляє по " + target);

        ShootCommand shootCommand = new ShootCommand(curentPlayer, opponent, target);

        shootCommand.execute();

        ShotResult result = shootCommand.getResult();

        GameLogger.getInstance().log("Результат: " + result);

        if (result == ShotResult.MISS) {
            GameLogger.getInstance().log("Хід переходить до іншого гравця");
            switchTurn();
        } else if (result == ShotResult.SUNK) {
            GameLogger.getInstance().log("Корабель потоплено!");
        }

        return result;
    }

}
