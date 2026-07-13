package net.hirukarogue.blacksmithery.miscelaneous.tagdata;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record TagForItems(Item item, TagKey<Item> tagKey) {
}
