package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import io.github.fablabsmc.fablabs.api.gamerule.v1.FabricRuleTypeConsumer;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.StringRule;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.DoubleRuleWidget;
import net.minecraft.class_5235;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net/minecraft/class_5235$class_5241$1") // net/minecraft/EditGameRulesScreen$class_5241$1
public abstract class MixinEditGameRulesScreen$class_5235$RuleTypeConsumer implements GameRules.RuleTypeConsumer, FabricRuleTypeConsumer {
	@SuppressWarnings("ShadowTarget")
	@Final
	@Shadow
	private class_5235 field_24314;
	@Shadow
	protected abstract <T extends GameRules.Rule<T>> void method_27640(GameRules.RuleKey<T> key, class_5235.class_5238<T> ruleWidgetFactory); // createRuleWidget

	@Override
	public void acceptDoubleRule(GameRules.RuleKey<DoubleRule> key, GameRules.RuleType<DoubleRule> type) {
		this.method_27640(key, (name, description, ruleName, rule) -> {
			return new DoubleRuleWidget(this.field_24314, name, description, ruleName, rule);
		});
	}

	@Override
	public void acceptFloatRule(GameRules.RuleKey<FloatRule> key, GameRules.RuleType<FloatRule> type) {

	}

	@Override
	public void acceptStringRule(GameRules.RuleKey<StringRule> key, GameRules.RuleType<StringRule> type) {

	}

	@Override
	public <E extends Enum<E>> void acceptEnumRule(GameRules.RuleKey<EnumRule<E>> key, GameRules.RuleType<EnumRule<E>> type) {

	}
}
