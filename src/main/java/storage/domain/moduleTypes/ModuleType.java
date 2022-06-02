package storage.domain.moduleTypes;

public abstract class ModuleType {

    private final String name;
    private final String description;
    private final double passingTime;

    public ModuleType(String name, String description, double passingTime) {
        this.name = name;
        this.description = description;
        this.passingTime = passingTime;
    }

    public String getName() {
        return name;
    }

    public double getPassingTime() {
        return passingTime;
    }

    public abstract double getRemovingTime(int n);

}
