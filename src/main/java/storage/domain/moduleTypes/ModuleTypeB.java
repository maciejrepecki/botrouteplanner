package storage.domain.moduleTypes;

public class ModuleTypeB extends ModuleType {

    private static final String name = "B";
    private static final String description = "Balanced";
    private static final double passingTime = 1;

    public ModuleTypeB() {
        super(name, description, passingTime);
    }

    @Override
    public double getRemovingTime(int n) {
        return 2 * n + 2;
    }
}
