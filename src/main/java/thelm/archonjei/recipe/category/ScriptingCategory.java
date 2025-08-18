package thelm.archonjei.recipe.category;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.crafting.Ingredient;
import safro.archon.client.screen.ScriptureTableScreen;
import safro.archon.recipe.ScriptingRecipe;
import safro.archon.registry.TagRegistry;
import thelm.archonjei.ArchonJEI;
import thelm.jeidrawables.gui.render.ResourceDrawable;

public class ScriptingCategory extends AbstractRecipeCategory<ScriptingRecipe> {

	public static final Component TITLE = new TranslatableComponent("rei.archon.scripting");

	public static final IDrawable SLOTS = new ResourceDrawable(ScriptureTableScreen.TEXTURE, 15, 16, 143, 59);
	public static final IDrawable LAPIS_BAR = new ResourceDrawable(ScriptureTableScreen.TEXTURE, 176, 29, 18, 4);

	public ScriptingCategory() {
		super(ArchonJEI.SCRIPTING, TITLE);
	}

	@Override
	public int getWidth() {
		return 143;
	}

	@Override
	public int getHeight() {
		return 59;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ScriptingRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 29, 42).addIngredients(recipe.getInputs().get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 64, 42).addIngredients(recipe.getInputs().get(1));
		builder.addSlot(RecipeIngredientRole.INPUT, 101, 42).addIngredients(recipe.getInputs().get(2));
		builder.addSlot(RecipeIngredientRole.INPUT, 125, 1).addIngredients(Ingredient.of(TagRegistry.BOOKS));
		builder.addSlot(RecipeIngredientRole.CATALYST, 2, 1).addIngredients(Ingredient.of(TagRegistry.LAPIS_LAZULIS));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 1).addItemStack(recipe.getResultItem());
	}

	@Override
	public void draw(ScriptingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
		SLOTS.draw(poseStack);
		LAPIS_BAR.draw(poseStack, 1, 23);
	}
}
