package com.example.examplemod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class ModGui extends GuiScreen {

    private GuiButton fullbrightBtn;
    private GuiButton tiltBtn;
    private GuiButton clearChatBtn;

    @Override
    public void initGui() {
        this.buttonList.clear();

        int boxWidth = 180;
        int boxHeight = 130;
        int x = (this.width - boxWidth) / 2;
        int y = (this.height - boxHeight) / 2;

        fullbrightBtn = new GuiButton(1, x + 15, y + 25, 150, 20, getFullbrightText());
        tiltBtn = new GuiButton(2, x + 15, y + 55, 150, 20, getTiltText());
        clearChatBtn = new GuiButton(3, x + 15, y + 85, 150, 20, getClearChatText());

        this.buttonList.add(fullbrightBtn);
        this.buttonList.add(tiltBtn);
        this.buttonList.add(clearChatBtn);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            NightOwlMods.toggleFullbright();
            button.displayString = getFullbrightText();
        } else if (button.id == 2) {
            ModConfig.noDamageTiltEnabled = !ModConfig.noDamageTiltEnabled;
            ModConfig.saveConfig();
            button.displayString = getTiltText();
        } else if (button.id == 3) {
            ModConfig.clearChatEnabled = !ModConfig.clearChatEnabled;
            ModConfig.saveConfig();
            button.displayString = getClearChatText();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        int boxWidth = 180;
        int boxHeight = 130;
        int x = (this.width - boxWidth) / 2;
        int y = (this.height - boxHeight) / 2;

        drawRect(x, y, x + boxWidth, y + boxHeight, 0xC0101010);
        this.drawCenteredString(this.fontRendererObj, "NightOwl's Mods", this.width / 2, y + 8, 0xFFFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private String getFullbrightText() {
        return "Fullbright: " + (ModConfig.fullbrightEnabled ? "ON" : "OFF");
    }

    private String getTiltText() {
        return "No Damage Tilt: " + (ModConfig.noDamageTiltEnabled ? "ON" : "OFF");
    }

    private String getClearChatText() {
        return "Clear Chat: " + (ModConfig.clearChatEnabled ? "ON" : "OFF");
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}