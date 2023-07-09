package com.netherbyte.pizzashopsimulator.save;

import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.netherbyte.pizzashopsimulator.SharedConstants.APPLICATION_STORAGE_DIRECTORY;

public class Saves {
    private static final Logger logger = LoggerFactory.getLogger(Saves.class);

    private static Save currentSave;

    public static Save getCurrentSave() {
        return currentSave;
    }

    public static void setCurrentSave(Save currentSave) {
        Saves.currentSave = currentSave;
    }

    public static Save getSaveFromFile(String name) {
        Path path = APPLICATION_STORAGE_DIRECTORY.resolve("saves/").resolve(name + ".dat");
        NamedTag data;
        try {
            data = NBTUtil.read(path.toAbsolutePath().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Save save = new Save();

        return save;
    }
}
