package com.netherbyte.pizzashopsimulator.util;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import static com.badlogic.gdx.Gdx.input;

public class PointerUtil {
    public static int getX() {
        return input.getX();
    }

    public static int getY() {
        return input.getY();
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
}
