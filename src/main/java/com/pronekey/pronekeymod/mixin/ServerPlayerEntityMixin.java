package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import com.pronekey.pronekeymod.server.ProneKeyModServer;

//((PlayerEntity)(Object)this)

@Mixin(PlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity)(Object)this;
        if (ProneKeyModServer.getMap(This.getUuid()) && This.getVehicle() == null)
            cir.setReturnValue(true);
    }
}