package com.pronekey.pronekeymod.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.PlayerEntity;

import com.pronekey.pronekeymod.client.ProneKeyModClient;

// Injects into the base game's isSwimming function at the top of the code.
@Mixin(PlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity This = (PlayerEntity) (Object) this;

        // Main function that sets the player to the swimming position.
        if (ProneKeyModClient.getState() && This.getVehicle() == null) {
            cir.setReturnValue(true);

            //Allows the player to start swimming using the key.
            if (This.isSubmergedInWater()) {
                This.setSprinting(true);
            }
        }
    }
}