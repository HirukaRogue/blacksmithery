package net.hirukarogue.blacksmithery.datagen.blockdata.providers;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.blockdata.BlockDataBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlacksmitheryBlockStateProvider extends BlockStateProvider {
    public BlacksmitheryBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BlacksmitheryMain.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List<Supplier<Object>> funcion = BlockDataBuilder.brain.getOrDefault("cube_all", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier.get() instanceof Block block) {
                    simpleBlockWithItem(block, cubeAll(block));
                } else {
                    System.err.println("Invalid cube all entry: " + supplier.get());
                }
            }
        }
    }
}
