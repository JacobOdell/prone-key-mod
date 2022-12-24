package com.pronekey.pronekeymod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ProneKeyModClient implements ClientModInitializer {

    //Defines a keybind named "prone" to the x key
    public static final KeyBinding prone = new KeyBinding("key.prone", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "key.category.test");

    @Override
    public void onInitializeClient() {
        //Registers the prone keybind
        KeyBindingHelper.registerKeyBinding(prone);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (prone.wasPressed()) {
                //This line is weird. Warning note.
                client.player.sendMessage(Text.literal("Prone key pressed"), false);
            }
        });

    }
}
