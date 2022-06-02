package route.optimizing;

import storage.domain.Grid;
import storage.domain.Job;
import storage.domain.Module;
import storage.domain.Product;
import storage.domain.moduleTypes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GridModifier {

    private final Grid grid;
    private final Job job;

    public GridModifier(String gridFileName, String jobFileName) {
        this.grid = readGridFromFile(gridFileName);
        this.job = readingJobFromFile(jobFileName);
    }

    public Grid getGrid() {
        return grid;
    }

    public Job getJob() {
        return job;
    }

    public RouteDetails getBestRoute() {
        List<RouteDetails> routes = getRoutes(this.grid, this.job);
        return routes.stream()
                .min(Comparator.comparingDouble(RouteDetails::getTotalTime))
                .orElse(null);
    }

    private GridGraph makeGraphFromGrid(Grid grid) {
        GridGraph gridGraph = new GridGraph();
        gridGraph.setX(grid.getX());
        gridGraph.setY(grid.getY());
        gridGraph.setGridOfModuleNodes();
        for (int j = 0; j < grid.getY(); j++) {
            for (int i = 0; i < grid.getX(); i++) {
                var module = grid.getModule(i, j);
                var passingTime = module.getPassingTime();
                var moduleNode = new ModuleNode(i, j, passingTime);
                gridGraph.setGridModuleNode(i, j, moduleNode);
                gridGraph.addModuleNode(moduleNode);
            }
        }
        addDestinations(gridGraph);
        return gridGraph;
    }

    private void addDestinations(GridGraph gridGraph) {
        int boundaryX = gridGraph.getX() - 1;
        int boundaryY = gridGraph.getY() - 1;
        for (int j = 0; j <= boundaryY; j++) {
            for (int i = 0; i <= boundaryX; i++) {
                if (i != 0) {
                    ModuleNode source = gridGraph.getModuleNode(i, j);
                    addDestinationToSource(gridGraph, source, i - 1, j);
                }
                if (j != 0) {
                    ModuleNode source = gridGraph.getModuleNode(i, j);
                    addDestinationToSource(gridGraph, source, i, j - 1);
                }
                if (i != boundaryX) {
                    ModuleNode source = gridGraph.getModuleNode(i, j);
                    addDestinationToSource(gridGraph, source, i + 1, j);
                }
                if (j != boundaryY) {
                    ModuleNode source = gridGraph.getModuleNode(i, j);
                    addDestinationToSource(gridGraph, source, i, j + 1);
                }
            }
        }
    }

    private void addDestinationToSource(GridGraph gridGraph, ModuleNode source, int x, int y) {
        ModuleNode destination = gridGraph.getModuleNode(x, y);
        double passingTime = Math.max(source.getPassingTime(), destination.getPassingTime());
        source.addDestination(gridGraph.getModuleNode(x, y), passingTime);
    }

    private List<RouteDetails> getRoutes(Grid grid, Job job) {
        var startX = job.getStartX();
        var startY = job.getStartY();
        var endX = job.getEndX();
        var endY = job.getEndY();
        var listOfProducts = findProductsFromGrid(grid, job);
        List<RouteDetails> routeDetailsList = new ArrayList<>();

        for (Product product : listOfProducts) {
            RouteDetails routeDetails = new RouteDetails(product);
            int productX = product.getX();
            int productY = product.getY();

            var gridGraph = calculateShortestPath(grid, startX, startY);
            setRouteDetailsFromStartToProduct(productX, productY, gridGraph, routeDetails);

            gridGraph = calculateShortestPath(grid, productX, productY);
            setRouteDetailsFromProductToStorage(endX, endY, gridGraph, routeDetails);

            routeDetails.setRemovingTime(removingTime(grid, product));
            routeDetails.setTotalTime();
            routeDetailsList.add(routeDetails);
        }
        return routeDetailsList;
    }

    protected GridGraph calculateShortestPath(Grid grid, int x, int y) {
        var routeOptimizer = new RouteOptimizer();
        var gridGraph = makeGraphFromGrid(grid);
        var moduleNode = gridGraph.getModuleNode(x, y);
        return routeOptimizer.calculateShortestPathFromSource(gridGraph, moduleNode);
    }

    private void setRouteDetailsFromProductToStorage(int x, int y, GridGraph gridGraph, RouteDetails routeDetails) {
        var moduleNode = gridGraph.getModuleNode(x, y);
        routeDetails.setPassingTimeToStorage(getDistanceAndSetPath(routeDetails, moduleNode));
        routeDetails.addModuleNode(moduleNode);
    }

    private void setRouteDetailsFromStartToProduct(int x, int y, GridGraph gridGraph, RouteDetails routeDetails) {
        var moduleNode = gridGraph.getModuleNode(x, y);
        routeDetails.setPassingTimeToProduct(getDistanceAndSetPath(routeDetails, moduleNode));
    }

    private double getDistanceAndSetPath(RouteDetails routeDetails, ModuleNode moduleNode) {
        var shortestPath = moduleNode.getShortestPath();
        routeDetails.addPath(shortestPath);
        return moduleNode.getDistance();
    }

    protected List<Product> findProductsFromGrid(Grid grid, Job job) {
        var productName = job.getProductName();
        var listOfProducts = grid.getListOfProducts();
        List<Product> productList = listOfProducts
                .stream()
                .filter(product -> product.getName().equals(productName))
                .toList();
        if (productList.isEmpty()) throw new ProductNotFoundException();
        return productList;
    }

    private double removingTime(Grid grid, Product product) {
        var x = product.getX();
        var y = product.getY();
        var n = product.getN();
        var module = grid.getModule(x, y);
        return module.getRemovingTime(n);
    }

    private void readGridInfo(BufferedReader reader, Grid grid) throws IOException {
        String line = reader.readLine();
        String[] lineTab = line.split(" ");
        grid.setX(checkLimitations(0,101,Integer.parseInt(lineTab[0])));
        grid.setY(checkLimitations(0,101,Integer.parseInt(lineTab[1])));
        grid.setN(checkLimitations(0,10,Integer.parseInt(lineTab[2])));
        grid.initGridOfModules();
        grid.initListOfProducts();
    }

    private int checkLimitations (int limitA, int limitB, int value){
        if (value < limitA || value > limitB) {
            throw new IllegalArgumentException("Value out of limit!");
        }
        return value;
    }

    private void readModuleTypes(BufferedReader reader, Grid grid) throws IOException {

        ModuleType moduleTypeH = new ModuleTypeH();
        ModuleType moduleTypeB = new ModuleTypeB();
        ModuleType moduleTypeO = new ModuleTypeO();
        ModuleType moduleTypeS = new ModuleTypeS();

        for (int i = 0; i < grid.getY(); i++) {
            String line = reader.readLine();
            String[] lineTab = line.split("");
            for (int j = 0; j < grid.getX(); j++) {
                String moduleTypeLetter = lineTab[j];
                ModuleType moduleType = switch (moduleTypeLetter) {
                    case "H" -> moduleTypeH;
                    case "B" -> moduleTypeB;
                    case "O" -> moduleTypeO;
                    case "S" -> moduleTypeS;
                    default -> throw new IllegalArgumentException("Wrong type of module!");
                };
                Module module = new Module(j, i, grid.getN(), moduleType);
                grid.setGridModule(j, i, module);
            }
        }
    }

    private void readProducts(BufferedReader reader, Grid grid) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] productLine = line.split(" ");
            String productName = productLine[0];
            int productX = checkLimitations(0, grid.getX(), Integer.parseInt(productLine[1]));
            int productY = checkLimitations(0, grid.getY(), Integer.parseInt(productLine[2]));
            int productN = checkLimitations(0, grid.getN(), Integer.parseInt(productLine[3]));
            Product product = new Product(productName, productX, productY, productN);
            grid.addProduct(product);
        }
    }

    private Grid readGridFromFile(String fileName) {
        Grid grid = new Grid();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            readGridInfo(reader, grid);
            readModuleTypes(reader, grid);
            readProducts(reader, grid);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return grid;
    }

    private Job readingJobFromFile(String fileName) {
        Job job = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            String[] lineTab = line.split(" ");
            int startX = checkLimitations(0, grid.getX(), Integer.parseInt(lineTab[0]));
            int startY = checkLimitations(0, grid.getY(), Integer.parseInt(lineTab[1]));
            line = reader.readLine();
            lineTab = line.split(" ");
            int endX = checkLimitations(0, grid.getX(), Integer.parseInt(lineTab[0]));
            int endY = checkLimitations(0, grid.getY(), Integer.parseInt(lineTab[1]));
            line = reader.readLine();
            String productName = line;
            job = new Job(startX, startY, endX, endY, productName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return job;
    }
}
