package io.github.fablabsmc.fablabs.impl.gamerule.client.widget;

import java.util.List;

import com.google.common.collect.ImmutableList;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.EnumRule;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class EnumRuleWidget<E extends Enum<E>> extends EditGameRulesScreen.AbstractRuleWidget {
	private final List<? extends Element> children;
	private final ButtonWidget buttonWidget;

	public EnumRuleWidget(EditGameRulesScreen gameRuleScreen, Text name, List<Text> description, final String ruleName, EnumRule<E> rule) {
		gameRuleScreen.super(description);
		this.buttonWidget = new ButtonWidget(10, 5, 220, 20, this.method_27634(name, rule.get()), (buttonWidget) -> {
			E value = rule.get();

			// Cycle to the next value
			E newValue = rule.cycle(value);
			rule.set(newValue, null);
			buttonWidget.setMessage(this.method_27634(name, newValue));
		});

		this.children = ImmutableList.of(this.buttonWidget);
	}

	private Text method_27634(Text text, E value) {
		return text.shallowCopy().append(": ").append(new LiteralText(value.toString()));
	}

	public void render(MatrixStack matrixStack, int x, int y, int width, int height, int mouseX, int mouseY, int i, boolean bl, float delta) {
		this.buttonWidget.x = width;
		this.buttonWidget.y = y;
		this.buttonWidget.render(matrixStack, mouseY, i, delta);
	}

	@Override
	public List<? extends Element> children() {
		return this.children;
	}
}
