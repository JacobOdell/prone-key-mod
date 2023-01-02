package com.pronekey.pronekeymod;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.ModInitializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProneKeyMod implements ModInitializer {
    public static final String MODID = "prone-key-mod";
    //Maps playerUUIDs to a boolean indicating whether this player is prone or not
    public static Map<UUID, Boolean> playersToIsProne = new HashMap<>();

    @Override
    public void onInitialize() {
        PacketHandler.registerC2SPackets();
    }
}
