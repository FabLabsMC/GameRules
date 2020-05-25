package io.github.fablabsmc.fablabs.api.gamerule.v1;

import io.github.fablabsmc.fablabs.impl.gamerule.RuleKeyInternals;
import io.github.fablabsmc.fablabs.mixin.gamerule.GameRulesAccessor;

import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public final class GameRuleRegistry {
	private GameRuleRegistry() {
	}

	/**
	 * Registers a {@link GameRules.Rule}.
	 *
	 * @param id   the id this rule will be named
	 * @param category the category of this rule
	 * @param type the rule type
	 * @param <T>  the type of rule
	 * @return a rule key which can be used to query the value of the rule
	 * @throws IllegalStateException if a rule of the same name already exists
	 */
	public static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(Identifier id, GameRules.RuleCategory category, GameRules.RuleType<T> type) {
		return GameRulesAccessor.invokeRegister(id.toString(), category, type);
	}

	/**
	 * Registers a {@link GameRules.Rule} with a custom category.
	 *
	 * @param id 	the id this rule will be named
	 * @param category the category of this rule
	 * @param type the rule type
	 * @param <T>  the type of rule
	 * @return a rule key which can be used to query the value of the rule
	 * @throws IllegalStateException if a rule of the same name already exists
	 */
	public static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(Identifier id, CustomGameRuleCategory category, GameRules.RuleType<T> type) {
		final GameRules.RuleKey<T> key = GameRulesAccessor.invokeRegister(id.toString(), GameRules.RuleCategory.MISC, type);
		((RuleKeyInternals) (Object) key).fabric_setCustomCategory(category);
		return key;
	}
}
