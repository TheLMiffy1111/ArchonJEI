package thelm.archonjei.recipe.replacer;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import safro.archon.api.summon.SummonHandler;
import safro.archon.item.SoulTomeItem;
import safro.archon.item.UndeadStaffItem;
import safro.archon.registry.ItemRegistry;

public class SoulBindingRecipeMaker {

	public static final TagKey<Item> SOUL_TOMES = TagKey.create(Registries.ITEM, new ResourceLocation("archon:soul_tomes"));

	public static List<CraftingRecipe> createRecipes() {
		String group = "archon.jei.soul_binding";
		Ingredient staffIng = Ingredient.of(ItemRegistry.UNDEAD_STAFF);
		return BuiltInRegistries.ITEM.getTag(SOUL_TOMES).
				stream().
				flatMap(HolderSet::stream).
				map(Holder::value).
				filter(SoulTomeItem.class::isInstance).
				map(SoulTomeItem.class::cast).
				<CraftingRecipe>map(tome -> {
					String summonId = SummonHandler.getId(tome.getSummon());
					if(summonId == null) {
						summonId = BuiltInRegistries.ITEM.getKey(tome).toString();
					}
					summonId = summonId.replace(':', '/');
					ResourceLocation id = new ResourceLocation("archon:jei/soul_binding/" + summonId);
					NonNullList<Ingredient> ingredients = NonNullList.create();
					ingredients.add(staffIng);
					ingredients.add(Ingredient.of(tome));
					ItemStack result = new ItemStack(ItemRegistry.UNDEAD_STAFF);
					UndeadStaffItem.addSummon(tome.getSummon(), result);
					return new ShapelessRecipe(id, group, CraftingBookCategory.MISC, result, ingredients);
				}).
				toList();
	}
}
