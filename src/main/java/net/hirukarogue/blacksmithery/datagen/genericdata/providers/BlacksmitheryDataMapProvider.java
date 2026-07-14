package net.hirukarogue.blacksmithery.datagen.genericdata.providers;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.datagen.genericdata.GenericDataBuilder;
import net.hirukarogue.blacksmithery.miscelaneous.DataMapData;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import org.slf4j.Logger;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryDataMapProvider extends DataMapProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<Supplier<DataMapData>> BRAIN = new ArrayList<>();

    public BlacksmitheryDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        if (!BRAIN.isEmpty()) {
            for (Supplier<DataMapData> supplier : BRAIN) {
                DataMapType dataMapType = supplier.get().type();
                Pair<?,?> dataPair = supplier.get().data();

                Object key = dataPair.getA();
                Object value = dataPair.getB();

                if (dataMapType == NeoForgeDataMaps.COMPOSTABLES) {
                    if (key instanceof Item item && value instanceof Float chance) {
                        this.builder(NeoForgeDataMaps.COMPOSTABLES).add(item.builtInRegistryHolder(), new Compostable(chance), false);
                    } else {
                        LOGGER.error("Invalid key-value pair for Compostable: {}", dataPair);
                    }
                } else if (dataMapType == NeoForgeDataMaps.FURNACE_FUELS){
                    if (key instanceof Item item && value instanceof Integer burnTime) {
                        this.builder(NeoForgeDataMaps.FURNACE_FUELS).add(item.builtInRegistryHolder(), new FurnaceFuel(burnTime), false);
                    } else {
                        LOGGER.error("Invalid key-value pair for FurnaceFuel: {}", dataPair);
                    }
                } else if (dataMapType == NeoForgeDataMaps.MONSTER_ROOM_MOBS) {
                    if (key instanceof EntityType<?> entityType && value instanceof MonsterRoomMob monsterRoomMob) {
                        this.builder(NeoForgeDataMaps.MONSTER_ROOM_MOBS).add(entityType.builtInRegistryHolder(), monsterRoomMob, false);
                    } else {
                        LOGGER.error("Invalid key-value pair for MonsterRoomMob: {}", dataPair);
                    }
                } else if (dataMapType == NeoForgeDataMaps.OXIDIZABLES) {
                    if (key instanceof Block block && value instanceof Oxidizable oxidizable) {
                        this.builder(NeoForgeDataMaps.OXIDIZABLES).add(block.builtInRegistryHolder(), oxidizable, false);
                    } else {
                        LOGGER.error("Invalid key-value pair for Oxidizable: {}", dataPair);
                    }
                } else if (dataMapType == NeoForgeDataMaps.PARROT_IMITATIONS) {
                    if (key instanceof EntityType<?> entityType && value instanceof ParrotImitation parrotImitation) {
                        this.builder(NeoForgeDataMaps.PARROT_IMITATIONS).add(entityType.builtInRegistryHolder(), parrotImitation, false);
                    } else {
                        LOGGER.error("Invalid key-value pair for ParrotImitation: {}", dataPair);
                    }
                } else if (dataMapType == NeoForgeDataMaps.STRIPPABLES) {
                    if (key instanceof Block block && value instanceof Strippable strippable) {
                        this.builder(NeoForgeDataMaps.STRIPPABLES).add(block.builtInRegistryHolder(), strippable, false);
                    } else {
                        LOGGER.error("Invalid key-value pair for Strippable: {}", dataPair);
                    }
                } else if (dataMapType == NeoForgeDataMaps.WAXABLES) {
                    if (key instanceof Block block && value instanceof Waxable waxable) {
                        this.builder(NeoForgeDataMaps.WAXABLES).add(block.builtInRegistryHolder(), waxable, false);
                    } else {
                        LOGGER.error("Invalid key-value pair for Waxable: {}", dataPair);
                    }
                }
                else {
                    LOGGER.error("Unsupported DataMapType: {}", dataMapType);
                }
            }
        }
    }
}
