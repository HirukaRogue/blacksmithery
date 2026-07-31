package net.hirukarogue.blacksmithery.tags;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BlacksmitheryTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(BlacksmitheryMain.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(BlacksmitheryMain.MOD_ID, name));
        }

        public static final TagKey<Item> AXE_HEADS = createTag("axe_heads");
        public static final TagKey<Item> HAMMER_HEADS = createTag("hammer_heads");
        public static final TagKey<Item> SPEAR_HEADS = createTag("spear_heads");
        public static final TagKey<Item> BLADES = createTag("blades");

        public static final TagKey<Item> WOOD_PIECES = createTag("wood_pieces");
        public static final TagKey<Item> STONE_PIECES = createTag("stone_pieces");
        public static final TagKey<Item> IRON_PIECES = createTag("iron_pieces");
        public static final TagKey<Item> GOLD_PIECES = createTag("gold_pieces");
        public static final TagKey<Item> DIAMOND_PIECES = createTag("diamond_pieces");

        public static final TagKey<Item> ORNAMENTAL_PIECES = createTag("ornamental_pieces");
    }
}
