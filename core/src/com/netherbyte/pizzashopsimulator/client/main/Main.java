package com.netherbyte.pizzashopsimulator.client.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.netherbyte.pizzashopsimulator.PizzaShopSimulatorVersion;
import com.netherbyte.pizzashopsimulator.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netherbyte.pizzashopsimulator.SharedConstants.NAME;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static boolean init() {
		logger.info("Starting " + NAME + " " + PizzaShopSimulatorVersion.CURRENT.getName());

		Items.register();

		logger.info("Initialization stage complete");
		return true;
	}
}
