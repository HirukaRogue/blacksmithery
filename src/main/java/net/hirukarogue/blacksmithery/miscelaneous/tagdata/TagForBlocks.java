package net.hirukarogue.blacksmithery.miscelaneous.tagdata;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record TagForBlocks(Block block, TagKey<Block> tagKey) {
}
