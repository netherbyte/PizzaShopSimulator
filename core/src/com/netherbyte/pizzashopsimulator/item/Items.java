package com.netherbyte.pizzashopsimulator.item;

import com.netherbyte.pizzashopsimulator.registry.Identifier;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netherbyte.pizzashopsimulator.SharedConstants.ID;

public class Items {
    private static final Logger logger = LoggerFactory.getLogger(Items.class);

    public static final Ingredient DOUGH = new Dough();
    public static final Ingredient SAUCE = new Sauce();
    public static final Ingredient CHEESE = new Cheese();
    public static final Ingredient PEPPERONI = new Pepperoni();

    public static void register() {
        logger.info("Registering items");

        Registry.register(Registries.INGREDIENTS, new Identifier(ID, "dough"), DOUGH);
        Registry.register(Registries.INGREDIENTS, new Identifier(ID, "sauce"), SAUCE);
        Registry.register(Registries.INGREDIENTS, new Identifier(ID, "cheese"), CHEESE);
        Registry.register(Registries.INGREDIENTS, new Identifier(ID, "pepperoni"), PEPPERONI);
    }
}
