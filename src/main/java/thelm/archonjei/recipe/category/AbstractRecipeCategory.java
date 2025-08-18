package thelm.archonjei.recipe.category;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;

public abstract class AbstractRecipeCategory<R> implements IRecipeCategory<R> {

	public final RecipeType<R> recipeType;
	public final Component title;

	public AbstractRecipeCategory(RecipeType<R> recipeType, Component title) {
		this.recipeType = recipeType;
		this.title = title;
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
	public IDrawable getIcon() {
		return null;
	}

	public RegistryAccess registryAccess() {
		return Minecraft.getInstance().level.registryAccess();
	}

	public Font font() {
		return Minecraft.getInstance().font;
	}
}
