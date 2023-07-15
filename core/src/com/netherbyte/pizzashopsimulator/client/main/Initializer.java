package com.netherbyte.pizzashopsimulator.client.main;

import com.netherbyte.pizzashopsimulator.PizzaShopSimulatorVersion;
import com.netherbyte.pizzashopsimulator.block.Blocks;
import com.netherbyte.pizzashopsimulator.item.Items;
import com.netherbyte.pizzashopsimulator.registry.Identifier;
import com.netherbyte.pizzashopsimulator.registry.Registries;
import com.netherbyte.pizzashopsimulator.registry.SimpleRegistry;
import com.netherbyte.pizzashopsimulator.client.resource.AssetProvider;
import com.netherbyte.pizzashopsimulator.util.OSUtil;
import com.netherbyte.svlib.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netherbyte.pizzashopsimulator.SharedConstants.ID;
import static com.netherbyte.pizzashopsimulator.SharedConstants.NAME;

public class Initializer {
	private static final Logger logger = LoggerFactory.getLogger(Initializer.class);

	public static boolean init() {
		logger.info("Starting " + NAME + " " + PizzaShopSimulatorVersion.CURRENT.getName());

		if (OSUtil.OS_NAME.contains("win")) {
			if (!Version.Math.isCompatible(OSUtil.OSVERREQ_WIN, OSUtil.OS_VERSION)) {
				throw new InitializationException("Operating system is incompatible. This game requires Windows " + OSUtil.OSVERREQ_WIN.getFormattedName() + ". You are using Windows " + OSUtil.OS_VERSION.getFormattedName());
			}
		} else if (OSUtil.OS_NAME.contains("mac")) {
			if (!Version.Math.isCompatible(OSUtil.OSVERREQ_MAC, OSUtil.OS_VERSION)) {
				throw new InitializationException("Operating system is incompatible. This game requires MacOS " + OSUtil.OSVERREQ_MAC.getFormattedName() + ". You are using MacOS " + OSUtil.OS_VERSION.getFormattedName());
			}
		} else if (OSUtil.OS_NAME.contains("linux")) {
			if (Version.Math.isCompatible(OSUtil.OSVERREQ_LIN, OSUtil.OS_VERSION)) {
				throw new InitializationException("Operating system is incompatible. This game requires Linux Kernel " + OSUtil.OSVERREQ_LIN.getFormattedName() + ". You are using Linux Kernel " + OSUtil.OS_VERSION.getFormattedName());
			}
		} else {
			throw new InitializationException("Unsupported operating system: " + OSUtil.OS_NAME);
		}

		Registries.init();
		Items.register();
		Blocks.register();

		logger.info("Initialization stage complete");
		return true;
	}

	public static void postInit() {
		logger.info("Beginning post initialization stage");

		for (SimpleRegistry<?> registry : Registries.REGISTRIES) {
			var ids = registry.getIdentifierList();
			for (Identifier identifier : ids) {
				if (AssetProvider.isTextureMissing(identifier))
					logger.warn("Missing texture: " + identifier + " (Not checking for block states)");
			}
		}

		if (AssetProvider.isFontMissing(new Identifier(ID, "default"))) {
			throw new PostInitializationException("Default font is missing");
		}

		logger.info("Post initialization stage complete");
	}
}
