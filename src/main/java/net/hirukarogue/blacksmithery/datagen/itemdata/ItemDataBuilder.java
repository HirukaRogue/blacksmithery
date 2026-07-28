package net.hirukarogue.blacksmithery.datagen.itemdata;

import net.hirukarogue.blacksmithery.datagen.itemdata.providers.BlacksmitheryItemModelProvider;
import net.hirukarogue.blacksmithery.datagen.itemdata.providers.BlacksmitheryItemTagProvider;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDataBuilder {
    private final Item item;
    private boolean hasModel = false;

    private final List<Runnable> buildActions = new ArrayList<>();

    private ItemDataBuilder(Item item) {
        this.item = item;
    }

    public static ItemDataBuilder item(Item item) {
        return new ItemDataBuilder(item);
    }

    public ItemDataBuilder basicItem() {
        if (!hasModel) {
            buildActions.add(() -> BlacksmitheryItemModelProvider.BASIC_ITEM.add(() -> this.item));
            hasModel = true;
            return this;
        }

        throw new IllegalArgumentException("An item cannot have more than 1 item model, item: " + this.item);
    }

    public ItemDataBuilder addToTag(TagKey<Item> tagKey) {
        buildActions.add(() -> BlacksmitheryItemTagProvider.BRAIN.add(() -> new TagForItems(this.item, tagKey)));
        return this;
    }

    public void build() {
        for (Runnable action : buildActions) {
            action.run();
        }

        buildActions.clear();
    }
}
