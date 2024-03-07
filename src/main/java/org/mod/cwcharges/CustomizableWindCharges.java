package org.mod.cwcharges;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizableWindCharges implements ModInitializer {


    public static final String MOD_ID = "customizable_wind_charges";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Nullable
    public static MinecraftServer server;
    public static Random random = server != null ? server.getOverworld().getRandom() : Random.create();

    public static GameRules.Key<GameRules.IntRule> COOLDOWN = GameRuleRegistry.register("windChargeCooldown", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(10));
    public static GameRules.Key<GameRules.IntRule> POWER = GameRuleRegistry.register("windChargePower", GameRules.Category.MOBS, GameRuleFactory.createIntRule(1));
    public static GameRules.Key<GameRules.IntRule> KNOCKBACK = GameRuleRegistry.register("windChargeKnockback", GameRules.Category.MOBS, GameRuleFactory.createIntRule(1));

    public static Integer getCooldown() {
        if (server == null) {
            return 10;
        }
        return server.getGameRules().getInt(COOLDOWN);
    }

    public static Float getPower() {
        if (server == null) {
            return 1.0f;
        }
        return (float) server.getGameRules().getInt(POWER);
    }

    public static Float getKnockback() {
        if (server == null) {
            return 1.0f;
        }
        return (float) server.getGameRules().getInt(KNOCKBACK) == 1 ? 1.1f : server.getGameRules().getInt(KNOCKBACK);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Customizable Wind Charges Initialized");

        ServerLifecycleEvents.SERVER_STARTED.register(server -> CustomizableWindCharges.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> CustomizableWindCharges.server = null);
    }
}