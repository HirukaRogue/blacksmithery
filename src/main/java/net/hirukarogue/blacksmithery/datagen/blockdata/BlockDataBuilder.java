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

import java.util.ArrayList;
import java.util.List;

public class BlockDataBuilder {
    private Block block;
    private boolean hasBlockState = false;

    private final List<Runnable> buildActions = new ArrayList<>();

    private BlockDataBuilder(Block block) {
        this.block = block;
    }

    public static BlockDataBuilder block(Block block) {
        return new BlockDataBuilder(block);
    }

    public BlockDataBuilder dropSelf() {
        buildActions.add(() -> BlacksmitheryBlockLootTableProvider.DROPSELF.add(() -> this.block));
        return this;
    }

    public BlockDataBuilder dropOther(Block block) {
        buildActions.add(() -> BlacksmitheryBlockLootTableProvider.DROP_OTHER.add(() -> new Pair<>(this.block, block)));
        return this;
    }

    public BlockDataBuilder cubeAll() {
        if (!hasBlockState) {
            buildActions.add(() -> BlacksmitheryBlockStateProvider.CUBE_ALL.add(() -> this.block));
            this.hasBlockState = true;
            return this;
        }

        throw new IllegalArgumentException("A block cannot have more than one block state, block: " + this.block);
    }

    public BlockDataBuilder addToTag(TagKey<Block> tag) {
        buildActions.add(() -> BlacksmitheryBlockTagProvider.BRAIN.add(() -> new TagForBlocks(this.block, tag)));
        return this;
    }

    public BlockDataBuilder setDropRange(Block block, int min, int max) {
        buildActions.add(() -> BlacksmitheryBlockLootTableProvider.BLOCK_DROPS.add(() -> new DropData(this.block, block, min, max)));
        return this;
    }

    public BlockDataBuilder setDropRange(Item item, int min, int max) {
        buildActions.add(() -> BlacksmitheryBlockLootTableProvider.BLOCK_DROPS.add(() -> new DropData(this.block, item, min, max)));
        return this;
    }

    public void build() {
        for (Runnable action : buildActions) {
            action.run();
        }
        buildActions.clear();
    }
}

