package net.hirukarogue.blacksmithery.datagen.blockdata.providers;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.blockdata.BlockDataBuilder;
import net.hirukarogue.blacksmithery.miscelaneous.tagdata.TagForBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryBlockTagProvider extends BlockTagsProvider {
    public static final List<Supplier<TagForBlocks>> BRAIN = new ArrayList<>();

    public BlacksmitheryBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        if (!BRAIN.isEmpty()) {
            for (Supplier<TagForBlocks> supplier : BRAIN) {
                this.tag(supplier.get().tagKey()).add(supplier.get().block());
            }
        }
    }
}
