package game;

public class Ship {
    private final int size;
    private static int hits = 0;

    public Ship(int size) {
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
