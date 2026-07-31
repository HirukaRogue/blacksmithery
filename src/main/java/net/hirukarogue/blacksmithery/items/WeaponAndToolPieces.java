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

    //Axe heads
    public static final DeferredItem<Item> WOODEN_AXE_HEAD = ITEMS.register("wooden_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STONE_AXE_HEAD = ITEMS.register("stone_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_AXE_HEAD = ITEMS.register("iron_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_AXE_HEAD = ITEMS.register("gold_axe_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_AXE_HEAD = ITEMS.register("diamond_axe_head", () -> new Item(new Item.Properties()));

    //Hammer heads
    public static final DeferredItem<Item> STONE_HAMMER_HEAD = ITEMS.register("stone_hammer_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_HAMMER_HEAD = ITEMS.register("iron_hammer_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_HAMMER_HEAD = ITEMS.register("gold_hammer_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_HAMMER_HEAD = ITEMS.register("diamond_hammer_head", () -> new Item(new Item.Properties()));

    //Spear heads
    public static final DeferredItem<Item> STONE_SPEAR_HEAD = ITEMS.register("stone_spear_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_SPEAR_HEAD = ITEMS.register("iron_spear_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_SPEAR_HEAD = ITEMS.register("gold_spear_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_SPEAR_HEAD = ITEMS.register("diamond_spear_head", () -> new Item(new Item.Properties()));

    //Blades
    public static final DeferredItem<Item> STONE_BLADE = ITEMS.register("stone_blade", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_BLADE = ITEMS.register("iron_blade", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_BLADE = ITEMS.register("gold_blade", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIAMOND_BLADE = ITEMS.register("diamond_blade", () -> new Item(new Item.Properties()));
}
