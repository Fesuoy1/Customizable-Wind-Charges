package org.mod.cwcharges;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizableWindCharges implements ModInitializer {


    public static final String MOD_ID = "customizable_wind_charges";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Nullable
    public static MinecraftServer server;

    // Player Wind Charge gamerules
    public static GameRules.Key<GameRules.IntegerValue> COOLDOWN = GameRuleRegistry
            .register("windChargeCooldown", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(10));
    public static GameRules.Key<GameRules.IntegerValue> PLAYER_POWER = GameRuleRegistry
            .register("windChargePower", GameRules.Category.MOBS, GameRuleFactory.createIntRule(1));
    public static GameRules.Key<GameRules.IntegerValue> PLAYER_KNOCKBACK = GameRuleRegistry
            .register("windChargeKnockback", GameRules.Category.MOBS, GameRuleFactory.createIntRule(1));

    // Breeze Wind Charge gamerules
    public static GameRules.Key<GameRules.IntegerValue> BREEZE_POWER = GameRuleRegistry
            .register("breezeWindChargePower", GameRules.Category.MOBS, GameRuleFactory.createIntRule(3));
    public static GameRules.Key<GameRules.IntegerValue> BREEZE_KNOCKBACK = GameRuleRegistry
            .register("breezeWindChargeKnockback", GameRules.Category.MOBS, GameRuleFactory.createIntRule(1));

    // --- Player Wind Charge getters ---

    public static Integer getCooldown() {
        if (server == null) {
            return 10;
        }
        return server.getGameRules().getInt(COOLDOWN);
    }

    public static Float getPlayerPower() {
        if (server == null) {
            return 1.2f;
        }
        int power = server.getGameRules().getInt(PLAYER_POWER);
        return power == 1 ? 1.2f : (float) power;
    }

    public static Float getPlayerKnockback() {
        if (server == null) {
            return 1.0f;
        }
        return (float) server.getGameRules().getInt(PLAYER_KNOCKBACK);
    }

    // --- Breeze Wind Charge getters ---

    public static Float getBreezePower() {
        if (server == null) {
            return 3.0f;
        }
        return (float) server.getGameRules().getInt(BREEZE_POWER);
    }

    public static Float getBreezeKnockback() {
        if (server == null) {
            return 1.0f;
        }
        return (float) server.getGameRules().getInt(BREEZE_KNOCKBACK);
    }

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> CustomizableWindCharges.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> CustomizableWindCharges.server = null);

        LOGGER.info("Customizable Wind Charges Initialized");
    }
}