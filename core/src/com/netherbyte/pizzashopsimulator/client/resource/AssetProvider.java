package com.netherbyte.pizzashopsimulator.client.resource;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.netherbyte.pizzashopsimulator.registry.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.badlogic.gdx.Gdx.files;

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

    public static BitmapFont getFont(Identifier identifier) {
        try {
            return new BitmapFont(files.internal("assets/" + identifier.group + "/font/" + identifier.key + ".fnt"));
        } catch (GdxRuntimeException e) {
            return new BitmapFont();
        }
    }

    public static boolean isTextureMissing(Identifier identifier) {
        try {
            new Texture("assets/" + identifier.group + "/textures/" + identifier.key + ".png");
        } catch (GdxRuntimeException e) {
            return true;
        }
        return false;
    }

    public static boolean isFontMissing(Identifier identifier) {
        try {
            new BitmapFont(files.internal("assets/" + identifier.group + "/font/" + identifier.key + ".fnt"));
        } catch (GdxRuntimeException e) {
            return true;
        }
        return false;
    }
}
