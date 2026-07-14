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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class BlacksmitheryBlockLootTableProvider extends BlockLootSubProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Map<String, List<Supplier<Object>>> BRAIN = new HashMap<>();

    public BlacksmitheryBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // Aqui você retorna apenas os blocos que você REALMENTE registrou no seu mod.
        // O sistema só vai validar loot tables para os blocos desta lista.
        return BRAIN.getOrDefault("drop_self", List.of()).stream()
                .map(supplier -> (Block) supplier.get())
                .toList();
    }

    @Override
    protected void generate() {
        List<Supplier<Object>> funcion = BRAIN.getOrDefault("set_drop_range", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> object : funcion) {
                DropData data;
                if (object.get() instanceof DropData dropData) {
                    data = dropData;
                } else {
                    LOGGER.error("Invalid drop range data: {}", object.get());
                    continue;
                }

                Block block = data.dropFrom();

                if (data.itemOrBlock() instanceof Block blockAsItem) {
                    createMultipleOreDrops(block, blockAsItem.asItem(), data.min(), data.max());
                } else if (data.itemOrBlock() instanceof Item item) {
                    createMultipleOreDrops(block, item, data.min(), data.max());
                } else {
                    LOGGER.error("Invalid drop item for block {}: {}", block, data.itemOrBlock());
                }
            }
        }

        funcion = BRAIN.getOrDefault("drop_self", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier instanceof Block block) {
                    dropSelf(block);
                } else {
                    LOGGER.error("Invalid drop self block: {}", supplier.get());
                }
            }
        }

        funcion = BRAIN.getOrDefault("drop_other", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier.get() instanceof Pair<?, ?> pair) {
                    Block block;
                    Block dropBlock;
                    if (pair.getA() instanceof Block b1 && pair.getB() instanceof Block b2) {
                        block = b1;
                        dropBlock = b2;
                    } else {
                        LOGGER.error("Invalid drop other entry: {} -> Both pairs must be blocks", supplier.get());
                        continue;
                    }
                    dropOther(block, dropBlock);
                } else {
                    LOGGER.error("Invalid drop other entry: {} -> Supplier is not a pair", supplier.get());
                }
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
