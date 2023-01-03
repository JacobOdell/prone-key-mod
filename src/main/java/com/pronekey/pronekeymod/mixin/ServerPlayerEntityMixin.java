package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

import static com.pronekey.pronekeymod.server.ProneKeyModServer.playersToIsProne;

@Mixin(PlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        UUID uuid = ((PlayerEntity)(Object)this).getUuid();

        if (playersToIsProne.get(uuid) != null && playersToIsProne.get(uuid))
            cir.setReturnValue(true);
    }
}