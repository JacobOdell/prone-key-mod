package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;
import static com.pronekey.pronekeymod.ProneKeyMod.playerProneStatesMap;
import static com.pronekey.pronekeymod.ProneKeyMod.proneState;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

//((PlayerEntity)(Object)this)

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity)(Object)this;


        System.out.println(playerProneStatesMap.get(This.getUuid()));

        if (playerProneStatesMap.get(This.getUuid()) == null){
            if (proneState.getState()){
                cir.setReturnValue(true);
            }
            return;
        }

        if (playerProneStatesMap.get(This.getUuid()) != null && playerProneStatesMap.get(This.getUuid()) == true){
            cir.setReturnValue(true);
        }
    }
}