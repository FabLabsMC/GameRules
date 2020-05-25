package io.github.fablabsmc.fablabs.test.gamerule;

import java.util.Arrays;

import io.github.fablabsmc.fablabs.api.gamerule.v1.CustomGameRuleCategory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;

import net.fabricmc.api.ModInitializer;

public class TestRulesMod implements ModInitializer {
	public static final CustomGameRuleCategory GREEN_CATEGORY = CustomGameRuleCategory.of(new Identifier("test", "green"),
			new LiteralText("This One is Green").styled(style -> style.withBold(true).withColor(Formatting.DARK_GREEN)));

	public static final CustomGameRuleCategory RED_CATEGORY = CustomGameRuleCategory.of(new Identifier("test", "red"),
			new LiteralText("This One is Red").styled(style -> style.withBold(true).withColor(Formatting.DARK_RED)));

	public static final GameRules.RuleKey<GameRules.IntRule> TEST_INT_RULE = register("test_positive_only_int_rule", GameRules.RuleCategory.MISC, RuleFactory.createIntRule(2, 0));
	public static final GameRules.RuleKey<DoubleRule> TEST_DOB = register("test_one_to_ten_double_rule", GameRules.RuleCategory.MISC, RuleFactory.createDoubleRule(1, 1.0D, 10.0D));
	public static final GameRules.RuleKey<FloatRule> TEST_FLOAT = register("test_zero_to_one_float_rule", GameRules.RuleCategory.MISC, RuleFactory.createFloatRule(0.0F, 0.0F, 1.0F));
	public static final GameRules.RuleKey<EnumRule<Direction>> TEST_ENUM = register("test_cardinal_direction_rule", GameRules.RuleCategory.MISC, RuleFactory.createEnumRule(Direction.NORTH, Arrays.stream(Direction.values()).filter(direction -> direction != Direction.UP && direction != Direction.DOWN).toArray(Direction[]::new)));

	public static final GameRules.RuleKey<GameRules.BooleanRule> TEST_BOOL_GREEN = register("green_boolean_rule", GREEN_CATEGORY, RuleFactory.createBooleanRule(false));
	public static final GameRules.RuleKey<GameRules.BooleanRule> TEST_BOOL_RED = register("red_boolean_rule", RED_CATEGORY, RuleFactory.createBooleanRule(false));
	public static final GameRules.RuleKey<EnumRule<PlayerEntity.SleepFailureReason>> TEST_ENUM_RED = register("red_sleep_failure_reason_enum_rule", RED_CATEGORY, RuleFactory.createEnumRule(PlayerEntity.SleepFailureReason.NOT_POSSIBLE_HERE));

	private static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String name, GameRules.RuleCategory category, GameRules.RuleType<T> type) {
		return GameRuleRegistry.register(name, category, type);
	}

	private static <T extends GameRules.Rule<T>> GameRules.RuleKey<T> register(String name, CustomGameRuleCategory category, GameRules.RuleType<T> type) {
		return GameRuleRegistry.register(name, category, type);
	}

	@Override
	public void onInitialize() {
		System.out.println("Loading GameRules test mod");
	}
}
