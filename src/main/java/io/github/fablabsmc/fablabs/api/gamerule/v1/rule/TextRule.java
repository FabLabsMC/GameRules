package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import java.util.function.Supplier;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

public abstract class TextRule extends GameRules.Rule<TextRule> implements Supplier<Text> {
	private Text value;

	protected TextRule(GameRules.RuleType<TextRule> type, Text defaultValue) {
		super(type);
		this.value = defaultValue;
	}

	@Override
	protected void setFromArgument(CommandContext<ServerCommandSource> context, String name) {
		this.value = context.getArgument(name, Text.class);
	}

	@Override
	protected void deserialize(String value) {
		this.value = Text.Serializer.fromJson(value);
	}

	@Override
	protected String serialize() {
		return Text.Serializer.toJson(this.value);
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected TextRule getThis() {
		return this;
	}

	@Override
	public Text get() {
		return this.value;
	}
}
