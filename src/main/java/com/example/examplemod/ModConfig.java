package com.example.examplemod;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class ModConfig {

    private static Configuration config;

    public static boolean damageTilt = false;
    public static boolean chat = false;
    public static boolean lighting = false;
    public static boolean armorHud = false;

    // Armor HUD Position
    public static int armorHudX = 10;
    public static int armorHudY = 10;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        loadConfig();
    }

    public static void loadConfig() {
        config.load();
        damageTilt = config.getBoolean("DamageTilt", Configuration.CATEGORY_GENERAL, false, "Toggle Damage Tilt");
        chat = config.getBoolean("Chat", Configuration.CATEGORY_GENERAL, false, "Toggle Clear Chat");
        lighting = config.getBoolean("Lighting", Configuration.CATEGORY_GENERAL, false, "Toggle Fullbright Lighting");
        armorHud = config.getBoolean("ArmorHUD", Configuration.CATEGORY_GENERAL, false, "Toggle Armor HUD");

        armorHudX = config.getInt("ArmorHudX", Configuration.CATEGORY_GENERAL, 10, 0, 4000, "Armor HUD X Position");
        armorHudY = config.getInt("ArmorHudY", Configuration.CATEGORY_GENERAL, 10, 0, 4000, "Armor HUD Y Position");

        if (config.hasChanged()) config.save();
    }

    public static void saveConfig() {
        config.get(Configuration.CATEGORY_GENERAL, "DamageTilt", false).setValue(damageTilt);
        config.get(Configuration.CATEGORY_GENERAL, "Chat", false).setValue(chat);
        config.get(Configuration.CATEGORY_GENERAL, "Lighting", false).setValue(lighting);
        config.get(Configuration.CATEGORY_GENERAL, "ArmorHUD", false).setValue(armorHud);

        config.get(Configuration.CATEGORY_GENERAL, "ArmorHudX", 10).setValue(armorHudX);
        config.get(Configuration.CATEGORY_GENERAL, "ArmorHudY", 10).setValue(armorHudY);

        config.save();
    }
}