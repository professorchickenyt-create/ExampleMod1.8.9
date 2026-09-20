package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "cleanfullbright", name = "Clean Fullbright", version = "1.0", clientSideOnly = true)
public class Main {

    public static KeyBinding openGuiKey;
    public static boolean isFullbrightEnabled = false;
    public static float savedGamma = 1.0f;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        openGuiKey = new KeyBinding("Open Fullbright Menu", Keyboard.KEY_RSHIFT, "Clean Mods");
        ClientRegistry.registerKeyBinding(openGuiKey);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (openGuiKey.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new SimpleGui());
        }
    }

    public static void toggleFullbright() {
        Minecraft mc = Minecraft.getMinecraft();
        isFullbrightEnabled = !isFullbrightEnabled;

        if (isFullbrightEnabled) {
            savedGamma = mc.gameSettings.gammaSetting;
            mc.gameSettings.gammaSetting = 100.0f;
        } else {
            mc.gameSettings.gammaSetting = savedGamma;
        }
    }
}