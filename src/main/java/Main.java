import route.optimizing.GridModifier;
import route.optimizing.RouteDetails;

public class Main {
    public static void main(String[] args) {

        String gridFileName = args[0];
        String jobFileName = args[1];
        GridModifier gridModifier = new GridModifier(gridFileName, jobFileName);
        RouteDetails result = gridModifier.getBestRoute();
        System.out.println(result);
    }
}
