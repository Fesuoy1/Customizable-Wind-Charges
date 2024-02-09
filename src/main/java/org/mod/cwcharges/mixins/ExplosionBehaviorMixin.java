package org.mod.cwcharges.mixins;

import net.minecraft.entity.projectile.AbstractWindChargeEntity.WindChargeExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.mod.cwcharges.CustomizableWindCharges;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExplosionBehavior.class)
public class ExplosionBehaviorMixin {

    @Inject(method = "getKnockbackModifier", at = @At("RETURN"), cancellable = true)
    public void onGetKnockbackModifier(CallbackInfoReturnable<Float> cir) {
        if ((ExplosionBehavior) (Object) this instanceof WindChargeExplosionBehavior) {
            cir.setReturnValue(CustomizableWindCharges.knockback);
        } else {
            cir.setReturnValue(1.1f);
        }

    }
}
