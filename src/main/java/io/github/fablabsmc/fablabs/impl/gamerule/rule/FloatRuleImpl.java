package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;

import net.minecraft.world.GameRules;

public class FloatRuleImpl extends FloatRule {
	public FloatRuleImpl(GameRules.RuleType<FloatRule> type, float value) {
		super(type, value);
	}
}
