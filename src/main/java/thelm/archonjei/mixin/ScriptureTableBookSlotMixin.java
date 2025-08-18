package thelm.archonjei.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;

@Mixin(targets = "safro.archon.client.screen.ScriptureTableScreenHandler$BookSlot")
public abstract class ScriptureTableBookSlotMixin extends Slot {

	private ScriptureTableBookSlotMixin(Container container, int slot, int x, int y) {
		super(container, slot, x, y);
	}

	@Override
	public boolean allowModification(Player player) {
		return mayPickup(player) && (getItem().isEmpty() || mayPlace(getItem()));
	}
}
