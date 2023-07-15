package com.netherbyte.pizzashopsimulator.save;

import java.util.ArrayList;
import java.util.List;
import com.netherbyte.pizzashopsimulator.item.Pizza;

public class Save {
    private int sales;
    private float cash;
    private List<Pizza> pendingSales;

    public Save() {
        sales = 0;
        cash = 0f;
        pendingSales = new ArrayList<>();
    }

    public int getSales() {
        return sales;
    }

    public void addSale() {
        this.sales++;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public float getCash() {
        return cash;
    }

    public void addCash(float cash) {
        this.cash += cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public List<Pizza> getPendingSales() {
        return pendingSales;
    }

    public void addPendingSale(Pizza pendingSale) {
        this.pendingSales.add(pendingSale);
    }

    public int getPendingSalesCount() {
        return pendingSales.size();
    }
}
