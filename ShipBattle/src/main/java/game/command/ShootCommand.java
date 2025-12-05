package game.command;

import game.model.Coordinates;
import game.model.Player;
import game.model.ShotResult;

public class ShootCommand implements Command {
    private final Player attacker;
    private final Player target;
    private final Coordinates coords;
    private ShotResult result; // Тут зберігаємо результат

    // Конструктор приймає РІВНО 3 аргументи
    public ShootCommand(Player attacker, Player target, Coordinates coords) {
        this.attacker = attacker;
        this.target = target;
        this.coords = coords;
    }

    @Override
    public void execute() {
        // Виконуємо постріл і зберігаємо результат
        result = attacker.getShot(target, coords);
    }

    // Цей метод ОБОВ'ЯЗКОВО має бути, щоб Game міг дізнатися результат
    public ShotResult getResult() {
        return result;
    }
}