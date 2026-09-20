package com.example.examplemod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiHudEditor extends GuiScreen {

    private boolean draggingArmorHud = false;
    private int dragOffsetX, dragOffsetY;

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, 10, 100, 20, "Save & Exit"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            ModConfig.saveConfig();
            this.mc.displayGuiScreen(new GuiClientMenu());
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // Draw Draggable Box for Armor HUD
        if (ModConfig.armorHud) {
            int boxW = 80;
            int boxH = 65;
            int x = ModConfig.armorHudX;
            int y = ModConfig.armorHudY;

            drawRect(x, y, x + boxW, y + boxH, 0x8000FF00);
            this.drawCenteredString(this.fontRendererObj, "Armor HUD", x + boxW / 2, y + boxH / 2 - 4, 0xFFFFFF);

            if (draggingArmorHud) {
                ModConfig.armorHudX = mouseX - dragOffsetX;
                ModConfig.armorHudY = mouseY - dragOffsetY;
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && ModConfig.armorHud) {
            int boxW = 80;
            int boxH = 65;
            if (mouseX >= ModConfig.armorHudX && mouseX <= ModConfig.armorHudX + boxW &&
                mouseY >= ModConfig.armorHudY && mouseY <= ModConfig.armorHudY + boxH) {
                draggingArmorHud = true;
                dragOffsetX = mouseX - ModConfig.armorHudX;
                dragOffsetY = mouseY - ModConfig.armorHudY;
            }
        }
        try { super.mouseClicked(mouseX, mouseY, mouseButton); } catch (Exception ignored) {}
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        draggingArmorHud = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}