package io.github.fablabsmc.fablabs.mixin.gamerule;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.GameRules;

@Mixin(GameRules.IntRule.class)
public interface GameRules$IntRuleAccessor {
	@Accessor
	int getValue();

	@Accessor
	void setValue(int value);
}
