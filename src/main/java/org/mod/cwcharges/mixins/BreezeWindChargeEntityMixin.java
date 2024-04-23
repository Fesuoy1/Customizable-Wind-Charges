package org.mod.cwcharges.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.AbstractWindChargeEntity;
import net.minecraft.entity.projectile.BreezeWindChargeEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import org.jetbrains.annotations.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.mod.cwcharges.CustomizableWindCharges.*;

@Mixin(BreezeWindChargeEntity.class)
public class BreezeWindChargeEntityMixin {
    @Unique
    private static final AbstractWindChargeEntity.WindChargeExplosionBehavior EXPLOSION_BEHAVIOR = new AbstractWindChargeEntity.WindChargeExplosionBehavior();

    @Redirect(method = "createExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/world/explosion/Explosion;"))
    public Explosion redirectCreateExplosion(World instance, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, ParticleEffect particle, ParticleEffect emitterParticle, RegistryEntry<SoundEvent> soundEvent) {
        BreezeWindChargeEntity windChargeEntity = (BreezeWindChargeEntity) (Object) this;
        return instance.createExplosion(windChargeEntity, null, EXPLOSION_BEHAVIOR, windChargeEntity.getX(), windChargeEntity.getY(), windChargeEntity.getZ(), getPower() + 2.0f, false, World.ExplosionSourceType.BLOW, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.ENTITY_BREEZE_WIND_BURST);
    }
}
