package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ModuleDamageTilt {

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (ModConfig.damageTilt && Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.hurtTime = 0;
            Minecraft.getMinecraft().thePlayer.maxHurtTime = 0;
            Minecraft.getMinecraft().thePlayer.attackedAtYaw = 0;
        }
    }
}