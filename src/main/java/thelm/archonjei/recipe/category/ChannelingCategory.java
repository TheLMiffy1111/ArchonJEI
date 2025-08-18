package thelm.archonjei.recipe.category;

import java.util.List;

import com.google.common.collect.Streams;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
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
		List<ItemStack> inputs;
		if(recipe.getTag() != null) {
			inputs = Streams.stream(BuiltInRegistries.BLOCK.getTagOrEmpty(recipe.getTag())).map(Holder::value).map(ItemStack::new).toList();
		}
		else {
			inputs = List.of(new ItemStack(recipe.getBlock()));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 5).addItemStacks(inputs).setBackground(JEIDrawables.SLOT, -1, -1);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 5).addItemStack(recipe.getResultItem(registryAccess())).setBackground(JEIDrawables.OUTPUT_SLOT, -5, -5);
	}

	@Override
	public void createRecipeExtras(IRecipeExtrasBuilder builder, ChannelingRecipe recipe, IFocusGroup focuses) {
		builder.addDrawable(JEIDrawables.RECIPE_ARROW, 24, 4);
	}

	@Override
	public void draw(ChannelingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		Font font = font();
		Component costComponent = Component.translatable("text.archon.mana_cost", recipe.getManaCost());
		guiGraphics.drawString(font, costComponent, getWidth() / 2 - font.width(costComponent) / 2, getHeight() - font.lineHeight, 0x00AAAA, false);
	}
}
