package com.example.examplemod;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class ModConfig {

    private static Configuration config;

    public static boolean fullbrightEnabled = false;
    public static int fullbrightBrightness = 100; // 0 to 100 slider value
    
    public static boolean noDamageTiltEnabled = false;
    public static boolean clearChatEnabled = false;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        loadConfig();
    }

    public static void loadConfig() {
        config.load();
        fullbrightEnabled = config.getBoolean("Fullbright", Configuration.CATEGORY_GENERAL, false, "Enable Fullbright");
        fullbrightBrightness = config.getInt("FullbrightBrightness", Configuration.CATEGORY_GENERAL, 100, 0, 100, "Brightness Level");
        noDamageTiltEnabled = config.getBoolean("NoDamageTilt", Configuration.CATEGORY_GENERAL, false, "Disable damage tilt screen shake");
        clearChatEnabled = config.getBoolean("ClearChat", Configuration.CATEGORY_GENERAL, false, "Transparent background for chat");
        
        if (config.hasChanged()) config.save();
    }

    public static void saveConfig() {
        config.get(Configuration.CATEGORY_GENERAL, "Fullbright", false).setValue(fullbrightEnabled);
        config.get(Configuration.CATEGORY_GENERAL, "FullbrightBrightness", 100).setValue(fullbrightBrightness);
        config.get(Configuration.CATEGORY_GENERAL, "NoDamageTilt", false).setValue(noDamageTiltEnabled);
        config.get(Configuration.CATEGORY_GENERAL, "ClearChat", false).setValue(clearChatEnabled);
        config.save();
    }
}