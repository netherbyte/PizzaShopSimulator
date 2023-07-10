package com.netherbyte.pizzashopsimulator.client.block;

import com.netherbyte.pizzashopsimulator.registry.Identifier;
import com.netherbyte.pizzashopsimulator.registry.Registries;

public class BlockState {
    public static Identifier ofBlock(Block block, String state) {
        Identifier id = Registries.BLOCKS.getIdentifier(block);
        return new Identifier(id.group, id.key + "_" + state);
    }
}
