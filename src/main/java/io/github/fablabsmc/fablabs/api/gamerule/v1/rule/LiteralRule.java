package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;

public abstract class LiteralRule<T extends GameRules.Rule<T>> extends GameRules.Rule<T> {
	protected LiteralRule(GameRules.RuleType<T> type) {
		super(type);
	}

	@Override
	protected final void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		// Do nothing. We use a different system for application
	}
}
