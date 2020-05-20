package io.github.fablabsmc.fablabs.impl.gamerule;

import java.util.HashMap;

import io.github.fablabsmc.fablabs.api.gamerule.v1.CustomGameRuleCategory;

import net.minecraft.world.GameRules;

public class RuleCategories {
	private static final HashMap<GameRules.RuleKey<?>, CustomGameRuleCategory> MAP = new HashMap<>();

	public static void putIfAbsent(GameRules.RuleKey<?> key, CustomGameRuleCategory value) {
		MAP.putIfAbsent(key, value);
	}

	public static boolean containsKey(GameRules.RuleKey<?> key) {
		return MAP.containsKey(key);
	}

	public static CustomGameRuleCategory get(GameRules.RuleKey<?> key) {
		return MAP.get(key);
	}
}
