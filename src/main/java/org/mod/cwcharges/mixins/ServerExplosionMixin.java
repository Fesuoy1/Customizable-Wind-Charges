package org.mod.cwcharges.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.ServerExplosion;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.mod.cwcharges.CustomizableWindCharges.*;

@Mixin(ServerExplosion.class)
public class ServerExplosionMixin {

    @Redirect(method = "hurtEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ExplosionDamageCalculator;getKnockbackMultiplier(Lnet/minecraft/world/entity/Entity;)F"))
    public float onGetKnockbackMultiplier(ExplosionDamageCalculator calculator, Entity entity) {
        ServerExplosion explosion = (ServerExplosion) (Object) this;
        Entity source = explosion.getDirectSourceEntity();
        if (source == null || (source != null && source.getType() != EntityTypes.WIND_CHARGE && source.getType() != EntityTypes.BREEZE_WIND_CHARGE))
        {
            source = explosion.getDamageSource().getDirectEntity();
            if (source == null || (source != null && source.getType() != EntityTypes.WIND_CHARGE && source.getType() != EntityTypes.BREEZE_WIND_CHARGE))
            {
                source = explosion.getDamageSource().getEntity();
            }
        }
        if (source != null && source.getType() == EntityTypes.WIND_CHARGE) {
            return calculator.getKnockbackMultiplier(entity) * getPlayerKnockback().floatValue();
        }
        if (source != null && source.getType() == EntityTypes.BREEZE_WIND_CHARGE) {
            return calculator.getKnockbackMultiplier(entity) * getBreezeKnockback().floatValue();
        }
        return calculator.getKnockbackMultiplier(entity);
    }
}