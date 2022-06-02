package storage.domain;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private int x;
    private int y;
    private int n;
    private Module[][] gridOfModules;
    private List<Product> listOfProducts;

    public Grid() {
    }

    public void setGridModule(int x, int y, Module module) {
        this.gridOfModules[x][y] = module;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getN() {
        return n;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void addProduct(Product product) {
        listOfProducts.add(product);
    }

    public Module getModule(int x, int y) {
        return gridOfModules[x][y];
    }

    public void initGridOfModules() {
        this.gridOfModules = new Module[this.x][this.y];
    }

    public void initListOfProducts() {
        this.listOfProducts = new ArrayList<>();
    }


}
