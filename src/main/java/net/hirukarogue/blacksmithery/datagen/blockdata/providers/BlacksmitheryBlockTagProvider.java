package net.hirukarogue.blacksmithery.datagen.blockdata.providers;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.blockdata.BlockDataBuilder;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryBlockTagProvider extends BlockTagsProvider {
    public BlacksmitheryBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        List<Supplier<Object>> funcion = BlockDataBuilder.brain.getOrDefault("add_to_tag", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier.get() instanceof TagForBlocks data) {
                    this.tag(data.tagKey()).add(data.block());
                } else {
                    System.err.println("Invalid tag for block: " + supplier.get());
                }
            }
        }
    }
}
