package com.pronekey.pronekeymod.mixin;

import com.pronekey.pronekeymod.client.ProneKeyModClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        if(ProneKeyModClient.isProneKeyPressed){
            System.out.print(this);
            cir.setReturnValue(true);
            //((PlayerEntity)(Object)this)
        }
    }
}
