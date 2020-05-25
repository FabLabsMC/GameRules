package io.github.fablabsmc.fablabs.api.gamerule.v1;

import io.github.fablabsmc.fablabs.impl.gamerule.RuleKeyInternals;
import io.github.fablabsmc.fablabs.mixin.gamerule.GameRulesAccessor;

import net.minecraft.world.GameRules;

public final class GameRuleRegistry {
	private GameRuleRegistry() {
	}

	/**
	 * Registers a {@link GameRules.Rule}.
	 *
	 * @param name   the name of the rule
	 * @param category the category of this rule
	 * @param type the rule type
	 * @param <T>  the type of rule
	 * @return a rule key which can be used to query the value of the rule
	 * @throws IllegalStateException if a rule of the same name already exists
	 */
	public static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String name, GameRules.RuleCategory category, GameRules.RuleType<T> type) {
		return GameRulesAccessor.invokeRegister(name, category, type);
	}

	/**
	 * Registers a {@link GameRules.Rule} with a custom category.
	 *
	 * @param name 	the name of the rule
	 * @param category the category of this rule
	 * @param type the rule type
	 * @param <T>  the type of rule
	 * @return a rule key which can be used to query the value of the rule
	 * @throws IllegalStateException if a rule of the same name already exists
	 */
	public static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String name, CustomGameRuleCategory category, GameRules.RuleType<T> type) {
		final GameRules.RuleKey<T> key = GameRulesAccessor.invokeRegister(name, GameRules.RuleCategory.MISC, type);
		((RuleKeyInternals) (Object) key).fabric_setCustomCategory(category);
		return key;
	}

	/**
	 * Checks if a name for a rule is already being used.
	 *
	 * @param ruleName the rule name to test
	 * @return true if the name is taken.
	 */
	public static boolean isRuleNameUsed(String ruleName) {
		return GameRulesAccessor.getRuleTypes().keySet().stream().anyMatch(key -> key.getName().equals(ruleName));
	}
}
