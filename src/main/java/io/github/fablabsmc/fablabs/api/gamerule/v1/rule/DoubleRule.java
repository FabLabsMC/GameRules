package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import java.util.function.DoubleSupplier;

import com.mojang.brigadier.context.CommandContext;
import io.github.fablabsmc.fablabs.impl.gamerule.GameRuleRegistryImpl;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public abstract class DoubleRule extends GameRules.Rule<DoubleRule> implements DoubleSupplier {
	private double value;

	protected DoubleRule(GameRules.RuleType<DoubleRule> type, double value) {
		super(type);
		this.value = value;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Double.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = DoubleRule.parseDouble(value);
	}

	private static double parseDouble(String string) {
		if (!string.isEmpty()) {
			try {
				return Double.parseDouble(string);
			} catch (NumberFormatException e) {
				GameRuleRegistryImpl.LOGGER.warn("Failed to parse double {}", string);
			}
		}

		return 0.0D;
	}

	@Override
	protected String serialize() {
		return Double.toString(this.value);
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected DoubleRule getThis() {
		return this;
	}

	@Override
	public double getAsDouble() {
		return this.value;
	}
}
