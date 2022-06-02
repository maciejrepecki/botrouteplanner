package storage.domain.moduleTypes;

public class ModuleTypeO extends ModuleType {

    private static final String name = "O";
    private static final String description = "Out of service";
    private static final double passingTime = Double.MAX_VALUE;

    public ModuleTypeO() {
        super(name, description, passingTime);
    }

    @Override
    public double getRemovingTime(int n) {
        return 0;
    }
}
