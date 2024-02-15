package org.mod.cwcharges;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizableWindCharges implements ModInitializer {


	public static final String MOD_ID = "customizable_wind_charges";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final GameRules.Key<GameRules.IntRule> COOLDOWN =
			GameRuleRegistry.register("windChargeCooldown", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(10));

	private static final GameRules.Key<GameRules.IntRule> POWER =
			GameRuleRegistry.register("windChargePower", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(1));


	private static final GameRules.Key<GameRules.IntRule> KNOCKBACK =
			GameRuleRegistry.register("windChargeKnockback", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(1));

	public static MinecraftServer server;

	public static Integer getCooldown() {
		return server.getGameRules().getInt(COOLDOWN);
	}

	public static Float getPower() {
		return (float)server.getGameRules().getInt(POWER);
	}

	public static Float getKnockback() {
		return (float)server.getGameRules().getInt(KNOCKBACK) == 1 ? 1.1f : server.getGameRules().getInt(KNOCKBACK);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Customizable Wind Charges Initialized");

		ServerLifecycleEvents.SERVER_STARTED.register(server -> CustomizableWindCharges.server = server);
		ServerLifecycleEvents.SERVER_STOPPED.register(server -> CustomizableWindCharges.server = null);
	}
}