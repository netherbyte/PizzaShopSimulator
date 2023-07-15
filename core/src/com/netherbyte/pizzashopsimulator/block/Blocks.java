package com.netherbyte.pizzashopsimulator.block;

import com.netherbyte.pizzashopsimulator.registry.Identifier;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netherbyte.pizzashopsimulator.SharedConstants.ID;

public class Blocks {
    private static final Logger logger = LoggerFactory.getLogger(Blocks.class);

    public static final Block OVEN = new Block();

    public static void register() {
        logger.info("Registering blocks");

        Registry.register(Registries.BLOCKS, new Identifier(ID, "oven"), OVEN);
    }
}
