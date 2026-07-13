package net.hirukarogue.blacksmithery.miscelaneous;

import net.minecraft.world.level.block.Block;

public record DropData(Block dropFrom, Object itemOrBlock, int min, int max) {
}
