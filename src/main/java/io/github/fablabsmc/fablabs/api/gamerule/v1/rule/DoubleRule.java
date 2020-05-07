package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import com.mojang.brigadier.context.CommandContext;
import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public class DoubleRule extends GameRules.Rule<DoubleRule> implements ValidateableRule {
	private static final Logger LOGGER = LogManager.getLogger(GameRuleRegistry.class);

	private final double lowerBound;
	private final double upperBound;
	private double value;

	@Deprecated
	public DoubleRule(GameRules.RuleType<DoubleRule> type, double value, double lowerBound, double upperBound) {
		super(type);
		this.value = value;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Double.class);
	}

	@Override
	protected void deserialize(String value) {
		final double d = DoubleRule.parseDouble(value);

		if (this.lowerBound > d || this.upperBound < d) {
			LOGGER.warn("Failed to parse double {}. Was out of bounds {} - {}", value, this.lowerBound, this.upperBound);
			return;
		}

		this.value = d;
	}

	private static double parseDouble(String string) {
		if (!string.isEmpty()) {
			try {
				return Double.parseDouble(string);
			} catch (NumberFormatException e) {
				LOGGER.warn("Failed to parse double {}", string);
			}
		}

		return 0.0D;
	}

	@Override
	public String serialize() {
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
	protected DoubleRule copy() {
		return new DoubleRule(this.type, this.value, this.lowerBound, this.upperBound);
	}

	@Override
	public void setValue(DoubleRule rule, MinecraftServer minecraftServer) {
		this.value = rule.value;
		this.changed(minecraftServer);
	}

	@Override
	public boolean validate(String value) {
		try {
			final double d = Double.parseDouble(value);

			return !(this.lowerBound > d) && !(this.upperBound < d);
		} catch (NumberFormatException ignored) {
			return false;
		}
	}

	public double get() {
		return this.value;
	}
}
