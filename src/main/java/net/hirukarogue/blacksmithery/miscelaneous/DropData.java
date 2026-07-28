package net.hirukarogue.blacksmithery.miscelaneous;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public record DropData(Block dropFrom, Item drop, int min, int max) {
    public DropData(Block dropFrom, ItemLike drop, int min, int max) {
        this(dropFrom, drop.asItem(), min, max);
    }
}
