package storage.domain;

public class Product {

    private final String name;
    private final int x;
    private final int y;
    private final int n;

    public Product(String name, int x, int y, int n) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y;
    }
}
