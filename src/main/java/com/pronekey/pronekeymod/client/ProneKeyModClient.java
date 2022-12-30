package com.pronekey.pronekeymod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import com.pronekey.pronekeymod.networking.PacketHandler;
import net.minecraft.client.option.KeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import org.lwjgl.glfw.GLFW;
import static com.pronekey.pronekeymod.ProneKeyMod.proneState;

@Environment(EnvType.CLIENT)
public class ProneKeyModClient implements ClientModInitializer {

    //Defines a keybind named "prone" to the x key
    public static final KeyBinding prone = new KeyBinding("key.prone", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "key.category.test");

    @Override
    public void onInitializeClient() {
        //Registers the prone keybind
        KeyBindingHelper.registerKeyBinding(prone);

        //Registers a tick event listener to set the pose to swimming if the prone keybind is held
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            //if(!prone.isPressed() || client.player == null)
            if (client.player == null)
                return;

            //TODO: Current Issue:
            //The server does not seem to be retaining the pose after the first packet is set.
            //As my current understand has the server not having separate logic to check if the player should be swimming
            //or not I don't really know what's going on but am tired so idk.
            //Just gotta figure out why the server side pose of the player is being changed after the first packet sets it to swimming.
            //ServerPlayerEntity (the object used to set the pose when the server gets the packet)
            //extends PlayerEntity which is where our mixin should be preventing the pose from being updated while ProneState.getState == true
            //but that doesn't seem to be the case.

            //Packet is only sent when the state of the prone key is changed. This appears to work.
            if (proneState.checkForStateChange(prone.isPressed()) && proneState.getState()){
                ClientPlayNetworking.send(PacketHandler.PRONE_ID, PacketByteBufs.create());
                System.out.println("Prone Packet Sent");
            }
        });
        PacketHandler.registerS2CPackets();
    }

}
