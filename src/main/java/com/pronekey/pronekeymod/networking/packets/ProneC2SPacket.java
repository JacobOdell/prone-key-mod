package com.pronekey.pronekeymod.networking.packets;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.PacketByteBuf;

import java.util.UUID;

import static com.pronekey.pronekeymod.ProneKeyMod.playerProneStatesMap;

public class ProneC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender){
        //Everything here only happens on server
        UUID playerId = player.getUuid();
        //boolean toSet;
        //toSet = buf.readBoolean();
        //System.out.println("toSet is!!! : " + toSet);
        //System.out.println("playerProneStates is!!!! : " + playerProneStatesMap.toString());

        System.out.println("Key State Changed from: " + playerProneStatesMap.get(playerId));

        if (playerProneStatesMap.get(playerId) == null || playerProneStatesMap.get(playerId) == false) {
            playerProneStatesMap.put(playerId, true);
        } else {
            playerProneStatesMap.put(playerId, false);
        }

        System.out.println("To: " + playerProneStatesMap.get(playerId));
        System.out.println("///////////////////////////////////////////////////");


        //player.setSwimming(toSet);
    }
}
