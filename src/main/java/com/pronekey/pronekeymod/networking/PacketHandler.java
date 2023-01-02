package com.pronekey.pronekeymod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

import com.pronekey.pronekeymod.networking.packets.ProneC2SPacket;
import com.pronekey.pronekeymod.ProneKeyMod;


public class PacketHandler {
    public static final Identifier PRONE_ID = new Identifier(ProneKeyMod.modId, "prone");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(PRONE_ID, ProneC2SPacket::receive);
    }

    public static void registerS2CPackets() {}
}