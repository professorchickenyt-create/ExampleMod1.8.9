package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleArmorHUD {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (ModConfig.armorHud && mc.thePlayer != null && !(mc.currentScreen instanceof GuiHudEditor)) {
            RenderItem itemRender = mc.getRenderItem();
            int x = ModConfig.armorHudX;
            int y = ModConfig.armorHudY;

            RenderHelper.enableGUIStandardItemLighting();
            for (int i = 3; i >= 0; i--) {
                ItemStack stack = mc.thePlayer.inventory.armorInventory[i];
                if (stack != null) {
                    itemRender.renderItemAndEffectIntoGUI(stack, x, y);
                    itemRender.renderItemOverlays(mc.fontRendererObj, stack, x, y);
                    y += 16;
                }
            }
            RenderHelper.disableStandardItemLighting();
        }
    }
}