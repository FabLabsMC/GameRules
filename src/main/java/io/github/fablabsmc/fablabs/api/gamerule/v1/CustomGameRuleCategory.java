package io.github.fablabsmc.fablabs.api.gamerule.v1;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Utility class for registering GameRule objects with custom categories outside of the categories Minecraft provides.
 */
public final class CustomGameRuleCategory {
	public static CustomGameRuleCategory of(Identifier id, Text name) {
		return new CustomGameRuleCategory(id, name);
	}

	private final Identifier id;
	private final Text name;

	private CustomGameRuleCategory(Identifier id, Text name) {
		this.id = id;
		this.name = name;
	}

	public Text getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CustomGameRuleCategory that = (CustomGameRuleCategory) o;

		return this.id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
