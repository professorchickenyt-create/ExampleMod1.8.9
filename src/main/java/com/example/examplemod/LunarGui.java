package com.example.examplemod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import java.io.IOException;

public class LunarGui extends GuiScreen {

    private boolean draggingSlider = false;
    private int sliderX, sliderY, sliderWidth = 100;

    @Override
    public void initGui() {
        this.buttonList.clear();
        int startY = this.height / 2 - 50;
        
        // Mod Toggles
        this.buttonList.add(new GuiButton(1, this.width / 2 - 120, startY, 100, 20, getStatusText("Fullbright", ModConfig.fullbrightEnabled)));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 120, startY + 30, 100, 20, getStatusText("No Tilt", ModConfig.noDamageTiltEnabled)));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 120, startY + 60, 100, 20, getStatusText("Clear Chat", ModConfig.clearChatEnabled)));

        // Slider dimensions for Fullbright
        sliderX = this.width / 2 + 20;
        sliderY = startY;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            ModConfig.fullbrightEnabled = !ModConfig.fullbrightEnabled;
            button.displayString = getStatusText("Fullbright", ModConfig.fullbrightEnabled);
        } else if (button.id == 2) {
            ModConfig.noDamageTiltEnabled = !ModConfig.noDamageTiltEnabled;
            button.displayString = getStatusText("No Tilt", ModConfig.noDamageTiltEnabled);
        } else if (button.id == 3) {
            ModConfig.clearChatEnabled = !ModConfig.clearChatEnabled;
            button.displayString = getStatusText("Clear Chat", ModConfig.clearChatEnabled);
        }
        ModConfig.saveConfig();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // Draw main Lunar-style background plate
        int bgWidth = 280;
        int bgHeight = 160;
        int x = (this.width - bgWidth) / 2;
        int y = (this.height - bgHeight) / 2;
        drawRect(x, y, x + bgWidth, y + bgHeight, 0xD0151515);
        this.drawCenteredString(this.fontRendererObj, "NightOwl's Modules", this.width / 2, y + 15, 0x00FFAA);

        super.drawScreen(mouseX, mouseY, partialTicks);

        // Draw Slider for Fullbright Settings
        if (ModConfig.fullbrightEnabled) {
            drawRect(sliderX, sliderY, sliderX + sliderWidth, sliderY + 20, 0xFF303030); // Track
            
            // Calculate thumb position
            int thumbX = sliderX + (int) ((ModConfig.fullbrightBrightness / 100.0f) * (sliderWidth - 10));
            drawRect(thumbX, sliderY, thumbX + 10, sliderY + 20, 0xFF00FFAA); // Thumb

            this.drawString(this.fontRendererObj, "Brightness: " + ModConfig.fullbrightBrightness + "%", sliderX, sliderY - 12, 0xFFFFFF);

            // Handle Dragging
            if (Mouse.isButtonDown(0) && draggingSlider) {
                float percent = (float) (mouseX - sliderX) / (float) sliderWidth;
                ModConfig.fullbrightBrightness = (int) Math.max(0, Math.min(100, percent * 100));
                ModConfig.saveConfig();
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (ModConfig.fullbrightEnabled && mouseButton == 0) {
            if (mouseX >= sliderX && mouseX <= sliderX + sliderWidth && mouseY >= sliderY && mouseY <= sliderY + 20) {
                draggingSlider = true;
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        draggingSlider = false;
    }

    private String getStatusText(String name, boolean enabled) {
        return name + ": " + (enabled ? "§aON" : "§cOFF");
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}