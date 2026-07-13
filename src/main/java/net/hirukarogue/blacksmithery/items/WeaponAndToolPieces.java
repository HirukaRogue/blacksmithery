package net.hirukarogue.blacksmithery.items;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WeaponAndToolPieces {
    protected static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlacksmitheryMain.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final DeferredItem<Item> WOODEN_AXE_HEAD = ITEMS.register("wooden_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STONE_AXE_HEAD = ITEMS.register("stone_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_AXE_HEAD = ITEMS.register("iron_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_AXE_HEAD = ITEMS.register("gold_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_AXE_HEAD = ITEMS.register("diamond_axe_head", () -> new Item(new Item.Properties()));
}
