package route.optimizing;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class RouteOptimizer {

    public GridGraph calculateShortestPathFromSource(GridGraph gridGraph, ModuleNode source) {

        source.setDistance(0.0);

        Set<ModuleNode> settledModuleNodes = new HashSet<>();
        Set<ModuleNode> unsettledModuleNodes = new HashSet<>();

        unsettledModuleNodes.add(source);

        while (unsettledModuleNodes.size() != 0) {
            ModuleNode currentModuleNode = getLowestDistanceModuleNode(unsettledModuleNodes);
            unsettledModuleNodes.remove(currentModuleNode);
            for (Map.Entry<ModuleNode, Double> adjacencyPair :
                    currentModuleNode.getAdjacentModuleNodes().entrySet()) {
                ModuleNode adjacentModuleNode = adjacencyPair.getKey();
                Double edgeWeight = adjacencyPair.getValue();
                if (!settledModuleNodes.contains(adjacentModuleNode)) {
                    calculateMinimumDistance(adjacentModuleNode, edgeWeight, currentModuleNode);
                    unsettledModuleNodes.add(adjacentModuleNode);
                }
            }
            settledModuleNodes.add(currentModuleNode);
        }
        return gridGraph;
    }

    private ModuleNode getLowestDistanceModuleNode(Set<ModuleNode> unsettledModuleNodes) {
        ModuleNode lowestDistanceModuleNode = null;
        double lowestDistance = Double.MAX_VALUE;
        for (ModuleNode moduleNode : unsettledModuleNodes) {
            double moduleNodeDistance = moduleNode.getDistance();
            if (moduleNodeDistance <= lowestDistance) {
                lowestDistance = moduleNodeDistance;
                lowestDistanceModuleNode = moduleNode;
            }
        }
        return lowestDistanceModuleNode;
    }

    private static void calculateMinimumDistance(ModuleNode evaluationNode,
                                                 Double edgeWeigh, ModuleNode sourceModuleNode) {
        Double sourceDistance = sourceModuleNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<ModuleNode> shortestPath = new LinkedList<>(sourceModuleNode.getShortestPath());
            shortestPath.add(sourceModuleNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

}