package io.github.fablabsmc.fablabs.impl.gamerule;

import java.util.function.BiConsumer;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.StringRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.TextRule;
import io.github.fablabsmc.fablabs.mixin.gamerule.GameRules$BooleanRuleAccessor;

import net.minecraft.command.arguments.TextArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
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
		// fixme: use Invoker when issue is fixed: https://github.com/FabricMC/fabric-loom/issues/193
		return new GameRules.RuleType<>(
				//return GameRules$RuleTypeAccessor.invokeNew(
				() -> IntegerArgumentType.integer(lowerBound, upperBound),
				type -> new GameRules.IntRule(type, defaultValue),
				notifier
		);
	}

	@Override
	public GameRules.RuleType<DoubleRule> createDoubleRule(double defaultValue, double lowerBound, double upperBound, BiConsumer<MinecraftServer, DoubleRule> notifier) {
		// fixme: use Invoker when issue is fixed: https://github.com/FabricMC/fabric-loom/issues/193
		return new GameRules.RuleType<>(
				//return GameRules$RuleTypeAccessor.invokeNew(
				() -> DoubleArgumentType.doubleArg(lowerBound, upperBound),
				type -> new DoubleRule(type, defaultValue),
				notifier
		);
	}

	@Override
	public GameRules.RuleType<FloatRule> createFloatRule(float defaultValue, float lowerBound, float upperBound, BiConsumer<MinecraftServer, FloatRule> notifier) {
		// fixme: use Invoker when issue is fixed: https://github.com/FabricMC/fabric-loom/issues/193
		return new GameRules.RuleType<>(
				//return GameRules$RuleTypeAccessor.invokeNew(
				() -> FloatArgumentType.floatArg(lowerBound, upperBound),
				type -> new FloatRule(type, defaultValue),
				notifier
		);
	}

	@Override
	public GameRules.RuleType<TextRule> createTextRule(Text defaultValue, BiConsumer<MinecraftServer, TextRule> notifier) {
		// fixme: use Invoker when issue is fixed: https://github.com/FabricMC/fabric-loom/issues/193
		return new GameRules.RuleType<>(
				//return GameRules$RuleTypeAccessor.invokeNew(
				TextArgumentType::text,
				type -> new TextRule(type, defaultValue),
				notifier
		);
	}

	@Override
	public GameRules.RuleType<StringRule> createStringWordRule(String defaultValue, BiConsumer<MinecraftServer, StringRule> notifier) {
		// fixme: use Invoker when issue is fixed: https://github.com/FabricMC/fabric-loom/issues/193
		return new GameRules.RuleType<>(
				//return GameRules$RuleTypeAccessor.invokeNew(
				StringArgumentType::word,
				type -> new StringRule(type, defaultValue),
				notifier
		);
	}

	@Override
	public GameRules.RuleType<StringRule> createStringRule(String defaultValue, BiConsumer<MinecraftServer, StringRule> notifier) {
		// fixme: use Invoker when issue is fixed: https://github.com/FabricMC/fabric-loom/issues/193
		return new GameRules.RuleType<>(
				//return GameRules$RuleTypeAccessor.invokeNew(
				StringArgumentType::string,
				type -> new StringRule(type, defaultValue),
				notifier
		);
	}

	@Override
	public <E extends Enum<E>> GameRules.RuleType<EnumRule<E>> createEnumRule(E defaultValue, E[] supportedValues, BiConsumer<MinecraftServer, EnumRule<E>> notifier) {
		if (supportedValues.length == 0) {
			throw new IllegalArgumentException("Cannot register an enum rule where no values are supported");
		}

		return new EnumRuleType<>(
				type -> new EnumRule<>(type, defaultValue, supportedValues),
				notifier,
				supportedValues
		);
	}
}
