package com.netherbyte.pizzashopsimulator.util;

import com.netherbyte.bgtk.system.SystemUtil;
import com.netherbyte.svlib.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.netherbyte.pizzashopsimulator.SharedConstants.NAME;

public class OSUtil extends SystemUtil {
    private static final Logger logger = LoggerFactory.getLogger(OSUtil.class);

    public static final Version OSVERREQ_WIN = Version.parse("10");
    public static final Version OSVERREQ_MAC = Version.parse("11");
    public static final Version OSVERREQ_LIN = Version.parse("4.14");

    public static String getApplicationStorageDirectory() {
        if (OS_NAME == null) {
            logger.error("Failed to get OS name");
            assert false;
        }
        assert OS_NAME != null;
        if (OS_NAME.contains("win")) {
            return System.getenv("APPDATA") + "\\Netherbyte\\" + NAME;
        } else if (OS_NAME.contains("mac")) {
            return System.getProperty("user.home") + "/Library/Application Support/" + NAME + "/Microfacture";
        } else if (OS_NAME.contains("linux")) {
            return System.getProperty("user.home") + "/.local/share/Netherbyte/" + NAME;
        } else {
            logger.warn("Unsupported OS: " + OS_NAME);
            logger.warn("You might experience issues with file system related features");
            return System.getProperty("user.home") + "/.netherbyte/" + NAME;
        }

    }
}
