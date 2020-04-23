package io.github.fablabsmc.fablabs.impl.gamerule;

import java.util.function.BiConsumer;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.github.fablabsmc.fablabs.api.gamerule.v1.FabricRuleTypeConsumer;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.StringRule;
import io.github.fablabsmc.fablabs.impl.gamerule.rule.DoubleRuleImpl;
import io.github.fablabsmc.fablabs.impl.gamerule.rule.EnumRuleImpl;
import io.github.fablabsmc.fablabs.impl.gamerule.rule.FloatRuleImpl;
import io.github.fablabsmc.fablabs.impl.gamerule.rule.StringRuleImpl;
import io.github.fablabsmc.fablabs.mixin.gamerule.GameRules$BooleanRuleAccessor;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

public class RuleFactoryImpl implements RuleFactory {
	public static final RuleFactoryImpl INSTANCE = new RuleFactoryImpl();

	private RuleFactoryImpl() {
	}

	@Override
	public GameRules.RuleType<GameRules.BooleanRule> createBooleanRule(boolean defaultValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> notifier) {
		return GameRules$BooleanRuleAccessor.invokeCreate(defaultValue, notifier);
	}

	@Override
	public GameRules.RuleType<GameRules.IntRule> createIntRule(int defaultValue, int lowerBound, int upperBound, BiConsumer<MinecraftServer, GameRules.IntRule> notifier) {
		return new GameRules.RuleType<>(
				() -> IntegerArgumentType.integer(lowerBound, upperBound),
				type -> new GameRules.IntRule(type, defaultValue),
				notifier,
				GameRules.RuleTypeConsumer::method_27330
		);
	}

	@Override
	public GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, double upperBound, BiConsumer<MinecraftServer, DoubleRule> notifier) {
		return new GameRules.RuleType<>(
				() -> DoubleArgumentType.doubleArg(lowerBound, upperBound),
				type -> new DoubleRuleImpl(type, defaultValue),
				notifier,
				this::acceptDouble
		);
	}

	private void acceptDouble(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<DoubleRule> key, GameRules.RuleType<DoubleRule> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptDoubleRule(key, type);
		}
		// TODO: Figure out
	}

	@Override
	public GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, float upperBound, BiConsumer<MinecraftServer, FloatRule> notifier) {
		return new GameRules.RuleType<>(
				() -> FloatArgumentType.floatArg(lowerBound, upperBound),
				type -> new FloatRuleImpl(type, defaultValue),
				notifier,
				this::acceptFloat
		);
	}

	private void acceptFloat(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<FloatRule> key, GameRules.RuleType<FloatRule> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptFloatRule(key, type);
		}
		// TODO: Figure out
	}

	@Override
	public GameRules.RuleType<StringRule> createStringRule(String defaultValue, BiConsumer<MinecraftServer, StringRule> notifier) {
		return new GameRules.RuleType<>(
				StringArgumentType::string,
				type -> new StringRuleImpl(type, defaultValue),
				notifier,
				this::acceptString
		);
	}

	private void acceptString(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<StringRule> key, GameRules.RuleType<StringRule> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptStringRule(key, type);
		}
		// TODO: Figure out
	}

	@Override
	public <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues, BiConsumer<MinecraftServer, EnumRule<E>> notifier) {
		if (supportedValues.length == 0) {
			throw new IllegalArgumentException("Cannot register an enum rule where no values are supported");
		}

		return new EnumRuleType<>(
				type -> new EnumRuleImpl<>(type, defaultValue, supportedValues),
				notifier,
				supportedValues,
				this::acceptEnum
		);
	}

	private <E extends Enum<E>> void acceptEnum(GameRules.RuleTypeConsumer consumer, GameRules.RuleKey<EnumRule<E>> key, GameRules.RuleType<EnumRule<E>> type) {
		if (consumer instanceof FabricRuleTypeConsumer) {
			((FabricRuleTypeConsumer) consumer).acceptEnumRule(key, type);
		}
		// TODO: Figure out
	}
}
