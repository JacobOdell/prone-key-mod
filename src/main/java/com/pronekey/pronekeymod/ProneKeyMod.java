package com.pronekey.pronekeymod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import static com.pronekey.pronekeymod.client.ProneKeyModClient.prone;

public class ProneKeyMod implements ModInitializer {
    @Override
    public void onInitialize() {

    }

    public static boolean isProneKeyPressed = false;

    public static void onPlayerTick(PlayerEntity pe){
//        System.out.println("onPlayerTick()");
        if (prone.wasPressed()) {
            //This line is weird. Warning note.
//                client.player.sendMessage(Text.literal("Prone key pressed"), false);
//                client.player.setPose(EntityPose.SWIMMING);
//                String hold = new String(client.player.getPose().toString());
            isProneKeyPressed = true;
        } else {
            isProneKeyPressed = false;
        }
    }
}
