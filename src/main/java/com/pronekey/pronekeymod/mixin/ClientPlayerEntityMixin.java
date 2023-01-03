package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

//TODO: Remove ClientProneState class and replace with ProneKeyModClient
import static com.pronekey.pronekeymod.client.ProneKeyModClient.clientProneState;

@Mixin(PlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity)(Object)this;
        if (clientProneState.getState() && This.getVehicle() == null)
            cir.setReturnValue(true);
    }
}