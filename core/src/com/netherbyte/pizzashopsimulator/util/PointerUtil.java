package com.netherbyte.pizzashopsimulator.util;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.Gdx.input;

public class PointerUtil {
    public static int getX() {
        return input.getX();
    }

    public static int getY() {
        return input.getY();
    }

    public static int getX(Viewport viewport) {
        return (int) (input.getX() - (viewport.getWorldWidth() / 2));
    }

    public static int getY(Viewport viewport) {
        return (int) (input.getY() - (viewport.getWorldHeight() / 2));
    }

    public static boolean isButtonPressed(int button) {
        return input.isButtonPressed(button);
    }

    public static boolean isButtonJustPressed(int button) {
        return input.isButtonJustPressed(button);
    }

    public static boolean isHovering(int objectX, int objectY) {
        return false;
    }

    public static boolean checkHitbox(float minX, float minY, float maxX, float maxY) {
        if (minX < getX() && getX() < maxX) {
            return minY < getY() && getY() < maxY;
        }
        return false;
    }

    public static boolean checkHitbox(float minX, float minY, float maxX, float maxY, Viewport viewport) {
        if (minX < getX(viewport) && getX(viewport) < maxX) {
            return minY < getY(viewport) && getY(viewport) < maxY;
        }
        return false;
    }
}
