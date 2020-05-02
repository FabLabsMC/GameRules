package io.github.fablabsmc.fablabs.impl.gamerule;

import java.util.Arrays;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;

import net.fabricmc.api.ModInitializer;

@Deprecated
public class TestRules implements ModInitializer {
	public static final GameRules.RuleKey<GameRules.IntRule> TEST_INT_RULE = register("boundy", GameRules.RuleCategory.MISC, RuleFactory.createIntRule(2, 0));
	public static final GameRules.RuleKey<DoubleRule> TEST_DOB = register("bound2", GameRules.RuleCategory.MISC, RuleFactory.createDoubleRule(1, 1.0D, 10.0D));
	public static final GameRules.RuleKey<FloatRule> TEST_FLOAT = register("bound3", GameRules.RuleCategory.MISC, RuleFactory.createFloatRule(0.0F, 0.0F, 1.0F));
	public static final GameRules.RuleKey<EnumRule<Direction>> TEST_ENUM = register("dir", GameRules.RuleCategory.MISC, RuleFactory.createEnumRule(Direction.NORTH,
			Arrays.stream(Direction.values())
					.filter(direction -> {
						return direction != Direction.UP && direction != Direction.DOWN;
					})
					.toArray(Direction[]::new)
	));

	@Override
	public void onInitialize() {
	}

	private static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String path, GameRules.RuleCategory category, GameRules.RuleType<T> type) {
		return GameRuleRegistry.register(new Identifier("test", path), category, type);
	}
}
