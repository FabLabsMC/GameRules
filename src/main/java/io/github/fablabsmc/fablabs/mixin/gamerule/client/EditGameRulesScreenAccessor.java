package io.github.fablabsmc.fablabs.mixin.gamerule.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5235;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_5235.class)
@Environment(EnvType.CLIENT)
public interface EditGameRulesScreenAccessor {
	@Invoker("method_27626")
	void validate(class_5235.class_5240 ruleWidget);

	@Invoker("method_27620")
	void invalidate(class_5235.class_5240 ruleWidget);
}
