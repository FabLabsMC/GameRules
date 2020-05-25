package io.github.fablabsmc.fablabs.mixin.gamerule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.CustomGameRuleCategory;
import io.github.fablabsmc.fablabs.impl.gamerule.RuleKeyInternals;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.world.GameRules;

@Mixin(GameRules.RuleKey.class)
public abstract class RuleKeyMixin implements RuleKeyInternals {
	/* @Nullable */
	@Unique
	private CustomGameRuleCategory customCategory;

	@Override
	public CustomGameRuleCategory fabric_getCustomCategory() {
		return this.customCategory;
	}

	@Override
	public void fabric_setCustomCategory(CustomGameRuleCategory customCategory) {
		this.customCategory = customCategory;
	}
}
