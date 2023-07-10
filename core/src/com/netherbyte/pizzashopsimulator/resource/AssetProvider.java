package com.netherbyte.pizzashopsimulator.resource;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.netherbyte.pizzashopsimulator.registry.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AssetProvider {
    private static final Logger logger = LoggerFactory.getLogger(AssetProvider.class);

    public static Texture getTexture(Identifier identifier) {
        try {
            return new Texture("assets/" + identifier.group + "/textures/" + identifier.key + ".png");
        } catch (GdxRuntimeException e) {

            Pixmap pixmap = new Pixmap(16, 16, Pixmap.Format.RGB888);
            pixmap.setColor(Color.BLACK);
            pixmap.fill();

            pixmap.setColor(1f, 0f, 1f, 1f);
            pixmap.drawRectangle(0, 0, 8, 8);
            pixmap.drawRectangle(8, 8, 8, 8);

            return new Texture(pixmap);
        }
    }

    public static boolean isMissing(Identifier identifier) {
        try {
            new Texture("assets/" + identifier.group + "/textures/" + identifier.key + ".png");
        } catch (GdxRuntimeException e) {
            return true;
        }
        return false;
    }
}
