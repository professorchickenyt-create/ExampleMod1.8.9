package com.example.examplemod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class SimpleGui extends GuiScreen {

    private GuiButton toggleBtn;

    @Override
    public void initGui() {
        this.buttonList.clear();
        
        int boxWidth = 160;
        int boxHeight = 80;
        int x = (this.width - boxWidth) / 2;
        int y = (this.height - boxHeight) / 2;

        String statusText = "Fullbright: " + (Main.isFullbrightEnabled ? "ON" : "OFF");
        toggleBtn = new GuiButton(1, x + 10, y + 30, 140, 20, statusText);
        this.buttonList.add(toggleBtn);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            Main.toggleFullbright();
            button.displayString = "Fullbright: " + (Main.isFullbrightEnabled ? "ON" : "OFF");
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        int boxWidth = 160;
        int boxHeight = 80;
        int x = (this.width - boxWidth) / 2;
        int y = (this.height - boxHeight) / 2;

        drawRect(x, y, x + boxWidth, y + boxHeight, 0xC0101010);
        this.drawCenteredString(this.fontRendererObj, "Mod Settings", this.width / 2, y + 10, 0xFFFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}