package com.netherbyte.pizzashopsimulator.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.netherbyte.pizzashopsimulator.client.resource.AssetProvider;
import com.netherbyte.pizzashopsimulator.registry.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.netherbyte.pizzashopsimulator.SharedConstants.ID;

public class Sounds {
    private static final Logger logger = LoggerFactory.getLogger(Sounds.class);
    public static final List<Sound> LIST = new ArrayList<>();

    public static Sound OVEN_TIMER_DONE;

    public static void register() {
        logger.info("Registering sounds");

        try {
            OVEN_TIMER_DONE = Gdx.audio.newSound(AssetProvider.getSound(new Identifier(ID, "oven/timer_done")));
        } catch (GdxRuntimeException e) {
            logger.warn("Missing sound(s) were found");
        }

        LIST.add(OVEN_TIMER_DONE);
    }
}
