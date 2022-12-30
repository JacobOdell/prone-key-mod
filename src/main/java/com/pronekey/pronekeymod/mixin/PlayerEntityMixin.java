package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;
import static com.pronekey.pronekeymod.ProneKeyMod.playerProneStates;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

//((PlayerEntity)(Object)this)

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity)(Object)this;

        if (playerProneStates.get(This.getUuid()) != null && playerProneStates.get(This.getUuid())){
            cir.setReturnValue(true);
        }

//        if (proneState.getState()){
//            cir.setReturnValue(true);
//        }
    }
}