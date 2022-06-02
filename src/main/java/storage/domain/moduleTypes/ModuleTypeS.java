package storage.domain.moduleTypes;

public class ModuleTypeS extends ModuleType {

    private static final String name = "S";
    private static final String description = "Storage access time - optimized";
    private static final double passingTime = 2;

    public ModuleTypeS() {
        super(name, description, passingTime);
    }

    @Override
    public double getRemovingTime(int n) {
        return n + 1;
    }
}
