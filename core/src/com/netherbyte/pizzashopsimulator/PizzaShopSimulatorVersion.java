package com.netherbyte.pizzashopsimulator;

import com.netherbyte.svlib.Channel;
import com.netherbyte.svlib.Version;

public class PizzaShopSimulatorVersion {
    public static final PizzaShopSimulatorVersion CURRENT = new PizzaShopSimulatorVersion();

    private final String version;
    private final int saveVersion;
    private final int dataVersion;
    private final int assetVersion;
    private final int uuid;
    private final boolean stable;

    private PizzaShopSimulatorVersion() {
        this.version = "1.1.0-dev.1";
        this.saveVersion = 2;
        this.dataVersion = 1;
        this.assetVersion = 1;
        this.uuid = 2;
        this.stable = true;
    }

    public String getName() {
        return version;
    }

    public String getVersion() {
        return version;
    }

    public int getSaveVersion() {
        return saveVersion;
    }

    public int getDataVersion() {
        return dataVersion;
    }

    public int getAssetVersion() {
        return assetVersion;
    }

    public int getUuid() { return uuid; }

    public boolean isStable() {
        return stable;
    }
}
