package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClearChat {

    @SubscribeEvent
    public void onRenderChat(RenderGameOverlayEvent.Pre event) {
        if (ModConfig.clearChatEnabled) {
            if (event.type == RenderGameOverlayEvent.ElementType.CHAT) {
                if (Minecraft.getMinecraft().ingameGUI != null) {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen();
                }
            }
        }
    }
}