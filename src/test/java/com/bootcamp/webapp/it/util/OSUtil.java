package com.bootcamp.webapp.it.util;

public class OSUtil {
    // Operating systems
    public static enum OS {
        WINDOWS("webdrivers/windows/chromedriver.exe"),
        LINUX("webdrivers/linux/chromedriver"),
        MAC("webdrivers/mac/chromedriver");

        String path;

        OS(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    };

    private static OS os = null;

    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            } else {
                os = OS.MAC;
            }
        }
        return os;
    }
}
