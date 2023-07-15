package com.netherbyte.pizzashopsimulator.client.main;

import com.badlogic.gdx.audio.Sound;
import com.netherbyte.pizzashopsimulator.sound.Sounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shutdown {
    private static final Logger logger = LoggerFactory.getLogger(Shutdown.class);

    public static void shutdown() {
        logger.info("Shutting down");

        logger.info("Cleaning up sounds");
        for (int i = 0; i < Sounds.LIST.size(); i++) {
            Sound sound = Sounds.LIST.get(i);
            logger.info("Disposing " + sound.toString());
            sound.dispose();
        }

        logger.info("Game has been shut down");
    }
}
