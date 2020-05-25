package io.github.fablabsmc.fablabs.impl.gamerule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.CustomGameRuleCategory;

public interface RuleKeyInternals {
	/* @Nullable */
	CustomGameRuleCategory fabric_getCustomCategory();

	void fabric_setCustomCategory(CustomGameRuleCategory customCategory);
}
