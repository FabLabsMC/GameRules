package io.github.fablabsmc.fablabs.impl.gamerule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;

@Deprecated
public class TestRules implements ModInitializer {
	public static final GameRules.RuleKey<EnumRule<Direction>> TEST = GameRuleRegistry.INSTANCE.register(new Identifier("test:enum_rule"), RuleFactory.INSTANCE.createEnumRule(Direction.NORTH));

	@Override
	public void onInitialize() {
	}
}
