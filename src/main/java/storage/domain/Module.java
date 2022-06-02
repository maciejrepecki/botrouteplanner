package storage.domain;

import storage.domain.moduleTypes.ModuleType;

public class Module {

    private final int x;
    private final int y;
    private final int n;
    private ModuleType moduleType;

    public Module(int x, int y, int n, ModuleType moduleType) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.moduleType = moduleType;
    }

    public String getName() {
        return moduleType.getName();
    }

    public double getPassingTime() {
        return moduleType.getPassingTime();
    }

    public double getRemovingTime(int n) {
        return moduleType.getRemovingTime(n);
    }
}
