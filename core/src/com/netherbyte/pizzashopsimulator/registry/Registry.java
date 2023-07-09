package com.netherbyte.pizzashopsimulator.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Registry {
    private static final Logger logger = LoggerFactory.getLogger(Registry.class);

    public static <T> void register(SimpleRegistry<T> registry, Identifier identifier, T object) {
        logger.info("Adding registry object: " + identifier.toString());
        registry.add(identifier, object);
    }
}
