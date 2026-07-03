package org.mod.cwcharges;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;

import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizableWindCharges implements ModInitializer {


    public static final String MOD_ID = "customizable_wind_charges";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Nullable
    public static MinecraftServer server;

    // Player Wind Charge gamerules
    public static final GameRule<Integer> COOLDOWN = GameRuleBuilder.forInteger(10)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "wind_charge_cooldown"));

    public static final GameRule<Double> PLAYER_POWER = GameRuleBuilder.forDouble(1.2D)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "wind_charge_power"));

    public static final GameRule<Double> PLAYER_KNOCKBACK = GameRuleBuilder.forDouble(1)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "wind_charge_knockback"));

    // Breeze Wind Charge gamerules
    public static final GameRule<Double> BREEZE_POWER = GameRuleBuilder.forDouble(3)
            .category(GameRuleCategory.MOBS)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "breeze_wind_charge_power"));

    public static final GameRule<Double> BREEZE_KNOCKBACK = GameRuleBuilder.forDouble(1)
            .category(GameRuleCategory.MOBS)
            .buildAndRegister(Identifier.fromNamespaceAndPath(MOD_ID, "breeze_wind_charge_knockback"));

    // --- Player Wind Charge getters ---

    public static Integer getCooldown() {
        if (server == null) {
            return 10;
        }
        return server.getGameRules().get(COOLDOWN);
    }

    public static Double getPlayerPower() {
        if (server == null) {
            return 1.2;
        }
        return server.getGameRules().get(PLAYER_POWER);
    }

    public static Double getPlayerKnockback() {
        if (server == null) {
            return 1.0;
        }
        return server.getGameRules().get(PLAYER_KNOCKBACK);
    }

    // --- Breeze Wind Charge getters ---

    public static Double getBreezePower() {
        if (server == null) {
            return 3.0;
        }
        return server.getGameRules().get(BREEZE_POWER);
    }

    public static Double getBreezeKnockback() {
        if (server == null) {
            return 1.0;
        }
        return  server.getGameRules().get(BREEZE_KNOCKBACK);
    }

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> server = s);
        ServerLifecycleEvents.SERVER_STOPPED.register(_ -> server = null);

        LOGGER.info("Customizable Wind Charges Initialized");
    }
}