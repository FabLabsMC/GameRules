package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.TextRule;

import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

public class TextRuleImpl extends TextRule {
	public TextRuleImpl(GameRules.RuleType<TextRule> type, Text defaultValue) {
		super(type, defaultValue);
	}
}
