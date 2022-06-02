package route.optimizing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModuleNode {

    private final int x;
    private final int y;
    private final double passingTime;
    private LinkedList<ModuleNode> shortestPath = new LinkedList<>();
    private Double distance = Double.MAX_VALUE;
    Map<ModuleNode,Double> adjacentModuleNodes = new HashMap<>();

    public void addDestination(ModuleNode destination, double distance) {
        adjacentModuleNodes.put(destination, distance);
    }

    public ModuleNode(int x, int y, double passingTime) {
        this.x = x;
        this.y = y;
        this.passingTime = passingTime;
    }

    public LinkedList<ModuleNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<ModuleNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<ModuleNode,Double> getAdjacentModuleNodes() {
        return adjacentModuleNodes;
    }

    public void setAdjacentModuleNodes(Map<ModuleNode, Double> adjacentModuleNodes) {
        this.adjacentModuleNodes = adjacentModuleNodes;
    }

    public double getPassingTime() {
        return passingTime;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
