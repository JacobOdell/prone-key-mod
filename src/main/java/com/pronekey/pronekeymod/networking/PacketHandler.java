package com.pronekey.pronekeymod.networking;

import com.pronekey.pronekeymod.networking.packets.ProneC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import com.pronekey.pronekeymod.ProneKeyMod;
import net.minecraft.util.Identifier;

public class PacketHandler {
    public static final Identifier PRONE_ID = new Identifier(ProneKeyMod.MODID, "prone");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(PRONE_ID, ProneC2SPacket::receive);
    }

    public static void registerS2CPackets() {}
}
