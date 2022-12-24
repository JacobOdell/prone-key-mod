package com.pronekey.pronekeymod.client;

import com.pronekey.pronekeymod.ProneKeyMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityPose;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ProneKeyModClient implements ClientModInitializer {

    //Defines a keybind named "prone" to the x key
    public static final KeyBinding prone = new KeyBinding("key.prone", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "key.category.test");

    @Override
    public void onInitializeClient() {
        //Registers the prone keybind
        KeyBindingHelper.registerKeyBinding(prone);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            while (prone.wasPressed()) {
//                //This line is weird. Warning note.
////                client.player.sendMessage(Text.literal("Prone key pressed"), false);
////                client.player.setPose(EntityPose.SWIMMING);
////                String hold = new String(client.player.getPose().toString());
//                System.out.println(client.player.getPose().toString());
//                client.player.setPose(EntityPose.SWIMMING);
//            }
            ProneKeyMod.isProneKeyPressed = prone.wasPressed();
        });

    }
}
