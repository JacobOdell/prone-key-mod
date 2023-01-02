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
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ProneKeyModClient implements ClientModInitializer {

    //Defines a keybind named "prone" to the x key
    public static final KeyBinding prone = new KeyBinding("key.prone", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "key.category.test");
    public static ClientProneState clientProneState = new ClientProneState();

    @Override
    public void onInitializeClient() {
        //Registers the prone keybind
        KeyBindingHelper.registerKeyBinding(prone);
        PacketHandler.registerS2CPackets();

        //Registers a tick event listener to set the pose to swimming if the prone keybind is held
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null)
                return;

            //Check if there is a change in the prone state and notify server if so
            if (clientProneState.checkForStateChange(prone.isPressed())){
                PacketByteBuf pbb = PacketByteBufs.create();
                pbb.writeBoolean(clientProneState.isProne);
                ClientPlayNetworking.send(PacketHandler.PRONE_ID, pbb);
            }
        });
    }

    //Object for keeping track of when the state of the prone key is changed
    public static class ClientProneState {
        boolean isProne = false;

        public boolean checkForStateChange(boolean newState){
            if (newState != isProne){
                isProne = newState;
                return true;
            }
            return false;
        }

        public boolean getState() {
            return isProne;
        }
    }
}
