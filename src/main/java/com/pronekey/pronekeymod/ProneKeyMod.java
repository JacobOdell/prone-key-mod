package com.pronekey.pronekeymod;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ProneKeyMod implements ModInitializer {
    public static final String MODID = "prone-key-mod";
    public static Identifier identifier = new Identifier(MODID);

    @Override
    public void onInitialize() {
        PacketHandler.registerC2SPackets();
    }
}
