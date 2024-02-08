package org.mod.cwcharges;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomizableWindCharges implements ModInitializer {

	private static final Integer SUCCESS = 1;

	public static final String MOD_ID = "customizable_wind_charges";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// the following variables will be used in a mixins
	public static int cooldown = 10;
	public static float power = 1.0f;

	@Override
	public void onInitialize() {
		LOGGER.info("Customizable Wind Charges Initialized");

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("setWindChargeCooldown")
					.requires(source -> source.hasPermissionLevel(3))
					.then(CommandManager.argument("cooldown", IntegerArgumentType.integer(0))
							.executes((context) -> {
								cooldown = IntegerArgumentType.getInteger(context, "cooldown");
								context.getSource().sendFeedback(() -> Text.literal("Now set Wind Charge Cooldown to " + cooldown), false);
								return SUCCESS;
							})));

			dispatcher.register(CommandManager.literal("setWindChargePower")
					.requires(source -> source.hasPermissionLevel(3))
					.then(CommandManager.argument("power", FloatArgumentType.floatArg(0))
							.executes((context) -> {
								power = FloatArgumentType.getFloat(context, "power");
								context.getSource().sendFeedback(() -> Text.literal("Now set Wind Charge Power to " + power), false);
								return SUCCESS;
							})));
		});
	}
}