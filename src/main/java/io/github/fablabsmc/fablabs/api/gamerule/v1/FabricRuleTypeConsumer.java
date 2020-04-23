package io.github.fablabsmc.fablabs.api.gamerule.v1;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.StringRule;
import net.minecraft.world.GameRules;

/**
 * An extended RuleTypeConsumer which supports fabric's own Rules.
 */
public interface FabricRuleTypeConsumer extends GameRules.RuleTypeConsumer {
	default void acceptDoubleRule(GameRules.RuleKey<DoubleRule> key, GameRules.RuleType<DoubleRule> type) {
	}

	default void acceptFloatRule(GameRules.RuleKey<FloatRule> key, GameRules.RuleType<FloatRule> type) {
	}

	default void acceptStringRule(GameRules.RuleKey<StringRule> key, GameRules.RuleType<StringRule> type) {
	}

	default <E extends Enum<E>> void acceptEnumRule(GameRules.RuleKey<EnumRule<E>> key, GameRules.RuleType<EnumRule<E>> type) {
	}
}
