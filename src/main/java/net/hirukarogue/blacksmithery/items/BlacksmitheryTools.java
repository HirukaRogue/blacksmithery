package net.hirukarogue.blacksmithery.items;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.items.tools.SmithingHammer;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlacksmitheryTools {
    protected static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlacksmitheryMain.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //Smithing Hammer
    public static final DeferredItem<Item> STONE_SMITHING_HAMMER = ITEMS.register("stone_smithing_hammer", () -> new SmithingHammer(new Item.Properties(), 1.26f));
    public static final DeferredItem<Item> IRON_SMITHING_HAMMER = ITEMS.register("iron_smithing_hammer", () -> new SmithingHammer(new Item.Properties(), 1.52f));
    public static final DeferredItem<Item> GOLD_SMITHING_HAMMER = ITEMS.register("gold_smithing_hammer", () -> new SmithingHammer(new Item.Properties(), 2.04f));
    public static final DeferredItem<Item> DIAMOND_SMITHING_HAMMER = ITEMS.register("diamond_smithing_hammer", () -> new SmithingHammer(new Item.Properties(), 4.08f));
}
