package io.github.fablabsmc.fablabs.impl.gamerule.client.widget;

import java.util.List;

import com.google.common.collect.ImmutableList;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.DoubleRule;
import io.github.fablabsmc.fablabs.mixin.gamerule.client.EditGameRulesScreenAccessor;
import io.github.fablabsmc.fablabs.mixin.gamerule.client.ScreenAccessor;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DoubleRuleWidget extends EditGameRulesScreen.AbstractRuleWidget {
	private final List<? extends Element> children;
	private final TextFieldWidget textFieldWidget;
	private final Text name;
	private final EditGameRulesScreen screen;

	public DoubleRuleWidget(EditGameRulesScreen gameRuleScreen, Text name, List<Text> description, final String ruleName, DoubleRule rule) {
		gameRuleScreen.super(description);
		this.screen = gameRuleScreen;
		EditGameRulesScreenAccessor accessor = (EditGameRulesScreenAccessor) gameRuleScreen;
		ScreenAccessor screenAccessor = (ScreenAccessor) gameRuleScreen;
		this.name = name;

		this.textFieldWidget = new TextFieldWidget(screenAccessor.getClient().textRenderer, 10, 5, 42, 20,
				name.shallowCopy()
				.append("\n")
				.append(ruleName)
				.append("\n")
		);

		this.textFieldWidget.setText(Double.toString(rule.getAsDouble()));
		this.textFieldWidget.setChangedListener(value -> {
			if (rule.validate(value)) {
				this.textFieldWidget.setEditableColor(14737632);
				accessor.callMarkValid(this);
			} else {
				this.textFieldWidget.setEditableColor(16711680);
				accessor.callMarkInvalid(this);
			}
		});

		this.children = ImmutableList.of(this.textFieldWidget);
	}

	@Override
	public List<? extends Element> children() {
		return this.children;
	}

	@Override
	public void render(MatrixStack matrixStack, int x, int y, int width, int height, int mouseX, int mouseY, int i, boolean bl, float delta) {
		ScreenAccessor accessor = (ScreenAccessor) this.screen;
		accessor.getClient().textRenderer.draw(matrixStack, this.name, width, (y + 5), 16777215);

		this.textFieldWidget.x = width + height - 44;
		this.textFieldWidget.y = y;
		this.textFieldWidget.render(matrixStack, mouseY, i, delta);
	}
}
