package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoDamageTilt {

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        // Runs every single visual frame to aggressively suppress the hurt animation
        if (ModConfig.noDamageTiltEnabled && Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.hurtTime = 0;
            Minecraft.getMinecraft().thePlayer.maxHurtTime = 0;
            Minecraft.getMinecraft().thePlayer.attackedAtYaw = 0;
        }
    }
}