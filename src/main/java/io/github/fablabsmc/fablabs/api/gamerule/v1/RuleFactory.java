package io.github.fablabsmc.fablabs.api.gamerule.v1;

import java.util.function.BiConsumer;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.StringRule;
import io.github.fablabsmc.fablabs.impl.gamerule.RuleFactoryImpl;

import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

public interface RuleFactory {
	RuleFactory INSTANCE = RuleFactoryImpl.INSTANCE;

	default GameRules.RuleType<GameRules.BooleanRule> createBooleanRule(boolean defaultValue) {
		return this.createBooleanRule(defaultValue, (server, rule) -> {
		});
	}

	GameRules.RuleType<GameRules.BooleanRule> createBooleanRule(boolean defaultValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> notifier);

	default GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue) {
		return this.createIntRule(defaultValue, (server, rule) -> {
		});
	}

	default GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound) {
		return this.createIntRule(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	default GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, BiConsumer<MinecraftServer, GameRules.IntRule> notifier) {
		return this.createIntRule(defaultValue, lowerBound, Integer.MAX_VALUE, notifier);
	}

	default GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, int upperBound) {
		return this.createIntRule(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	default GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, BiConsumer<MinecraftServer, GameRules.IntRule> notifier) {
		return this.createIntRule(defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE, notifier);
	}

	GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, int upperBound, BiConsumer<MinecraftServer, GameRules.IntRule> notifier);

	default GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue) {
		return this.createDoubleRule(defaultValue, (server, rule) -> {
		});
	}

	default GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound) {
		return this.createDoubleRule(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	default GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, BiConsumer<MinecraftServer, DoubleRule> notifier) {
		return this.createDoubleRule(defaultValue, lowerBound, Integer.MAX_VALUE, notifier);
	}

	default GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, double upperBound) {
		return this.createDoubleRule(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	default GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, BiConsumer<MinecraftServer, DoubleRule> notifier) {
		return this.createDoubleRule(defaultValue, Double.MIN_VALUE, Double.MAX_VALUE, notifier);
	}

	GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, double upperBound, BiConsumer<MinecraftServer, DoubleRule> notifier);

	default GameRules.RuleType<FloatRule> createFloatRule(float defaultValue) {
		return this.createFloatRule(defaultValue, (server, rule) -> {
		});
	}

	default GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound) {
		return this.createFloatRule(defaultValue, lowerBound, Integer.MAX_VALUE, (server, rule) -> {
		});
	}

	default GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, BiConsumer<MinecraftServer, FloatRule> notifier) {
		return this.createFloatRule(defaultValue, lowerBound, Integer.MAX_VALUE, notifier);
	}

	default GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, float upperBound) {
		return this.createFloatRule(defaultValue, lowerBound, upperBound, (server, rule) -> {
		});
	}

	default GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, BiConsumer<MinecraftServer, FloatRule> notifier) {
		return this.createFloatRule(defaultValue, Float.MIN_VALUE, Float.MAX_VALUE, notifier);
	}

	GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, float upperBound, BiConsumer<MinecraftServer, FloatRule> notifier);

	default GameRules.RuleType<StringRule> createStringRule(String defaultValue) {
		return this.createStringRule(defaultValue, (server, rule) -> {
		});
	}

	GameRules.RuleType<StringRule> createStringRule(String defaultValue, BiConsumer<MinecraftServer, StringRule> notifier);

	default <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue) {
		return this.createEnumRule(defaultValue, (server, rule) -> {
		});
	}

	default <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, BiConsumer<MinecraftServer, EnumRule<E>> notifier) {
		return this.createEnumRule(defaultValue, defaultValue.getDeclaringClass().getEnumConstants(), notifier);
	}

	default <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues) {
		return this.createEnumRule(defaultValue, supportedValues, (server, rule) -> {
		});
	}

	<E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues, BiConsumer<MinecraftServer, EnumRule<E>> notifier);
}
