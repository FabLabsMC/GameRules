package io.github.fablabsmc.fablabs.test.gamerule;

import java.util.Arrays;

import io.github.fablabsmc.fablabs.api.gamerule.v1.CustomGameRuleCategory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;

import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TestRulesMod implements ModInitializer {
	public static final CustomGameRuleCategory GREEN_CATEGORY = new CustomGameRuleCategory(new Identifier("test", "green"),
			new LiteralText("This One is Green").styled(style -> style.withBold(true).withColor(Formatting.DARK_GREEN)));

	public static final CustomGameRuleCategory RED_CATEGORY = new CustomGameRuleCategory(new Identifier("test", "red"),
			new LiteralText("This One is Red").styled(style -> style.withBold(true).withColor(Formatting.DARK_RED)));

	public static final GameRules.RuleKey<GameRules.IntRule> TEST_INT_RULE = register("boundy", GameRules.RuleCategory.MISC, RuleFactory.createIntRule(2, 0));
	public static final GameRules.RuleKey<DoubleRule> TEST_DOB = register("bound2", GameRules.RuleCategory.MISC, RuleFactory.createDoubleRule(1, 1.0D, 10.0D));
	public static final GameRules.RuleKey<FloatRule> TEST_FLOAT = register("bound3", GameRules.RuleCategory.MISC, RuleFactory.createFloatRule(0.0F, 0.0F, 1.0F));
	public static final GameRules.RuleKey<EnumRule<Direction>> TEST_ENUM = register("dir", GameRules.RuleCategory.MISC, RuleFactory.createEnumRule(Direction.NORTH,
			Arrays.stream(Direction.values())
					.filter(direction -> direction != Direction.UP && direction != Direction.DOWN)
					.toArray(Direction[]::new)
	));

	public static final GameRules.RuleKey<GameRules.BooleanRule> TEST_BOOL_GREEN =
			register("green_bool", GREEN_CATEGORY, RuleFactory.createBooleanRule(false));

	public static final GameRules.RuleKey<GameRules.BooleanRule> TEST_BOOL_RED =
			register("red_bool", RED_CATEGORY, RuleFactory.createBooleanRule(false));

	public static final GameRules.RuleKey<EnumRule<Direction>> TEST_ENUM_RED =
			register("red_enum", RED_CATEGORY, RuleFactory.createEnumRule(Direction.NORTH));

	private static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String path, GameRules.RuleCategory category, GameRules.RuleType<T> type) {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			return GameRuleRegistry.register(new Identifier("test", path), category, type);
		} else {
			return null;
		}
	}

	private static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String path, CustomGameRuleCategory category, GameRules.RuleType<T> type) {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			return GameRuleRegistry.register(new Identifier("test", path), category, type);
		} else {
			return null;
		}
	}

	@Override
	public void onInitialize() {
		System.out.println("test");
	}
}
