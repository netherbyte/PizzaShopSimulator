package com.netherbyte.pizzashopsimulator.client.main;

import com.netherbyte.pizzashopsimulator.PizzaShopSimulatorVersion;

import static com.netherbyte.pizzashopsimulator.SharedConstants.NAME;

public class DesktopLauncherConfig {
    public static final DesktopLauncherConfig CONFIG = new DesktopLauncherConfig();

    public int fps() {
        return 60;
    }

    public boolean vsync() {
        return true;
    }

    public String title() {
        if (PizzaShopSimulatorVersion.CURRENT.isStable()) {
            return NAME + " " + PizzaShopSimulatorVersion.CURRENT.getName();
        } else {
            return NAME + " " + PizzaShopSimulatorVersion.CURRENT.getName() + " (UNSTABLE)";
        }
    }

    public int width() {
        return 1280;
    }

    public int height() {
        return 720;
    }
}
