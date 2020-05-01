package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import io.github.fablabsmc.fablabs.api.gamerule.v1.FabricRuleTypeConsumer;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.DoubleRuleWidget;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.EnumRuleWidget;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.FloatRuleWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(targets = "net/minecraft/client/gui/screen/world/EditGameRulesScreen$RuleListWidget$1")
public abstract class MixinEditGameRulesScreen$RuleListWidget$RuleTypeConsumer implements GameRules.RuleTypeConsumer, FabricRuleTypeConsumer {
	@Final
	@Shadow
	private EditGameRulesScreen field_24314;
	@Shadow
	protected abstract <T extends GameRules.Rule<T>> void createRuleWidget(GameRules.RuleKey<T> key, EditGameRulesScreen.RuleWidgetFactory<T> ruleWidgetFactory); // createRuleWidget

	@Override
	public void acceptDoubleRule(GameRules.RuleKey<DoubleRule> key, GameRules.RuleType<DoubleRule> type) {
		this.createRuleWidget(key, (name, description, ruleName, rule) -> {
			return new DoubleRuleWidget(this.field_24314, name, description, ruleName, rule);
		});
	}

	@Override
	public void acceptFloatRule(GameRules.RuleKey<FloatRule> key, GameRules.RuleType<FloatRule> type) {
		this.createRuleWidget(key, (name, description, ruleName, rule) -> {
			return new FloatRuleWidget(this.field_24314, name, description, ruleName, rule);
		});
	}

	@Override
	public <E extends Enum<E>> void acceptEnumRule(GameRules.RuleKey<EnumRule<E>> key, GameRules.RuleType<EnumRule<E>> type) {
		this.createRuleWidget(key, (name, description, ruleName, rule) -> {
			return new EnumRuleWidget<>(this.field_24314, name, description, ruleName, rule);
		});
	}
}
