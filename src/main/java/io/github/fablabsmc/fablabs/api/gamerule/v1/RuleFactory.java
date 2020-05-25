package io.github.fablabsmc.fablabs.api.gamerule.v1;

import java.util.function.BiConsumer;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.impl.gamerule.EnumRuleType;
import io.github.fablabsmc.fablabs.impl.gamerule.rule.BoundedIntRule;
import io.github.fablabsmc.fablabs.mixin.gamerule.BooleanRuleAccessor;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

public final class RuleFactory {
	private RuleFactory() {
	}

	public static GameRules.RuleType<GameRules.BooleanRule> createBooleanRule(boolean defaultValue) {
		return createBooleanRule(defaultValue, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<GameRules.BooleanRule> createBooleanRule(boolean defaultValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> changedCallback) {
		return BooleanRuleAccessor.invokeCreate(defaultValue, changedCallback);
	}

	public static GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue) {
		return createIntRule(defaultValue, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound) {
		return createIntRule(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, BiConsumer<MinecraftServer, GameRules.IntRule> changedCallback) {
		return createIntRule(defaultValue, lowerBound, Integer.MAX_VALUE, changedCallback);
	}

	public static GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, int upperBound) {
		return createIntRule(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, BiConsumer<MinecraftServer, GameRules.IntRule> changedCallback) {
		return createIntRule(defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE, changedCallback);
	}

	public static GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, int upperBound, BiConsumer<MinecraftServer, GameRules.IntRule> changedCallback) {
		return new GameRules.RuleType<>(
				() -> IntegerArgumentType.integer(lowerBound, upperBound),
				type -> new BoundedIntRule(type, defaultValue, lowerBound, upperBound), // Internally use a bounded int rule
				changedCallback,
				GameRules.RuleTypeConsumer::acceptInt
		);
	}

	public static GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue) {
		return createDoubleRule(defaultValue, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound) {
		return createDoubleRule(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, BiConsumer<MinecraftServer, DoubleRule> changedCallback) {
		return createDoubleRule(defaultValue, lowerBound, Integer.MAX_VALUE, changedCallback);
	}

	public static GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, double upperBound) {
		return createDoubleRule(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, BiConsumer<MinecraftServer, DoubleRule> changedCallback) {
		return createDoubleRule(defaultValue, Double.MIN_VALUE, Double.MAX_VALUE, changedCallback);
	}

	public static GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, double upperBound, BiConsumer<MinecraftServer, DoubleRule> changedCallback) {
		return new GameRules.RuleType<>(
				() -> DoubleArgumentType.doubleArg(lowerBound, upperBound),
				type -> new DoubleRule(type, defaultValue, lowerBound, upperBound),
				changedCallback,
				RuleFactory::acceptDouble
		);
	}

	public static GameRules.RuleType<FloatRule> createFloatRule(float defaultValue) {
		return createFloatRule(defaultValue, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound) {
		return createFloatRule(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, BiConsumer<MinecraftServer, FloatRule> changedCallback) {
		return createFloatRule(defaultValue, lowerBound, Integer.MAX_VALUE, changedCallback);
	}

	public static GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, float upperBound) {
		return createFloatRule(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	public static GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, BiConsumer<MinecraftServer, FloatRule> changedCallback) {
		return createFloatRule(defaultValue, Float.MIN_VALUE, Float.MAX_VALUE, changedCallback);
	}

	public static GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, float upperBound, BiConsumer<MinecraftServer, FloatRule> changedCallback) {
		return new GameRules.RuleType<>(
				() -> FloatArgumentType.floatArg(lowerBound, upperBound),
				type -> new FloatRule(type, defaultValue, lowerBound, upperBound),
				changedCallback,
				RuleFactory::acceptFloat
		);
	}

	public static <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue) {
		return createEnumRule(defaultValue, (server, rule) -> {
		});
	}

	public static <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, BiConsumer<MinecraftServer, EnumRule<E>> changedCallback) {
		return createEnumRule(defaultValue, defaultValue.getDeclaringClass().getEnumConstants(), changedCallback);
	}

	public static <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues) {
		return createEnumRule(defaultValue, supportedValues, (server, rule) -> {
		});
	}

	public static <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues, BiConsumer<MinecraftServer, EnumRule<E>> changedCallback) {
		if (supportedValues.length == 0) {
			throw new IllegalArgumentException("Cannot register an enum rule where no values are supported");
		}

		return new EnumRuleType<>(
				type -> new EnumRule<>(type, defaultValue, supportedValues),
				changedCallback,
				supportedValues,
				RuleFactory::acceptEnum
		);
	}

	// RULE ACCEPTORS

	private static void acceptDouble(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<DoubleRule> key, GameRules.RuleType<DoubleRule> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptDoubleRule(key, type);
		}

		// If we don't have a FabricRuleTypeConsumer, do nothing
	}

	private static void acceptFloat(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<FloatRule> key, GameRules.RuleType<FloatRule> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptFloatRule(key, type);
		}

		// If we don't have a FabricRuleTypeConsumer, do nothing
	}

	private static <E extends Enum<E>> void acceptEnum(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<EnumRule<E>> key, GameRules.RuleType<EnumRule<E>> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptEnumRule(key, type);
		}

		// If we don't have a FabricRuleTypeConsumer, do nothing
	}
}
