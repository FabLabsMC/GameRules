package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;

import net.minecraft.world.GameRules;

public class DoubleRuleImpl extends DoubleRule {
	public DoubleRuleImpl(GameRules.RuleType<DoubleRule> type, double value) {
		super(type, value);
	}
}
