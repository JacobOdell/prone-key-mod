package com.pronekey.pronekeymod;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.ModInitializer;

//TODO Clean up version json stuff, mod name etc.

public class ProneKeyMod implements ModInitializer {
    public static final String MODID = "prone-key-mod";

    @Override
    public void onInitialize() {
        PacketHandler.registerC2SPackets();
        PacketHandler.registerS2CPackets();
    }
}
