package net.hirukarogue.blacksmithery.datagen;

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

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BlacksmitheryMain.MOD_ID)
public class MainDataGen {
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
        try {
            System.out.println("First Line");
            ItemDataBuilder.item(WeaponAndToolPieces.WOODEN_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.WOOD_PIECES);
            System.out.println("Second Line");
            ItemDataBuilder.item(WeaponAndToolPieces.STONE_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.STONE_PIECES);
            System.out.println("Third Line");
            ItemDataBuilder.item(WeaponAndToolPieces.IRON_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.IRON_PIECES);
            System.out.println("Fourth Line");
            ItemDataBuilder.item(WeaponAndToolPieces.GOLD_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.GOLD_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES);
            System.out.println("Fifth Line");
            ItemDataBuilder.item(WeaponAndToolPieces.DIAMOND_AXE_HEAD.get()).basicItem()
                    .addToTag(BlacksmitheryTags.Items.AXE_HEADS)
                    .addToTag(BlacksmitheryTags.Items.DIAMOND_PIECES)
                    .addToTag(BlacksmitheryTags.Items.ORNAMENTAL_PIECES);
            System.out.println("End Line");
        } catch (Exception e) {
            throw new RuntimeException("Something happened: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}