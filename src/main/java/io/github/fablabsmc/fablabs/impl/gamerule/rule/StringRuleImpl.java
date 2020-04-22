package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.StringRule;

import net.minecraft.world.GameRules;

public class StringRuleImpl extends StringRule {
	public StringRuleImpl(GameRules.RuleType<StringRule> type, String value) {
		super(type, value);
	}
}
