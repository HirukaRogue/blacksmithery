package net.hirukarogue.blacksmithery.items;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.items.weapons.onehand.HuntingKnife;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlacksmitheryWeapons {
    protected static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlacksmitheryMain.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //Knifes
    public static final DeferredItem<Item> STONE_KNIFE = ITEMS.register("stone_knife", () -> new HuntingKnife(new Item.Properties(), 1.26f, 1.5f));
    public static final DeferredItem<Item> IRON_KNIFE = ITEMS.register("iron_knife", () -> new HuntingKnife(new Item.Properties(), 2.1f, 2f));
    public static final DeferredItem<Item> GOLDEN_KNIFE = ITEMS.register("gold_knife", () -> new HuntingKnife(new Item.Properties(), 0.45f, 2.5f));
    public static final DeferredItem<Item> DIAMOND_KNIFE = ITEMS.register("diamond_knife", () -> new HuntingKnife(new Item.Properties(), 5.0f, 3f));

    //Spear
    public static final DeferredItem<Item> STONE_SPEAR = ITEMS.register("stone_spear", () -> new HuntingKnife(new Item.Properties(), 1.26f, 2.5f));
    public static final DeferredItem<Item> IRON_SPEAR = ITEMS.register("iron_spear", () -> new HuntingKnife(new Item.Properties(), 2.1f, 3f));
    public static final DeferredItem<Item> GOLDEN_SPEAR = ITEMS.register("gold_spear", () -> new HuntingKnife(new Item.Properties(), 0.45f, 3.5f));
    public static final DeferredItem<Item> DIAMOND_SPEAR = ITEMS.register("diamond_spear", () -> new HuntingKnife(new Item.Properties(), 5.0f, 4f));
}
