package game.creation;

import game.model.Player;

public class PlayerBuilder {
    private String name;
    private int boardWidth = 10;
    private int boardHeight = 10;

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder setBoardSize(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        return this;
    }

    public Player build() {
        if (name == null) {
            name = "Player-" + (int)(Math.random() * 1000);
        }
        return new Player(name, boardWidth, boardHeight);
    }
}
