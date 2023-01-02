package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import static com.pronekey.pronekeymod.client.ProneKeyModClient.clientProneState;

//((PlayerEntity)(Object)this)

@Mixin(PlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        if (clientProneState.getState())
            cir.setReturnValue(true);
    }
}