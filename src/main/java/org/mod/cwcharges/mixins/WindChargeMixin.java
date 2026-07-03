package org.mod.cwcharges.mixins;

import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static org.mod.cwcharges.CustomizableWindCharges.getPlayerPower;

@Mixin(WindCharge.class)
public class WindChargeMixin {

    @ModifyArg(method = "explode", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/util/random/WeightedList;Lnet/minecraft/core/Holder;)V"
        ), index = 6
    )
    public float explode$modify(float r) {
        return getPlayerPower().floatValue();
    }
}