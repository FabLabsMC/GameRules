package io.github.fablabsmc.fablabs.mixin.gamerule;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.GameRules;

@Mixin(GameRules.class)
public interface GameRulesAccessor {
	@Invoker
	static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> invokeRegister(String name, GameRules.RuleCategory category, GameRules.RuleType<T> type) {
		throw new AssertionError("This shouldn't happen!");
	}

	@Accessor("RULE_TYPES")
	static Map<GameRules.RuleKey<?>, GameRules.RuleType<?>> getRuleTypes() {
		throw new AssertionError("This shouldn't happen!");
	}
}
