package com.pronekey.pronekeymod.mixin;

import com.pronekey.pronekeymod.ProneKeyMod;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.pronekey.pronekeymod.client.ProneKeyModClient.prone;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
//    @Inject(method = "tick", at = @At("HEAD"))
//    public void tick(CallbackInfo ci) {
//        ProneKeyMod.onPlayerTick((PlayerEntity) (Object) this);
//    }

    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        if(ProneKeyMod.isProneKeyPressed){
            cir.setReturnValue(true);
        }
    }
}
