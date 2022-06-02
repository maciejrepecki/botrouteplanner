package route.optimizing;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteOptimizerTest {

    @Test
    public void shouldCalculateShortestPathToEveryModuleNodeFromStartPoint (){
        //given
        RouteOptimizer routeOptimizer = new RouteOptimizer();
        ModuleNode moduleNode1 = new ModuleNode(0,0,1);
        ModuleNode moduleNode2 = new ModuleNode(0,1,2);
        ModuleNode moduleNode3 = new ModuleNode(1,0,3);
        ModuleNode moduleNode4 = new ModuleNode(1,1,4);
        GridGraph gridGraph = new GridGraph();
        gridGraph.setX(2);
        gridGraph.setY(2);
        gridGraph.setGridOfModuleNodes();
        Map<ModuleNode,Double> mapForModuleNode1 = Map.of(moduleNode2,2.0,moduleNode3,3.0);
        moduleNode1.setAdjacentModuleNodes(mapForModuleNode1);
        Map<ModuleNode,Double> mapForModuleNode2 = Map.of(moduleNode1,2.0,moduleNode4,4.0);
        moduleNode2.setAdjacentModuleNodes(mapForModuleNode2);
        Map<ModuleNode,Double> mapForModuleNode3 = Map.of(moduleNode1,3.0,moduleNode4,4.0);
        moduleNode3.setAdjacentModuleNodes(mapForModuleNode3);
        Map<ModuleNode,Double> mapForModuleNode4 = Map.of(moduleNode3,4.0,moduleNode2,4.0);
        moduleNode4.setAdjacentModuleNodes(mapForModuleNode4);
        gridGraph.addModuleNode(moduleNode1);
        gridGraph.setGridOfModuleNodes(0,0,moduleNode1);
        gridGraph.addModuleNode(moduleNode2);
        gridGraph.setGridOfModuleNodes(0,1,moduleNode2);
        gridGraph.addModuleNode(moduleNode3);
        gridGraph.setGridOfModuleNodes(1,0,moduleNode3);
        gridGraph.addModuleNode(moduleNode4);
        gridGraph.setGridOfModuleNodes(1,1,moduleNode4);
        //when
        GridGraph resultGridGraph = routeOptimizer.calculateShortestPathFromSource(gridGraph,moduleNode1);
        //then
        assertThat(resultGridGraph.getModuleNode(1,1).getDistance()).isEqualTo(6.0);
    }
}
