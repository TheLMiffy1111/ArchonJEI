package thelm.archonjei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import safro.archon.client.screen.ScriptureTableScreen;
import safro.archon.client.screen.ScriptureTableScreenHandler;
import safro.archon.recipe.ChannelingRecipe;
import safro.archon.recipe.ScriptingRecipe;
import safro.archon.registry.BlockRegistry;
import safro.archon.registry.ItemRegistry;
import safro.archon.registry.MiscRegistry;
import safro.archon.registry.RecipeRegistry;
import thelm.archonjei.recipe.category.ChannelingCategory;
import thelm.archonjei.recipe.category.ScriptingCategory;

public class ArchonJEI implements IModPlugin {

	public static final ResourceLocation UID = new ResourceLocation("archonjei:archon");
	public static final Logger LOGGER = LogManager.getLogger();

	public static IJeiHelpers jeiHelpers;
	public static IJeiRuntime jeiRuntime;

	public static final RecipeType<ChannelingRecipe> CHANNELING = new RecipeType<>(new ResourceLocation("archon:channeling"), ChannelingRecipe.class);
	public static final RecipeType<ScriptingRecipe> SCRIPTING = new RecipeType<>(new ResourceLocation("archon:scripting"), ScriptingRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		jeiHelpers = registration.getJeiHelpers();

		if(checkDisabled()) {
			return;
		}

		registration.addRecipeCategories(new ChannelingCategory());
		registration.addRecipeCategories(new ScriptingCategory());
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
		registration.addRecipes(CHANNELING, recipeManager.getAllRecipesFor(RecipeRegistry.CHANNELING));
		registration.addRecipes(SCRIPTING, recipeManager.getAllRecipesFor(RecipeRegistry.SCRIPTING));
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		registration.addRecipeTransferHandler(ScriptureTableScreenHandler.class, MiscRegistry.SCRIPTURE_TABLE_SH, SCRIPTING, 0, 4, 6, 36);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		registration.addRecipeCatalyst(new ItemStack(ItemRegistry.CHANNELER), CHANNELING);
		registration.addRecipeCatalyst(new ItemStack(BlockRegistry.SCRIPTURE_TABLE), SCRIPTING);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		if(checkDisabled()) {
			return;
		}

		registration.addRecipeClickArea(ScriptureTableScreen.class, 78, 34, 18, 23, SCRIPTING);
	}

	public boolean checkDisabled() {
		if(FabricLoader.getInstance().isModLoaded("rei_plugin_compatibilities")) {
			LOGGER.warn("ArchonJEI is disabled with REIPC as Archon has native REI support");
			return true;
		}
		if(FabricLoader.getInstance().isModLoaded("emi")) {
			LOGGER.warn("ArchonJEI is disabled with EMI as Archon has native EMI support");
			return true;
		}
		return false;
	}
}
