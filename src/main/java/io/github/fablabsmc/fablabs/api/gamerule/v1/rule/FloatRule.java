package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import com.mojang.brigadier.context.CommandContext;
import io.github.fablabsmc.fablabs.api.gamerule.v1.FloatSupplier;
import io.github.fablabsmc.fablabs.impl.gamerule.GameRuleRegistryImpl;

import io.github.fablabsmc.fablabs.impl.gamerule.rule.FloatRuleImpl;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public abstract class FloatRule extends GameRules.Rule<FloatRule> implements FloatSupplier, ValidateableRule {
	private float value;

	protected FloatRule(GameRules.RuleType<FloatRule> type, float value) {
		super(type);
		this.value = value;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Float.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = FloatRule.parseFloat(value);
	}

	private static float parseFloat(String string) {
		if (!string.isEmpty()) {
			try {
				return Float.parseFloat(string);
			} catch (NumberFormatException e) {
				GameRuleRegistryImpl.LOGGER.warn("Failed to parse float {}", string);
			}
		}

		return 0.0F;
	}

	@Override
	public String serialize() {
		return Float.toString(this.value);
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected FloatRule getThis() {
		return this;
	}

	@Override
	protected FloatRule method_27338() {
		return new FloatRuleImpl(this.type, this.value);
	}

	@Override
	public void method_27337(FloatRule rule, MinecraftServer minecraftServer) {
		this.value = rule.value;
		this.changed(minecraftServer);
	}

	@Override
	public boolean validate(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException ignored) {
			return false;
		}
	}

	@Override
	public float getAsFloat() {
		return this.value;
	}
}
