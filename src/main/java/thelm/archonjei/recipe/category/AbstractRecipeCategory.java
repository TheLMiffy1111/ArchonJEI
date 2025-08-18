package thelm.archonjei.recipe.category;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import thelm.jeidrawables.gui.render.BlankDrawable;

public abstract class AbstractRecipeCategory<R> implements IRecipeCategory<R> {

	public final RecipeType<R> recipeType;
	public final Component title;
	public final IDrawable background;

	public AbstractRecipeCategory(RecipeType<R> recipeType, Component title) {
		this.recipeType = recipeType;
		this.title = title;
		background = new BlankDrawable(getWidth(), getHeight());
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public RecipeType<R> getRecipeType() {
		return recipeType;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public abstract int getWidth();

	@Override
	public abstract int getHeight();

	@Override
	public IDrawable getIcon() {
		return null;
	}

	public Font font() {
		return Minecraft.getInstance().font;
	}
}
