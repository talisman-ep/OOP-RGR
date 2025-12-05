package game.strategy;

import game.model.Board;
import game.model.Coordinates;

import java.util.Scanner;

public class ConsoleShootingStrategy implements ShootingStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Coordinates chooseTarget(Board enemyBoard) {
        System.out.print("Введіть координати пострілу (X Y): ");
        while (true) {
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                return new Coordinates(x, y);
            } catch (Exception e) {
                System.out.println("Некоректне введення! Спробуйте ще раз.");
                scanner.next();
            }
        }
    }
}
