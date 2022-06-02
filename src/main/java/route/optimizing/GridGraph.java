package route.optimizing;
import java.util.HashSet;
import java.util.Set;

public class GridGraph {

    private int x;
    private int y;
    private ModuleNode[][] gridOfModuleNodes;
    private final Set<ModuleNode> moduleNodes = new HashSet<>();

    public void setGridOfModuleNodes() {
        this.gridOfModuleNodes = new ModuleNode[x][y];
    }

    public void addModuleNode(ModuleNode moduleNode) {
        moduleNodes.add(moduleNode);
    }

    public void setGridModuleNode (int x, int y, ModuleNode moduleNode){
        this.gridOfModuleNodes[x][y] = moduleNode;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGridOfModuleNodes(int x, int y, ModuleNode moduleNode) {
        this.gridOfModuleNodes[x][y] = moduleNode;
    }

    public ModuleNode getModuleNode(int x, int y) {
        return gridOfModuleNodes[x][y];
    }

}
