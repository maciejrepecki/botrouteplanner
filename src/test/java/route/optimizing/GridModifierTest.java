package route.optimizing;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class GridModifierTest {

    @Test
    public void shouldReturnRouteDetailsForTheShortestRoute() {
        //given
        GridModifier gridModifier = new GridModifier("src/grid-test1.txt", "src/job-test1.txt");
        //when
        RouteDetails bestRoute = gridModifier.getBestRoute();
        //then
        assertThat(bestRoute).isInstanceOf(RouteDetails.class);
        assertThat(bestRoute.getTotalTime()).isEqualTo(10.5);
        assertThat(bestRoute.getShortestPathFromStartToEnd()).hasSize(9);
        assertThat(bestRoute.getProduct().getName()).isEqualTo("P1");
        assertThat(bestRoute.getProduct().getX()).isEqualTo(3);
        assertThat(bestRoute.getProduct().getY()).isEqualTo(2);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGridSizeIsOutOfBound() {
        //given
        String gridFileName = "src/grid-test2.txt";
        String jobFileName = "src/job-test2.txt";
        //when
        Throwable thrown = catchThrowable(() -> {
            new GridModifier(gridFileName, jobFileName);
        });
        //then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Value out of limit!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenJobEndParameterIsOutOfBound() {
        //given
        String gridFileName = "src/grid-test3.txt";
        String jobFileName = "src/job-test3.txt";
        //when
        Throwable thrown = catchThrowable(() -> {
            new GridModifier(gridFileName, jobFileName);
        });
        //then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Value out of limit!");
    }

    @Test
    public void shouldThrowProductNotFoundExceptionWhenJobProductIsAbsentInGrid() {
        //given
        String gridFileName = "src/grid-test4.txt";
        String jobFileName = "src/job-test4.txt";
        GridModifier gridModifier = new GridModifier(gridFileName, jobFileName);
        //when
        Throwable thrown = catchThrowable(() -> {
            gridModifier.findProductsFromGrid(gridModifier.getGrid(), gridModifier.getJob());
        });
        //then
        assertThat(thrown).isInstanceOf(ProductNotFoundException.class);
    }
}