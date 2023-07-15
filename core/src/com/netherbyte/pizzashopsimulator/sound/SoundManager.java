package com.netherbyte.pizzashopsimulator.sound;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.netherbyte.pizzashopsimulator.registry.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundManager {
    private static final Logger logger = LoggerFactory.getLogger(SoundManager.class);

    public static void play(Sound sound, float volume, float pitch, float pan) {
        try {
            sound.play(volume, pitch, pan);
            sound.dispose();
        } catch (Exception e) {
            logger.warn("Missing sound found");
        }
    }
}
