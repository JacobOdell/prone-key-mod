package com.pronekey.pronekeymod.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.pronekey.pronekeymod.client.ProneKeyModClient;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

//((PlayerEntity)(Object)this)

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        if (ProneKeyModClient.proneState.getState()){
            cir.setReturnValue(true);
        }
    }
}