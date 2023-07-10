package com.netherbyte.pizzashopsimulator.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PosUtil {
    public static Vector2 center(Viewport viewport, float width, float height) {
        return new Vector2(
                (viewport.getWorldWidth() / 2) - (width / 2),
                (viewport.getWorldHeight() / 2) - (height / 2)
        );
    }
}
