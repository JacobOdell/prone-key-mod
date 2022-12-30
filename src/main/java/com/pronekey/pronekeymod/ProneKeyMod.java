package com.pronekey.pronekeymod;

import com.pronekey.pronekeymod.networking.PacketHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class ProneKeyMod implements ModInitializer {
    public static final String MODID = "prone-key-mod";
    public static Identifier identifier = new Identifier(MODID);
    public static ProneState proneState = new ProneState();

    @Override
    public void onInitialize() {
        PacketHandler.registerC2SPackets();
    }

    //Object for keeping track of when the state of the prone key is changed
    //Ik stinky location for it but was just making sure it would work.
    public static class ProneState{
        boolean currentState = false;
        int debugId = 0;

         public boolean checkForStateChange(boolean newState){
            if (newState != currentState){
                currentState = newState;
                System.out.println("Key State Changed: " + debugId);
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
