package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EditGameRulesScreen.class)
@Environment(EnvType.CLIENT)
public interface EditGameRulesScreenAccessor {
	@Invoker("markValid")
	void markValid(EditGameRulesScreen.AbstractRuleWidget ruleWidget);

	@Invoker("markInvalid")
	void markInvalid(EditGameRulesScreen.AbstractRuleWidget ruleWidget);
}
