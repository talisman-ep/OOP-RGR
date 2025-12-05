package game.model;

public abstract class Ship {
    private final int size;
    private int hits = 0;

    protected Ship(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void hit() {
        if (hits < size){
            hits ++;
        }
    }

    public boolean isSunk() {
        return hits >= size;
    }
}
