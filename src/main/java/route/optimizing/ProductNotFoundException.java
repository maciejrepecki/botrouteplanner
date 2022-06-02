package route.optimizing;

public class ProductNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Product not found in grid!";
    }
}
