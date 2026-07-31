package net.hirukarogue.blacksmithery.datagen;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.blockdata.BlockDataBuilder;
import net.hirukarogue.blacksmithery.datagen.blockdata.providers.BlacksmitheryBlockLootTableProvider;
import net.hirukarogue.blacksmithery.datagen.blockdata.providers.BlacksmitheryBlockStateProvider;
import net.hirukarogue.blacksmithery.datagen.blockdata.providers.BlacksmitheryBlockTagProvider;
import net.hirukarogue.blacksmithery.datagen.genericdata.GenericDataBuilder;
import net.hirukarogue.blacksmithery.datagen.genericdata.providers.BlacksmitheryDataMapProvider;
import net.hirukarogue.blacksmithery.datagen.genericdata.providers.BlacksmitheryRecipeProvider;
import net.hirukarogue.blacksmithery.datagen.itemdata.ItemDataBuilder;
import net.hirukarogue.blacksmithery.datagen.itemdata.providers.BlacksmitheryItemModelProvider;
import net.hirukarogue.blacksmithery.datagen.itemdata.providers.BlacksmitheryItemTagProvider;
import net.hirukarogue.blacksmithery.items.WeaponAndToolPieces;
import net.hirukarogue.blacksmithery.tags.BlacksmitheryTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BlacksmitheryMain.MOD_ID)
public class MainDataGen {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator data = event.getGenerator();
        PackOutput output = data.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        try {
            //block stuffs
            var lootProviderEntry = new LootTableProvider.SubProviderEntry(
                    BlacksmitheryBlockLootTableProvider::new,
                    net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.BLOCK
            );

            data.addProvider(event.includeServer(), new LootTableProvider(output, java.util.Collections.emptySet(),
                    java.util.List.of(lootProviderEntry), provider));

            BlockTagsProvider blockTagsProvider = new BlacksmitheryBlockTagProvider(output, provider, helper);
            data.addProvider(event.includeServer(), blockTagsProvider);

            data.addProvider(event.includeClient(), new BlacksmitheryBlockStateProvider(output, helper));

            //item stuffs
            data.addProvider(event.includeClient(), new BlacksmitheryItemModelProvider(output, helper));

            data.addProvider(event.includeServer(), new BlacksmitheryItemTagProvider(output, provider, blockTagsProvider.contentsGetter(), helper));

            //generic stuff
            data.addProvider(event.includeServer(), new BlacksmitheryRecipeProvider(output, provider));
            data.addProvider(event.includeServer(), new BlacksmitheryDataMapProvider(output, provider));
        } catch (Exception e) {
            throw new RuntimeException("Error while gathering data for Blacksmithery mod", e);
        }

        storeData();
    }

    private static void storeData() {
        LOGGER.info("Starting registering on datagen...");
        try {
            //axe heads
            ItemDataBuilder.item(WeaponAndToolPieces.WOODEN_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.WOOD_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.STONE_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.STONE_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.IRON_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.IRON_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.GOLD_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.GOLD_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.DIAMOND_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.DIAMOND_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();

            //hammer heads
            ItemDataBuilder.item(WeaponAndToolPieces.STONE_HAMMER_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.HAMMER_HEADS)
                    .addToTag(BlacksmitheryTags.Items.STONE_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.IRON_HAMMER_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.HAMMER_HEADS)
                    .addToTag(BlacksmitheryTags.Items.IRON_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.GOLD_HAMMER_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.HAMMER_HEADS)
                    .addToTag(BlacksmitheryTags.Items.GOLD_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.DIAMOND_HAMMER_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.HAMMER_HEADS)
                    .addToTag(BlacksmitheryTags.Items.DIAMOND_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();

            //spear heads
            ItemDataBuilder.item(WeaponAndToolPieces.STONE_SPEAR_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.SPEAR_HEADS)
                    .addToTag(BlacksmitheryTags.Items.STONE_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.IRON_SPEAR_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.SPEAR_HEADS)
                    .addToTag(BlacksmitheryTags.Items.IRON_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.GOLD_SPEAR_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.SPEAR_HEADS)
                    .addToTag(BlacksmitheryTags.Items.GOLD_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.DIAMOND_SPEAR_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.SPEAR_HEADS)
                    .addToTag(BlacksmitheryTags.Items.DIAMOND_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();

            //blades
            ItemDataBuilder.item(WeaponAndToolPieces.STONE_BLADE.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.BLADES)
                    .addToTag(BlacksmitheryTags.Items.STONE_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.IRON_BLADE.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.BLADES)
                    .addToTag(BlacksmitheryTags.Items.IRON_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.GOLD_BLADE.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.BLADES)
                    .addToTag(BlacksmitheryTags.Items.GOLD_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();
            ItemDataBuilder.item(WeaponAndToolPieces.DIAMOND_BLADE.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.BLADES)
                    .addToTag(BlacksmitheryTags.Items.DIAMOND_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES)
                    .build();

            LOGGER.info("Datagen complete!");
        } catch (Exception e) {
            LOGGER.error("Fatal error on storing data", e);
            throw new RuntimeException(e);
        }
    }
}