package com.netherbyte.pizzashopsimulator.util;

import com.badlogic.gdx.graphics.Texture;
import com.netherbyte.pizzashopsimulator.block.Blocks;
import com.netherbyte.pizzashopsimulator.client.resource.AssetProvider;
import com.netherbyte.pizzashopsimulator.registry.Registries;

import static com.netherbyte.pizzashopsimulator.SharedConstants.DEFAULT_HEIGHT;

public class OvenManager {
    public static boolean isOvenJustClicked(int i) {
        Texture oven = AssetProvider.getTexture(Registries.BLOCKS.getIdentifier(Blocks.OVEN));

        return PointerUtil.checkHitbox(
                oven.getWidth() * (i - 1),
                DEFAULT_HEIGHT,
                oven.getWidth() * (i),
                DEFAULT_HEIGHT - oven.getHeight()
        ) && PointerUtil.isButtonJustPressed(0);
    }
}
