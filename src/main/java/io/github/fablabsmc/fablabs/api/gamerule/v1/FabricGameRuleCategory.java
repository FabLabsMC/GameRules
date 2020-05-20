package io.github.fablabsmc.fablabs.api.gamerule.v1;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * Utility class for registering GameRule objects with custom categories outside of the seven categories MC provides
 */
public class FabricGameRuleCategory implements Comparable<FabricGameRuleCategory>{
    private final Text category;

    public FabricGameRuleCategory(String category) {
        this.category = new LiteralText(category).styled((style) -> style.withBold(true).withColor(Formatting.YELLOW));
    }

    public FabricGameRuleCategory(Text category) {
        this.category = category;
    }

    @Environment(EnvType.CLIENT)
    public Text getName() {
        return this.category;
    }

    @Override
    public int compareTo(FabricGameRuleCategory category) {
        return this.category.asString().compareTo(category.category.asString());
    }
}
