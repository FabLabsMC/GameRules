package io.github.fablabsmc.fablabs.impl.gamerule;

import io.github.fablabsmc.fablabs.api.gamerule.v1.GameRuleRegistry;
import io.github.fablabsmc.fablabs.api.gamerule.v1.RuleFactory;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;

@Deprecated
public class TestRules implements ModInitializer {
	public static final GameRules.RuleKey<EnumRule<Direction>> TEST = GameRuleRegistry.INSTANCE.register(new Identifier("test:enum_rule"), GameRules.class_5198.field_24100, RuleFactory.INSTANCE.createEnumRule(Direction.NORTH));
	public static final GameRules.RuleKey<DoubleRule> EST = GameRuleRegistry.INSTANCE.register(new Identifier("yeet"), GameRules.class_5198.field_24100, RuleFactory.INSTANCE.createDoubleRule(-69420));

	@Override
	public void onInitialize() {

	}
}
