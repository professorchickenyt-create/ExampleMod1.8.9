package com.example.examplemod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class GuiClientMenu extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.clear();

        int frameW = 420;
        int frameH = 260;
        int frameX = (this.width - frameW) / 2;
        int frameY = (this.height - frameH) / 2;

        // Top-Right Close Button
        this.buttonList.add(new GuiButton(0, frameX + frameW - 25, frameY + 5, 20, 20, "X"));

        // Left Sidebar Button: Edit HUD Layout
        this.buttonList.add(new GuiButton(100, frameX + 10, frameY + frameH - 30, 95, 20, "EDIT HUD LAYOUT"));

        // 2x2 Grid Mod Cards
        int gridX = frameX + 115;
        int gridY = frameY + 45;
        int cardW = 140;
        int cardH = 95;
        int gapX = 15;
        int gapY = 15;

        // Card 1: Damage Tilt
        createModCard(1, gridX, gridY, cardW, cardH, ModConfig.damageTilt);

        // Card 2: Chat
        createModCard(2, gridX + cardW + gapX, gridY, cardW, cardH, ModConfig.chat);

        // Card 3: Lighting
        createModCard(3, gridX, gridY + cardH + gapY, cardW, cardH, ModConfig.lighting);

        // Card 4: Armor HUD
        createModCard(4, gridX + cardW + gapX, gridY + cardH + gapY, cardW, cardH, ModConfig.armorHud);
    }

    private void createModCard(int id, int x, int y, int w, int h, boolean enabled) {
        // Options Gear Button
        this.buttonList.add(new GuiButton(id * 10 + 1, x + 10, y + h - 30, 55, 20, "OPTIONS"));
        // Toggle Button
        String label = enabled ? "§aENABLED" : "§cDISABLED";
        this.buttonList.add(new GuiButton(id * 10 + 2, x + 70, y + h - 30, 60, 20, label));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(null);
            return;
        }

        if (button.id == 100) {
            this.mc.displayGuiScreen(new GuiHudEditor());
            return;
        }

        // Toggle Handlers
        if (button.id == 12) { // Damage Tilt
            ModConfig.damageTilt = !ModConfig.damageTilt;
            button.displayString = ModConfig.damageTilt ? "§aENABLED" : "§cDISABLED";
        } else if (button.id == 22) { // Chat
            ModConfig.chat = !ModConfig.chat;
            button.displayString = ModConfig.chat ? "§aENABLED" : "§cDISABLED";
        } else if (button.id == 32) { // Lighting
            ModConfig.lighting = !ModConfig.lighting;
            button.displayString = ModConfig.lighting ? "§aENABLED" : "§cDISABLED";
        } else if (button.id == 42) { // Armor HUD
            ModConfig.armorHud = !ModConfig.armorHud;
            button.displayString = ModConfig.armorHud ? "§aENABLED" : "§cDISABLED";
        }

        ModConfig.saveConfig();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        int frameW = 420;
        int frameH = 260;
        int frameX = (this.width - frameW) / 2;
        int frameY = (this.height - frameH) / 2;

        // Outer Dark Box
        drawRect(frameX, frameY, frameX + frameW, frameY + frameH, 0xF0181818);
        // Header Bar
        drawRect(frameX, frameY, frameX + frameW, frameY + 30, 0xF0222222);

        // Header Navigation Tabs (No logo, pure tabs)
        this.drawCenteredString(this.fontRendererObj, "§lMODS", frameX + 160, frameY + 10, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "SETTINGS", frameX + 220, frameY + 10, 0x888888);
        this.drawCenteredString(this.fontRendererObj, "WAYPOINTS", frameX + 290, frameY + 10, 0x888888);

        // Left Sidebar (Profiles)
        drawRect(frameX + 5, frameY + 35, frameX + 110, frameY + frameH - 5, 0xF0121212);
        drawRect(frameX + 10, frameY + 40, frameX + 105, frameY + 60, 0xF02A2A2A);
        this.drawString(this.fontRendererObj, "Default", frameX + 18, frameY + 46, 0xFFFFFF);

        // Render Cards & Card Titles
        int gridX = frameX + 115;
        int gridY = frameY + 45;
        int cardW = 140;
        int cardH = 95;
        int gapX = 15;
        int gapY = 15;

        drawCardBox(gridX, gridY, cardW, cardH, "Damage Tilt");
        drawCardBox(gridX + cardW + gapX, gridY, cardW, cardH, "Chat");
        drawCardBox(gridX, gridY + cardH + gapY, cardW, cardH, "Lighting");
        drawCardBox(gridX + cardW + gapX, gridY + cardH + gapY, cardW, cardH, "Armor HUD");

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawCardBox(int x, int y, int w, int h, String title) {
        drawRect(x, y, x + w, y + h, 0xF0222222);
        this.drawCenteredString(this.fontRendererObj, title, x + (w / 2), y + 20, 0xFFFFFF);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}