package org.mod.cwcharges.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Explosion;

import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.mod.cwcharges.CustomizableWindCharges.*;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Redirect(method = "explode", at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/world/phys/Vec3;", ordinal = 2))
    public Vec3 onGetKnockbackModifier(double d, double e, double f) {
        Explosion explosion = (Explosion) (Object) this;
        Entity entity = null;
        if (explosion.getDirectSourceEntity() != null) {
            entity = explosion.getDirectSourceEntity();
        } else if (explosion.getIndirectSourceEntity() != null) {
            entity = explosion.getIndirectSourceEntity();
        }
        if (entity != null && entity.getType() == EntityType.WIND_CHARGE) {
            return new Vec3(d, e, f).scale(getPlayerKnockback());
        }
        if (entity != null && entity.getType() == EntityType.BREEZE_WIND_CHARGE) {
            return new Vec3(d, e, f).scale(getBreezeKnockback());
        }
       return new Vec3(d, e, f);
    }
}