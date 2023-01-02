package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import static com.pronekey.pronekeymod.ProneKeyMod.playersToIsProne;

//((PlayerEntity)(Object)this)

@Mixin(PlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity)(Object)this;

        if (playersToIsProne.get(This.getUuid()) != null && playersToIsProne.get(This.getUuid())){
            cir.setReturnValue(true);
        }
    }
}