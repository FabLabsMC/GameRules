package io.github.fablabsmc.fablabs.impl.gamerule.client.widget;

import com.google.common.collect.ImmutableList;
import io.github.fablabsmc.fablabs.api.gamerule.v1.rule.FloatRule;
import io.github.fablabsmc.fablabs.mixin.gamerule.client.EditGameRulesScreenAccessor;
import io.github.fablabsmc.fablabs.mixin.gamerule.client.ScreenAccessor;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.world.EditGameRulesScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.List;

public class FloatRuleWidget extends EditGameRulesScreen.AbstractRuleWidget {
	private final List<? extends Element> children;
	private final TextFieldWidget textFieldWidget;
	private final Text name;
	private final EditGameRulesScreen screen;

	public FloatRuleWidget(EditGameRulesScreen gameRuleScreen, Text name, List<Text> description, final String ruleName, FloatRule rule) {
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

		this.textFieldWidget.setText(Float.toString(rule.getAsFloat()));
		this.textFieldWidget.setChangedListener(value -> {
			if (rule.validate(value)) {
				this.textFieldWidget.setEditableColor(14737632);
				accessor.markValid(this);
			} else {
				this.textFieldWidget.setEditableColor(16711680);
				accessor.markInvalid(this);
			}

		});

		this.children = ImmutableList.of(this.textFieldWidget);
	}

	@Override
	public List<? extends Element> children() {
		return this.children;
	}

	@Override
	public void render(MatrixStack matrices, int x, int y, int width, int height, int mouseX, int mouseY, int i, boolean bl, float tickDelta) {
		ScreenAccessor accessor = (ScreenAccessor) this.screen;
		accessor.getClient().textRenderer.draw(matrices, this.name, width, (y + 5), 16777215);

		this.textFieldWidget.x = width + height - 44;
		this.textFieldWidget.y = y;
		this.textFieldWidget.render(matrices, mouseY, i, tickDelta);
	}
}
