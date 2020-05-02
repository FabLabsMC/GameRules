package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.screen.world.EditGameRulesScreen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Mixin(EditGameRulesScreen.class)
@Environment(EnvType.CLIENT)
public interface EditGameRulesScreenAccessor {
	@Invoker("markValid")
	void callMarkValid(EditGameRulesScreen.AbstractRuleWidget ruleWidget);

	@Invoker("markInvalid")
	void callMarkInvalid(EditGameRulesScreen.AbstractRuleWidget ruleWidget);
}
