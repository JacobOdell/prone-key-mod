package com.pronekey.pronekeymod.server;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Environment(EnvType.SERVER)
public class ProneKeyModServer implements DedicatedServerModInitializer {

    //Maps player UUIDs to a boolean indicating whether this player is prone or not

    public static Map<UUID, Boolean> playersToIsProne = new HashMap<>();

    @Override
    public void onInitializeServer() {}
}
