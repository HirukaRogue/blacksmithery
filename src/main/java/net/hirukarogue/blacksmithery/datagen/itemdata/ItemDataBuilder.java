package net.hirukarogue.blacksmithery.datagen.itemdata;

import net.hirukarogue.blacksmithery.datagen.itemdata.providers.BlacksmitheryItemModelProvider;
import net.hirukarogue.blacksmithery.datagen.itemdata.providers.BlacksmitheryItemTagProvider;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ItemDataBuilder {
    private final Item item;

    private ItemDataBuilder(Item item) {
        this.item = item;
    }

    public static ItemDataBuilder item(Item item) {
        return new ItemDataBuilder(item);
    }

    public ItemDataBuilder basicItem() {
        addItemModelEntry("basic_item", () -> this.item);
        return this;
    }

    public ItemDataBuilder addToTag(TagKey<Item> tagKey) {
        BlacksmitheryItemTagProvider.BRAIN.add(() -> new TagForItems(this.item, tagKey));
        return this;
    }

    private static void addItemModelEntry(String key, Supplier <Object> supplier) {
        BlacksmitheryItemModelProvider.BRAIN.computeIfAbsent(key, k -> new ArrayList<>()).add(supplier);
    }
}
