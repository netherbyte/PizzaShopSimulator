package com.netherbyte.pizzashopsimulator.registry;

import com.netherbyte.pizzashopsimulator.client.block.Block;
import com.netherbyte.pizzashopsimulator.item.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Registries {
    private static final Logger logger = LoggerFactory.getLogger(Registries.class);

    public static final List<SimpleRegistry> REGISTRIES = new ArrayList<>();

    public static final SimpleRegistry<Ingredient> INGREDIENTS = new SimpleRegistry<>();
    public static final SimpleRegistry<Block> BLOCKS = new SimpleRegistry<>();

    public static void init() {
        logger.info("Initializing registries");

        REGISTRIES.add(INGREDIENTS);
        REGISTRIES.add(BLOCKS);
    }
}
