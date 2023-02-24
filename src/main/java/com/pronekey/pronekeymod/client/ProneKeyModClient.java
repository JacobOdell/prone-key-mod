package com.pronekey.pronekeymod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import org.lwjgl.glfw.GLFW;

import com.pronekey.pronekeymod.networking.PacketHandler;


@Environment(EnvType.CLIENT)
public class ProneKeyModClient implements ClientModInitializer {

    public static final KeyBinding proneHoldKey = new KeyBinding("Hold Prone", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Prone Key Mod");
    public static final KeyBinding proneToggleKey = new KeyBinding("Toggle Prone", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Prone Key Mod");
    static boolean proneState = false;
    static boolean proneToggleKeyOn = false;

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(proneHoldKey);
        KeyBindingHelper.registerKeyBinding(proneToggleKey);

        //Registers a tick event listener to respond to key presses.
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null)
                return;

            //Checks if there is a change in the prone state and sends a packet to the server if so.
            if (this.checkForStateChange()) {
                PacketByteBuf pbb = PacketByteBufs.create();
                pbb.writeBoolean(getState());
                ClientPlayNetworking.send(PacketHandler.PRONE_ID, pbb);
            }
        });
    }

    // Checks if the state of the player needs to be changed when the key is pressed.
    public boolean checkForStateChange() {
        boolean oldState = proneState;

        while (proneToggleKey.wasPressed()) {
            proneToggleKeyOn = !proneToggleKeyOn;
        }

        proneState = proneHoldKey.isPressed() || proneToggleKeyOn;

        return oldState != proneState;
    }

    // Simple getter for the proneState variable
    public static boolean getState() {
        return proneState;
    }
}
