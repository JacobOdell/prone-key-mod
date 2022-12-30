package com.pronekey.pronekeymod.networking.packets;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.PacketByteBuf;

public class ProneC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        //Everything here only happens on server
        player.setSwimming(true);
    }
}
