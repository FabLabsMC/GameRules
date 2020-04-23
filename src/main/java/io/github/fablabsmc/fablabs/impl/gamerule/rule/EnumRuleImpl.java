package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import static com.google.common.base.Preconditions.checkNotNull;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

import java.util.Collection;

public class EnumRuleImpl<E extends Enum<E>> extends EnumRule<E> {
	public EnumRuleImpl(GameRules.RuleType<EnumRule<E>> type, E value, E[] supportedValues) {
		super(type, value, supportedValues);
	}

	public EnumRuleImpl(GameRules.RuleType<EnumRule<E>> type, E value, Collection<E> supportedValues) {
		super(type, value, supportedValues);
	}

	@Override
	public void set(E value, /* @Nullable */ MinecraftServer server) throws IllegalArgumentException {
		checkNotNull(value);

		if (!this.supports(value)) {
			throw new IllegalArgumentException("Tried to set an unsupported value: " + value.toString());
		}

		this.value = value;
		this.changed(server);
	}
}
