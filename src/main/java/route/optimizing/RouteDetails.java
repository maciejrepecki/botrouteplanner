package route.optimizing;

import storage.domain.Product;

import java.util.LinkedList;

public class RouteDetails {

    private final Product product;
    private double passingTimeToProduct;
    private double passingTimeToStorage;
    private double removingTime;
    private double totalTime;
    private final LinkedList<ModuleNode> shortestPathFromStartToEnd;

    public RouteDetails(Product product) {
        this.product = product;
        this.shortestPathFromStartToEnd = new LinkedList<>();
    }

    public void setPassingTimeToProduct(double passingTimeToProduct) {
        this.passingTimeToProduct = passingTimeToProduct;
    }

    public void setPassingTimeToStorage(double passingTimeToStorage) {
        this.passingTimeToStorage = passingTimeToStorage;
    }

    public void setRemovingTime(double removingTime) {
        this.removingTime = removingTime;
    }

    public void setTotalTime() {
        this.totalTime = this.passingTimeToProduct + this.passingTimeToStorage + this.removingTime;
    }

    public LinkedList<ModuleNode> getShortestPathFromStartToEnd() {
        return shortestPathFromStartToEnd;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void addPath(LinkedList<ModuleNode> path) {
        shortestPathFromStartToEnd.addAll(path);
    }

    public void addModuleNode(ModuleNode moduleNodeOfStorage) {
        shortestPathFromStartToEnd.add(moduleNodeOfStorage);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(shortestPathFromStartToEnd.size() - 1);
        stringBuilder.append("\n");
        stringBuilder.append(Math.round(totalTime * 10) / 10.0);
        stringBuilder.append("\n");
        for (ModuleNode moduleNode : shortestPathFromStartToEnd
        ) {
            stringBuilder.append(moduleNode.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}


