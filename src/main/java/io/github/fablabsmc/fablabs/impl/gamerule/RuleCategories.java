package io.github.fablabsmc.fablabs.impl.gamerule;

import java.util.HashMap;

import io.github.fablabsmc.fablabs.api.gamerule.v1.FabricGameRuleCategory;

import net.minecraft.world.GameRules;

public class RuleCategories {
	private static final HashMap<GameRules.RuleKey<?>, FabricGameRuleCategory> MAP = new HashMap<>();

	public static void putIfAbsent(GameRules.RuleKey<?> key, FabricGameRuleCategory value) {
		MAP.putIfAbsent(key, value);
	}

	public static boolean containsKey(GameRules.RuleKey<?> key) {
		return MAP.containsKey(key);
	}

	public static FabricGameRuleCategory get(GameRules.RuleKey<?> key) {
		return MAP.get(key);
	}
}
