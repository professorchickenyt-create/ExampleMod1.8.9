package com.example.examplemod;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.lang.reflect.Field;

public class ClearChat {

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        // Uses reflection to hack into the private text field and disable the dark typing box
        if (ModConfig.clearChatEnabled && event.gui instanceof GuiChat) {
            for (Field field : GuiChat.class.getDeclaredFields()) {
                if (field.getType() == GuiTextField.class) {
                    field.setAccessible(true);
                    try {
                        GuiTextField textField = (GuiTextField) field.get(event.gui);
                        textField.setEnableBackgroundDrawing(false);
                    } catch (Exception ignored) {}
                }
            }
        }
    }
}