package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import java.util.Locale;

import io.github.fablabsmc.fablabs.api.gamerule.v1.FabricRuleTypeConsumer;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.DoubleRuleWidget;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.EnumRuleWidget;
import io.github.fablabsmc.fablabs.impl.gamerule.client.widget.FloatRuleWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.world.GameRules;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(targets = "net/minecraft/client/gui/screen/world/EditGameRulesScreen$RuleListWidget$1")
public abstract class RuleListWidgetRuleTypeConsumerMixin implements GameRules.RuleTypeConsumer, FabricRuleTypeConsumer {
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
			return new EnumRuleWidget<>(this.field_24314, name, description, ruleName, rule, key.getTranslationKey());
		});
	}

	/**
	 * @reason We need to display an enum rule's default value as translated.
	 */
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules$Rule;serialize()Ljava/lang/String;"), method = "createRuleWidget")
	private <T extends GameRules.Rule<T>> String displayProperEnumName(GameRules.Rule<T> rule, GameRules.RuleKey<T> key, EditGameRulesScreen.RuleWidgetFactory<T> widgetFactory) {
		if (rule instanceof EnumRule) {
			String translationKey = key.getTranslationKey() + "." + ((EnumRule<?>) rule).get().name().toLowerCase(Locale.ROOT);

			if (I18n.hasTranslation(translationKey)) {
				return I18n.translate(translationKey);
			}

			return ((EnumRule<?>) rule).get().toString();
		}

		return rule.serialize();
	}
}
