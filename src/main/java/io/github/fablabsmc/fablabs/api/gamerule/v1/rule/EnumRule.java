package io.github.fablabsmc.fablabs.api.gamerule.v1.rule;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Supplier;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

public class EnumRule<E extends Enum<E>> extends LiteralRule<EnumRule<E>> implements Supplier<E> {
	private static final Logger LOGGER = LogManager.getLogger(GameRuleRegistry.class);

	private final Class<E> classType;
	protected final Collection<E> supportedValues;
	protected E value;

	@Deprecated
	public EnumRule(GameRules.RuleType<EnumRule<E>> type, E value, E[] supportedValues) {
		this(type, value, Arrays.asList(supportedValues));
	}

	@Deprecated
	public EnumRule(GameRules.RuleType<EnumRule<E>> type, E value, Collection<E> supportedValues) {
		super(type);
		this.classType = value.getDeclaringClass();
		this.value = value;
		this.supportedValues = Collections.unmodifiableCollection(supportedValues);
	}

	@Override
	protected void deserialize(String value) {
		int ordinal = parseInt(value);
		E[] possibleValues = this.classType.getEnumConstants();

		if (possibleValues.length <= ordinal + 1) { // Our ordinal doesn't exist, log the issue
			LOGGER.warn("Failed to parse int {} for rule of type {}. Since it's ordinal is not present", ordinal, this.classType);
			return;
		}

		if (!this.supports(possibleValues[ordinal])) {
			LOGGER.warn("Failed to parse int {} for rule of type {}.", ordinal, this.classType);
		}

		this.set(possibleValues[ordinal], null);
	}

	private int parseInt(String string) {
		if (!string.isEmpty()) {
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException e) {
				LOGGER.warn("Failed to parse int {} for rule of type {}", string, this.classType);
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
	protected EnumRule<E> copy() {
		return new EnumRule<E>(this.type, this.value, this.supportedValues);
	}

	@Override
	public void setValue(EnumRule<E> rule, MinecraftServer minecraftServer) {
		this.value = rule.value;
		this.changed(minecraftServer);
	}

	@Override
	public E get() {
		return this.value;
	}

	public E cycle(E start) {
		if (this.supportedValues.size() > 1) {
			E value = getNext(this.supportedValues, start);

			return value;
			//if (!supportedValues.contains(value)) {
			//		return this.cycle(value);
			//}
		}

		return start;
	}

	protected static <T> T getNext(Collection<T> values, T value) {
		Iterator<T> iterator = values.iterator();

		do {
			if (!iterator.hasNext()) {
				return iterator.next();
			}
		} while (!iterator.next().equals(value));

		if (iterator.hasNext()) {
			return iterator.next();
		} else {
			return values.iterator().next();
		}
	}

	public boolean supports(E value) {
		for (E supportedValue : this.supportedValues) {
			if (value == supportedValue) {
				return true;
			}
		}

		return false;
	}

	public void set(E value, /* @Nullable */ MinecraftServer server) throws IllegalArgumentException {
		checkNotNull(value);

		if (!this.supports(value)) {
			throw new IllegalArgumentException("Tried to set an unsupported value: " + value.toString());
		}

		this.value = value;
		this.changed(server);
	}
}
