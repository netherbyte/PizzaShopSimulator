package com.netherbyte.pizzashopsimulator.client.main;

import com.netherbyte.pizzashopsimulator.PizzaShopSimulatorVersion;
import com.netherbyte.pizzashopsimulator.client.block.Blocks;
import com.netherbyte.pizzashopsimulator.item.Items;
import com.netherbyte.pizzashopsimulator.registry.Identifier;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.registry.SimpleRegistry;
import com.netherbyte.pizzashopsimulator.resource.AssetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netherbyte.pizzashopsimulator.SharedConstants.NAME;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static boolean init() {
		logger.info("Starting " + NAME + " " + PizzaShopSimulatorVersion.CURRENT.getName());

		Registries.init();
		Items.register();
		Blocks.register();



		logger.info("Initialization stage complete");
		return true;
	}

	public static void postInit() {
		logger.info("Beginning post initialization stage");

		for (SimpleRegistry registry : Registries.REGISTRIES) {
			var ids = registry.getIdentifierList();
			for (int i = 0; i < ids.size(); i++) {
				Identifier id = (Identifier) ids.get(i);
				if (AssetProvider.isMissing(id)) logger.warn("Missing texture: " + id + " (Not checking for block states)");
			}
		}

		logger.info("Post initialization stage complete");
	}
}
