package net.hirukarogue.blacksmithery.datagen.blockdata.providers;

import net.hirukarogue.blacksmithery.datagen.blockdata.BlockDataBuilder;
import net.hirukarogue.blacksmithery.miscelaneous.DropData;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;
import oshi.util.tuples.Pair;

import java.util.*;
import java.util.function.Supplier;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class BlacksmitheryBlockLootTableProvider extends BlockLootSubProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<Supplier<Block>> DROPSELF = new ArrayList<>();
    public static final List<Supplier<Pair<Block, Block>>> DROP_OTHER = new ArrayList<>();
    public static final List<Supplier<DropData>> BLOCK_DROPS = new ArrayList<>();

    public BlacksmitheryBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // Aqui você retorna apenas os blocos que você REALMENTE registrou no seu mod.
        // O sistema só vai validar loot tables para os blocos desta lista.
        return DROPSELF.stream().map(Supplier::get).toList();
    }

    @Override
    protected void generate() {
        if (!BLOCK_DROPS.isEmpty()) {
            for (Supplier<DropData> supplier : BLOCK_DROPS) {
                Block block = supplier.get().dropFrom();

                createMultipleOreDrops(block, supplier.get().itemOrBlock(), supplier.get().min(), supplier.get().max());
            }
        }

        if (!DROPSELF.isEmpty()) {
            for (Supplier<Block> supplier : DROPSELF) {
                dropSelf(supplier.get());
            }
        }

        if (!DROP_OTHER.isEmpty()) {
            for (Supplier<Pair<Block, Block>> supplier : DROP_OTHER) {
                Block block = supplier.get().getA();
                Block dropBlock = supplier.get().getB();

                dropOther(block, dropBlock);
            }
        }
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
}
