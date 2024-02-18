package org.mod.cwcharges.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import org.jetbrains.annotations.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.mod.cwcharges.CustomizableWindCharges.getPower;
import static org.mod.cwcharges.CustomizableWindCharges.server;

@Mixin(WindChargeEntity.class)
public class WindChargeEntityMixin {

    @Unique
    private static final WindChargeEntity.WindChargeExplosionBehavior EXPLOSION_BEHAVIOR = new WindChargeEntity.WindChargeExplosionBehavior();
    @Unique
    private final Random random = server.getOverworld().getRandom();
    
    @Redirect(method = "createExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/particle/ParticleEffect;Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/world/explosion/Explosion;"))
    public Explosion redirectCreateExplosion(World instance, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, ParticleEffect particle, ParticleEffect emitterParticle, RegistryEntry<SoundEvent> soundEvent) {
        WindChargeEntity windChargeEntity = (WindChargeEntity) (Object) this;
        return instance.createExplosion(entity, null, EXPLOSION_BEHAVIOR, windChargeEntity.getX(), windChargeEntity.getY(), windChargeEntity.getZ(), getPower() + 0.3f * random.nextFloat(), false, World.ExplosionSourceType.BLOW, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, SoundEvents.ENTITY_WIND_CHARGE_WIND_BURST);
    }
}
