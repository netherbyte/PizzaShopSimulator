package com.netherbyte.pizzashopsimulator.client.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.netherbyte.pizzashopsimulator.client.GuiManager;
import org.lwjgl.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.badlogic.gdx.Version.VERSION;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class Desktop {
	private static final Logger logger = LoggerFactory.getLogger(Desktop.class);

	public static void main (String[] arg) {
		logger.info("Creating new window");
		logger.info("Using LWJGL " + Version.getVersion());
		logger.info("Using LibGDX " + VERSION);
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(DesktopLauncherConfig.CONFIG.fps());
		config.useVsync(DesktopLauncherConfig.CONFIG.vsync());
		config.setWindowedMode(DesktopLauncherConfig.CONFIG.width(), DesktopLauncherConfig.CONFIG.height());
		config.setTitle(DesktopLauncherConfig.CONFIG.title());
		if (Main.init()) {
			new Lwjgl3Application(new GuiManager(), config);
		}
	}
}
