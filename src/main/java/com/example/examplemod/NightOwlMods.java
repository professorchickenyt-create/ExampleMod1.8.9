package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "nightowlmods", name = "NightOwl's Mods", version = "1.0", clientSideOnly = true)
public class NightOwlMods {

    public static KeyBinding openMenuKey;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModConfig.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        openMenuKey = new KeyBinding("Open Client Menu", Keyboard.KEY_RSHIFT, "NightOwl Client");
        ClientRegistry.registerKeyBinding(openMenuKey);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModuleLighting());
        MinecraftForge.EVENT_BUS.register(new ModuleDamageTilt());
        MinecraftForge.EVENT_BUS.register(new ModuleChat());
        MinecraftForge.EVENT_BUS.register(new ModuleArmorHUD());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (openMenuKey.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiClientMenu());
        }
    }
}