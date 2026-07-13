package net.hirukarogue.blacksmithery.datagen.itemdata.providers;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.itemdata.ItemDataBuilder;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryItemTagProvider extends ItemTagsProvider {
    public BlacksmitheryItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        List<Supplier<Object>> funcion = ItemDataBuilder.brain.getOrDefault("add_to_tag", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier.get() instanceof TagForItems(Item item, TagKey<Item> tagKey)) {
                    this.tag(tagKey).add(item);
                } else {
                    System.err.println("Invalid tag for item: " + supplier.get());
                }
            }
        }
    }
}
