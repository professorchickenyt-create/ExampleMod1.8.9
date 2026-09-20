package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoDamageTilt {

    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        if (ModConfig.noDamageTiltEnabled) {
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.hurtTime = 0;
                Minecraft.getMinecraft().thePlayer.maxHurtTime = 0;
            }
        }
    }
}