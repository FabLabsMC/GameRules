package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import java.util.function.Supplier;

import io.github.fablabsmc.fablabs.impl.gamerule.GameRuleRegistryImpl;

import io.github.fablabsmc.fablabs.impl.gamerule.rule.EnumRuleImpl;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

public abstract class EnumRule<E extends Enum<E>> extends LiteralRule<EnumRule<E>> implements Supplier<E> {
	private final Class<E> classType;
	protected final E[] supportedValues;
	protected E value;

	protected EnumRule(GameRules.RuleType<EnumRule<E>> type, E value, E[] supportedValues) {
		super(type);
		this.classType = value.getDeclaringClass();
		this.value = value;
		this.supportedValues = supportedValues;
	}

	@Override
	protected void deserialize(String value) {
		int ordinal = parseInt(value);
		E[] possibleValues = this.classType.getEnumConstants();

		if (possibleValues.length <= ordinal + 1) { // Our ordinal doesn't exist, log the issue
			GameRuleRegistryImpl.LOGGER.warn("Failed to parse int {} for rule of type {}. Since it's ordinal is not present", ordinal, this.classType);
			return;
		}

		try {
			this.setValue(possibleValues[ordinal]);
		} catch (IllegalArgumentException e) { // Not a supported value
			GameRuleRegistryImpl.LOGGER.warn("Failed to parse int {} for rule of type {}. {}", ordinal, this.classType, e);
		}
	}

	private int parseInt(String string) {
		if (!string.isEmpty()) {
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException e) {
				GameRuleRegistryImpl.LOGGER.warn("Failed to parse int {} for rule of type {}", string, this.classType);
			}
		}

		return 0;
	}

	@Override
	public String serialize() {
		return Integer.toString(this.value.ordinal());
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected EnumRule<E> getThis() {
		return this;
	}

	public Class<E> getEnumClass() {
		return this.classType;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	@Override
	protected EnumRule<E> method_27338() {
		return new EnumRuleImpl<>(this.type, this.value, this.supportedValues);
	}

	@Override
	public void method_27337(EnumRule<E> rule, MinecraftServer minecraftServer) {
		this.value = rule.value;
		this.changed(minecraftServer);
	}

	@Override
	public E get() {
		return this.value;
	}

	protected abstract void setValue(E value) throws IllegalArgumentException;
}
