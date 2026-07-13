package net.hirukarogue.blacksmithery.datagen.blockdata;

import net.hirukarogue.blacksmithery.miscelaneous.DropData;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForBlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

import java.util.*;
import java.util.function.Supplier;

public class BlockDataBuilder {
    public static final Map<String, List<Supplier<Object>>> brain = new HashMap<>();

    private Block block;
    // Armazenamos as configurações temporariamente ou injetamos direto no "brain"

    private BlockDataBuilder(Block block) {
        this.block = block;
    }

    public static BlockDataBuilder block(Block block) {
        return new BlockDataBuilder(block);
    }

    public BlockDataBuilder dropSelf() {
        addEntry("drop_self", () -> this.block);
        return this; // Retorna o próprio builder para permitir encadeamento
    }

    public BlockDataBuilder dropOther(Block block) {
        addEntry("drop_other", () -> new Pair<>(this.block, block));
        return this;
    }

    public BlockDataBuilder cubeAll() {
        addEntry("cube_all", () -> this.block);
        return this;
    }

    public BlockDataBuilder addToTag(TagKey<Block> tag) {
        addEntry("add_to_tag", () -> new TagForBlocks(this.block, tag));
        return this;
    }

    public BlockDataBuilder setDropRange(Block block, int min, int max) {
        addEntry("set_drop_range", () -> new DropData(this.block, block, min, max));
        return this;
    }

    public BlockDataBuilder setDropRange(Item item, int min, int max) {
        addEntry("set_drop_range", () -> new DropData(this.block, item, min, max));
        return this;
    }

    private static void addEntry(String key, Supplier<Object> supplier) {
        brain.computeIfAbsent(key,  k -> new ArrayList<>()).add(supplier);
    }
}
