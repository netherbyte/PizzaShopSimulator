package com.netherbyte.pizzashopsimulator;

import com.netherbyte.pizzashopsimulator.util.OSUtil;

import java.nio.file.Path;

public class SharedConstants {
    public static final String NAME = "Pizza Shop Simulator";
    public static final String ID = "pizzashopsimulator";

    public static final int DEFAULT_WIDTH = 1280;
    public static final int DEFAULT_HEIGHT = 720;

    public static final Path APPLICATION_STORAGE_DIRECTORY = Path.of(OSUtil.getApplicationStorageDirectory());
}
