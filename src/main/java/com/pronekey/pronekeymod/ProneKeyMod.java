package com.pronekey.pronekeymod;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProneKeyMod implements ModInitializer {
    public static final String MODID = "prone-key-mod";
    //public static Identifier identifier = new Identifier(MODID);
    public static ProneState proneState = new ProneState();
    public static Map<UUID, Boolean> playerProneStatesMap = new HashMap<>();

    @Override
    public void onInitialize() {
        PacketHandler.registerC2SPackets();
    }

    //Object for keeping track of when the state of the prone key is changed
    public static class ProneState{
        boolean currentState = false;
        int debugId = 0;

         public boolean checkForStateChange(boolean newState){
            if (newState != currentState){
                System.out.println("Key State Changed from: " + currentState + " to: " + newState + " " + debugId);
                currentState = newState;
                debugId++;
                return true;
            }
            return false;
        }
        public boolean getState() {
            return currentState;
        }
    }
}
