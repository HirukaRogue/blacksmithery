package net.hirukarogue.blacksmithery.events;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.tags.BlacksmitheryTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = BlacksmitheryMain.MOD_ID)
public class PreserveOnCrafting {

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        // Garante que é uma bancada / grade de crafting válida
        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        Container craftingGrid = event.getInventory();

        for (int i = 0; i < craftingGrid.getContainerSize(); i++) {
            ItemStack stack = craftingGrid.getItem(i);

            if (!stack.isEmpty() && stack.is(BlacksmitheryTags.Items.PRESERVES_IN_CRAFTING)) {
                ItemStack remaining = stack.copy();

                if (remaining.isDamageableItem()) {
                    // Aplica 1 de dano
                    remaining.setDamageValue(remaining.getDamageValue() + 1);

                    // Se estourar a durabilidade, o item quebra (fica vazio)
                    if (remaining.getDamageValue() >= remaining.getMaxDamage()) {
                        remaining = ItemStack.EMPTY;
                    }
                }

                // Devolve o item alterado/danificado diretamente para o slot da grade
                craftingGrid.setItem(i, remaining);
            }
        }
    }
}
