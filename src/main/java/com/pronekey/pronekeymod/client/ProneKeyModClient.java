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

           if (client.player == null){
               //System.out.println("Client was null returning!!!");
               return;
           }

            if (proneState.checkForStateChange(prone.isPressed())){

                //send a packet representing change in state
                PacketByteBuf pbb = PacketByteBufs.create();

                //Changed back to getState bc technically the is prone key could be tapped an not be pressed byu the time the packet is made and sent
                //pbb.writeBoolean(proneState.getState());

                ClientPlayNetworking.send(PacketHandler.PRONE_ID, pbb);
                //System.out.println("Prone Packet Sent containing: " + pbb.readBoolean());
            }
        });
        PacketHandler.registerS2CPackets();
    }

}
