package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.fablabsmc.fablabs.api.gamerule.v1.CustomGameRuleCategory;
import io.github.fablabsmc.fablabs.impl.gamerule.RuleKeyInternals;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.world.GameRules;

@Mixin(EditGameRulesScreen.RuleListWidget.class)
public abstract class RuleListWidgetMixin extends net.minecraft.client.gui.widget.EntryListWidget<EditGameRulesScreen.AbstractRuleWidget> {
	@Unique
	private final Map<CustomGameRuleCategory, ArrayList<EditGameRulesScreen.AbstractRuleWidget>> fabricCategories = new HashMap<>();

	public RuleListWidgetMixin(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
	}

	@Inject(method = "<init>(Lnet/minecraft/world/GameRules;)V", at = @At("TAIL"))
	private void initializeFabricGameruleCategories(EditGameRulesScreen screen, GameRules gameRules, CallbackInfo ci) {
		fabricCategories.forEach((category, widgetList) -> {
			this.addEntry(screen.new RuleCategoryWidget(category.getName()));

			for (EditGameRulesScreen.AbstractRuleWidget widget : widgetList) {
				this.addEntry(widget);
			}
		});
	}

	@Inject(method = "method_27638(Ljava/util/Map$Entry;)V", at = @At("HEAD"), cancellable = true)
	private void ignoreKeysWithCustomCategories(Map.Entry<GameRules.RuleKey<?>, EditGameRulesScreen.AbstractRuleWidget> entry, CallbackInfo ci) {
		final GameRules.RuleKey<?> ruleKey = entry.getKey();
		final CustomGameRuleCategory customRuleCategory = ((RuleKeyInternals) (Object) ruleKey).fabric_getCustomCategory();

		if (customRuleCategory != null) {
			fabricCategories.computeIfAbsent(customRuleCategory, c -> new ArrayList<>()).add(entry.getValue());
			ci.cancel();
		}
	}
}
