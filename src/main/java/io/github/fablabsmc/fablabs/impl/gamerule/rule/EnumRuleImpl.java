package io.github.fablabsmc.fablabs.impl.gamerule.rule;

import static com.google.common.base.Preconditions.checkNotNull;

import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;

import net.minecraft.world.GameRules;

public class EnumRuleImpl<E extends Enum<E>> extends EnumRule<E> {
	public EnumRuleImpl(GameRules.RuleType<EnumRule<E>> type, E value, E[] supportedValues) {
		super(type, value, supportedValues);
	}

	@Override
	public void setValue(E value) throws IllegalArgumentException {
		checkNotNull(value);

		for (E supportedValue : this.supportedValues) {
			if (supportedValue.equals(value)) {
				this.value = value;
				return;
			}
		}

		throw new IllegalArgumentException("Tried to set an unsupported value: " + value.toString());
	}
}
