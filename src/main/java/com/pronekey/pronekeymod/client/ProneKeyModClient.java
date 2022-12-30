package com.pronekey.pronekeymod.client;

import com.pronekey.pronekeymod.ProneKeyMod;
import com.pronekey.pronekeymod.networking.ModMessages;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

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
            if(!prone.isPressed() || client.player == null)
                return;

            //client.player.setPose(EntityPose.SWIMMING);
            ClientPlayNetworking.send(ModMessages.PRONE_ID, PacketByteBufs.create());

            //tell the server im crouching
            //
        });

        ModMessages.registerS2CPackets();
    }
}
