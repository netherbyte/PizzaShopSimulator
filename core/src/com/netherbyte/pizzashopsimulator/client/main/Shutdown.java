package com.netherbyte.pizzashopsimulator.client.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shutdown {
    private static final Logger logger = LoggerFactory.getLogger(Shutdown.class);

    public static void shutdown() {
        logger.info("Shutting down");

        logger.info("Game has been shut down");
    }
}
