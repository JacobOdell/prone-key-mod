package com.pronekey.pronekeymod.client;

import com.ibm.icu.impl.StaticUnicodeSets;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.search.SearchManager;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import org.lwjgl.glfw.GLFW;

import com.pronekey.pronekeymod.networking.PacketHandler;

import java.security.Key;


@Environment(EnvType.CLIENT)
public class ProneKeyModClient implements ClientModInitializer {

    public static final KeyBinding prone = new KeyBinding("Prone Key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Prone Key Mod");
    public static final KeyBinding proneToggle = new KeyBinding("Toggle Prone Key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "Prone Key Mod");

    static boolean isProne = false;

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(proneToggle);
        KeyBindingHelper.registerKeyBinding(prone);

        //Registers a tick event listener to set the pose to swimming if the prone keybind is held
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null)
                return;

            //Check if there is a change in the prone state and notify server if so
            if (this.checkForStateChange()) {
                PacketByteBuf pbb = PacketByteBufs.create();
                pbb.writeBoolean(getState());
                ClientPlayNetworking.send(PacketHandler.PRONE_ID, pbb);
            }
        });
    }

    public boolean checkForStateChange() {
        boolean newState;

        newState = prone.isPressed();

        System.out.println(prone.isPressed() + " " + proneToggle.isPressed());

        if (newState != isProne) {
            isProne = newState;
            return true;
        }

        newState = proneToggle.isPressed();

        if (newState) {
            isProne = !isProne;
            System.out.println("Toggled" + isProne);
            return true;
        }
        return false;
    }

    public static boolean getState() {
        return isProne;
    }
}
