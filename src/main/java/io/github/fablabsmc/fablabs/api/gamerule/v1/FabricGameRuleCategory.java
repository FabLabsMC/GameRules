package io.github.fablabsmc.fablabs.api.gamerule.v1;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Utility class for registering GameRule objects with custom categories outside of the categories Minecraft provides.
 */
public class FabricGameRuleCategory implements Comparable<FabricGameRuleCategory> {
	private final Identifier id;
	private final Text name;

	public FabricGameRuleCategory(Identifier id, Text name) {
		this.id = id;
		this.name = name;
	}

	public Text getName() {
		return this.name;
	}

	@Override
	public int compareTo(FabricGameRuleCategory category) {
		return this.id.compareTo(category.id);
	}
}
