package net.hirukarogue.blacksmithery.datagen.itemdata.providers;

import com.mojang.logging.LogUtils;
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
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryItemTagProvider extends ItemTagsProvider {
    public static final List<Supplier<TagForItems>> BRAIN = new ArrayList<>();

    public BlacksmitheryItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        if (!BRAIN.isEmpty()) {
            for (Supplier<TagForItems> supplier : BRAIN) {
                this.tag(supplier.get().tagKey()).add(supplier.get().item());
            }
        }
    }
}
