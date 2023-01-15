package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.PlayerEntity;

import com.pronekey.pronekeymod.client.ProneKeyModClient;


@Mixin(PlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity) (Object) this;
        if (ProneKeyModClient.getState() && This.getVehicle() == null) {
            cir.setReturnValue(true);
            if (This.isSubmergedInWater()) {
                This.setSprinting(true);
            }
        }
    }
}