package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fullbright {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings != null) {
            if (ModConfig.fullbrightEnabled) {
                // Safely scales gamma without corrupting chunk lighting (0% = 1.0f, 100% = 15.0f max daylight)
                float targetGamma = 1.0f + (ModConfig.fullbrightBrightness / 100.0f) * 14.0f;
                mc.gameSettings.gammaSetting = targetGamma;
            } else {
                mc.gameSettings.gammaSetting = 1.0f; // Reset to default moody/bright
            }
        }
    }
}