package net.hirukarogue.blacksmithery.datagen.blockdata;

import net.hirukarogue.blacksmithery.datagen.blockdata.providers.BlacksmitheryBlockLootTableProvider;
import net.hirukarogue.blacksmithery.datagen.blockdata.providers.BlacksmitheryBlockStateProvider;
import net.hirukarogue.blacksmithery.datagen.blockdata.providers.BlacksmitheryBlockTagProvider;
import net.hirukarogue.blacksmithery.miscelaneous.DropData;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForBlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

import java.util.*;
import java.util.function.Supplier;

public class BlockDataBuilder {
    private Block block;
    // Armazenamos as configurações temporariamente ou injetamos direto no "brain"

    private BlockDataBuilder(Block block) {
        this.block = block;
    }

    public static BlockDataBuilder block(Block block) {
        return new BlockDataBuilder(block);
    }

    public BlockDataBuilder dropSelf() {
        addLootTableEntry("drop_self", () -> this.block);
        return this; // Retorna o próprio builder para permitir encadeamento
    }

    public BlockDataBuilder dropOther(Block block) {
        addLootTableEntry("drop_other", () -> new Pair<>(this.block, block));
        return this;
    }

    public BlockDataBuilder cubeAll() {
        addBlockStateEntry("cube_all", () -> this.block);
        return this;
    }

    public BlockDataBuilder addToTag(TagKey<Block> tag) {
        BlacksmitheryBlockTagProvider.BRAIN.add(() -> new TagForBlocks(this.block, tag));
        return this;
    }

    public BlockDataBuilder setDropRange(Block block, int min, int max) {
        addLootTableEntry("set_drop_range", () -> new DropData(this.block, block, min, max));
        return this;
    }

    public BlockDataBuilder setDropRange(Item item, int min, int max) {
        addLootTableEntry("set_drop_range", () -> new DropData(this.block, item, min, max));
        return this;
    }

    private static void addLootTableEntry(String key, Supplier<Object> supplier) {
        BlacksmitheryBlockLootTableProvider.BRAIN.computeIfAbsent(key, k -> new ArrayList<>()).add(supplier);
    }

    private static void addBlockStateEntry(String key, Supplier<Object> supplier) {
        BlacksmitheryBlockStateProvider.BRAIN.computeIfAbsent(key, k-> new ArrayList<>()).add(supplier);
    }
}
