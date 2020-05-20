package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.FabricGameRuleCategory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Mixin(targets = "net/minecraft/client/gui/screen/world/EditGameRulesScreen$RuleListWidget")
public abstract class EditGameRulesScreen$RuleListWidgetMixin extends ElementListWidget<EditGameRulesScreen.AbstractRuleWidget> {
    private EditGameRulesScreen$RuleListWidgetMixin(MinecraftClient minecraftClient, int i, int j, int k, int l, int m) {
        super(minecraftClient, i, j, k, l, m);
    }

    private Map<FabricGameRuleCategory, ArrayList<EditGameRulesScreen.AbstractRuleWidget>> fabricCategories = new TreeMap<>();

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Inject(method = "<init>", at = @At("TAIL"))
    private void initializeFabricGameruleCategories(EditGameRulesScreen screen, GameRules gameRules, CallbackInfo ci) {
        fabricCategories.forEach((category, widgetList) -> {
            this.addEntry(screen.new RuleCategoryWidget(category.getName()));
            for (EditGameRulesScreen.AbstractRuleWidget widget : widgetList) {
                this.addEntry(widget);
            }
        });
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "method_27638(Ljava/util/Map$Entry;)V", at = @At("HEAD"), cancellable = true)
    private void dontShowFabric(Map.Entry<GameRules.RuleKey<?>, EditGameRulesScreen.AbstractRuleWidget> entry, CallbackInfo ci) {
        if (GameRuleRegistry.MAP.containsKey(entry.getKey())) {
            FabricGameRuleCategory category = GameRuleRegistry.MAP.get(entry.getKey());
            fabricCategories.putIfAbsent(category, new ArrayList<>());
            fabricCategories.get(category).add(entry.getValue());
            ci.cancel();
        }
    }
}
