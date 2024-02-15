package org.mod.cwcharges.mixins;


import net.minecraft.entity.projectile.WindChargeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mod.cwcharges.CustomizableWindCharges.getKnockback;

@Mixin(WindChargeEntity.WindChargeExplosionBehavior.class)
public class WindChargeExplosionBehaviorMixin {

    @Inject(method = "getKnockbackModifier", at = @At("RETURN"), cancellable = true)
    public void onGetKnockbackModifier(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(getKnockback());
    }
}
