package org.mod.cwcharges.mixins;

import net.minecraft.entity.projectile.AbstractWindChargeEntity.WindChargeExplosionBehavior;
import net.minecraft.world.explosion.ExplosionBehavior;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mod.cwcharges.CustomizableWindCharges.getKnockback;

@Mixin(ExplosionBehavior.class)
public class ExplosionBehaviorMixin {

    @Inject(method = "getKnockbackModifier", at = @At("RETURN"), cancellable = true)
    public void onGetKnockbackModifier(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue((ExplosionBehavior) (Object) this instanceof WindChargeExplosionBehavior ?
                getKnockback() : 1.0f);
    }
}
