package io.github.fablabsmc.fablabs.impl.gamerule;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.LiteralRule;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public abstract class LiteralRuleType<T extends LiteralRule<T>> extends GameRules.RuleType<T> {
	public LiteralRuleType(Supplier<ArgumentType<?>> argumentType, Function<GameRules.RuleType<T>, T> ruleFactory, BiConsumer<MinecraftServer, T> changeCallback, GameRules.RuleAcceptor<T> acceptor) {
		super(argumentType, ruleFactory, changeCallback, acceptor);
	}

	@Override
	@Deprecated
	public final RequiredArgumentBuilder<ServerCommandSource, ?> argument(String name) {
		return super.argument(name);
	}

	/**
	 * Literal Rule types should implement this method in order to register their nodes on the GameRule command.
	 */
	public abstract void register(LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder, GameRules.RuleKey<T> key);
}
