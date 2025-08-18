package thelm.archonjei.recipe.category;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import safro.archon.recipe.ChannelingRecipe;
import thelm.archonjei.ArchonJEI;
import thelm.jeidrawables.JEIDrawables;

public class ChannelingCategory extends AbstractRecipeCategory<ChannelingRecipe> {

	public static final Component TITLE = Component.translatable("rei.archon.channeling");

	public ChannelingCategory() {
		super(ArchonJEI.CHANNELING, TITLE);
	}

	@Override
	public int getWidth() {
		return 78;
	}

	@Override
	public int getHeight() {
		return 38;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ChannelingRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 5).addItemStacks(recipe.getInputs()).setBackground(JEIDrawables.SLOT, -1, -1);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 5).addItemStack(recipe.getResultItem()).setBackground(JEIDrawables.OUTPUT_SLOT, -5, -5);
	}

	@Override
	public void draw(ChannelingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
		JEIDrawables.RECIPE_ARROW.draw(poseStack, 24, 4);
		Font font = font();
		Component costComponent = Component.translatable("text.archon.mana_cost", recipe.getManaCost());
		font.draw(poseStack, costComponent, getWidth() / 2 - font.width(costComponent) / 2, getHeight() - font.lineHeight, 0x00AAAA);
	}
}
