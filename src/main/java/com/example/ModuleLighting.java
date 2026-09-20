package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ModuleLighting {

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings != null) {
            if (ModConfig.lighting) {
                mc.gameSettings.gammaSetting = 15.0f;
            } else if (mc.gameSettings.gammaSetting > 1.0f) {
                mc.gameSettings.gammaSetting = 1.0f;
            }
        }
    }
}