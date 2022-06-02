package storage.domain.moduleTypes;

public class ModuleTypeH extends ModuleType {

    private static final String name = "H";
    private static final String description = "High speed transit - optimized";
    private static final double passingTime = 0.5;

    public ModuleTypeH() {
        super(name, description, passingTime);
    }

    @Override
    public double getRemovingTime(int n) {
        return 3 * n + 4;
    }
}
