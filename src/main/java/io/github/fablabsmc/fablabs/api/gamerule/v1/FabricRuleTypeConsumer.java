package io.github.fablabsmc.fablabs.api.gamerule.v1;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;

import net.minecraft.world.GameRules;

/**
 * An extended RuleTypeConsumer which supports fabric's own rule types.
 */
public interface FabricRuleTypeConsumer extends GameRules.RuleTypeConsumer {
	default void acceptDoubleRule(GameRules.RuleKey<DoubleRule> key, GameRules.RuleType<DoubleRule> type) {
	}

	default void acceptFloatRule(GameRules.RuleKey<FloatRule> key, GameRules.RuleType<FloatRule> type) {
	}

	default <E extends Enum<E>> void acceptEnumRule(GameRules.RuleKey<EnumRule<E>> key, GameRules.RuleType<EnumRule<E>> type) {
	}
}
