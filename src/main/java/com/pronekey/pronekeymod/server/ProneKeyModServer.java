package com.pronekey.pronekeymod.server;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Environment(EnvType.SERVER)
public class ProneKeyModServer implements DedicatedServerModInitializer {

    /**
     *  Maps player UUIDs to a boolean indicating whether this player is prone or not
     */
    private static final Map<UUID, Boolean> playersToIsProne = new HashMap<>();

    @Override
    public void onInitializeServer() {
        PacketHandler.registerC2SPackets();
    }

    public static void updateMap(UUID playerId, boolean isProne) {
        playersToIsProne.put(playerId, isProne);
    }

    public static boolean getMap(UUID playerId) {
        if(playersToIsProne.get(playerId) == null)
            return false;
        else
            return playersToIsProne.get(playerId);
    }
}
