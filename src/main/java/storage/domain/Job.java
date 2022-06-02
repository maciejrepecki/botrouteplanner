package storage.domain;

public class Job {

    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    private final String productName;

    public Job(int startX, int startY, int endX, int endY, String productName) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.productName = productName;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public String getProductName() {
        return productName;
    }

}
