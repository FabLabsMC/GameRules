package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.mixin.gamerule.IntRuleAccessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.GameRules;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class BoundedIntRule extends GameRules.IntRule {
	private static final Logger LOGGER = LogManager.getLogger(GameRuleRegistry.class);

	private final int lowerBound;
	private final int upperBound;

	public BoundedIntRule(GameRules.RuleType<GameRules.IntRule> type, int initialValue, int lowerBound, int upperBound) {
		super(type, initialValue);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	protected void deserialize(String value) {
		final int i = BoundedIntRule.parseInt(value);

		if (this.lowerBound > i || this.upperBound < i) {
			LOGGER.warn("Failed to parse integer {}. Was out of bounds {} - {}", value, this.lowerBound, this.upperBound);
			return;
		}

		((IntRuleAccessor) this).setValue(i);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean validate(String input) {
		try {
			int value = Integer.parseInt(input);

			if (this.lowerBound > value || this.upperBound < value) {
				return false;
			}

			((IntRuleAccessor) this).setValue(value);
			return true;
		} catch (NumberFormatException var3) {
			return false;
		}
	}

	@Override
	protected GameRules.IntRule copy() {
		return new BoundedIntRule(this.type, ((IntRuleAccessor) this).getValue(), this.lowerBound, this.upperBound);
	}

	private static int parseInt(String input) {
		if (!input.isEmpty()) {
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException var2) {
				LOGGER.warn("Failed to parse integer {}", input);
			}
		}

		return 0;
	}
}
