package com.pronekey.pronekeymod.networking;

import com.pronekey.pronekeymod.ProneKeyMod;
import com.pronekey.pronekeymod.networking.packets.ProneC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier PRONE_ID = new Identifier(ProneKeyMod.MODID, "prone");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(PRONE_ID, ProneC2SPacket::receive);
    }

    public static void registerS2CPackets(){

    }

}
