package com.pronekey.pronekeymod.networking.packets;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.PacketByteBuf;

import java.util.UUID;

import static com.pronekey.pronekeymod.ProneKeyMod.playerProneStates;

public class ProneC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        //Everything here only happens on server
        UUID playerId = player.getUuid();
        boolean toSet;
        toSet = buf.readBoolean();
        System.out.println("toSet is!!! : " + toSet);
        System.out.println("playerProneStates is!!!! : " + playerProneStates.toString());

        playerProneStates.put(playerId, toSet);

//        boolean noEntry = playerProneStates.get(playerId) == null;
//        if(noEntry){
//            playerProneStates.put(playerId, true);
//        } else {
//            boolean currentState = playerProneStates.get(playerId);
//            playerProneStates.put(playerId, !currentState);
//        }

        player.setSwimming(toSet);
    }
}
