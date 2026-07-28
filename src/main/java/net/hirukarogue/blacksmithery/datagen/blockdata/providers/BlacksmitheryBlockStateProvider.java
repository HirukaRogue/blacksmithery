package net.hirukarogue.blacksmithery.datagen.blockdata.providers;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.blockdata.BlockDataBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlacksmitheryBlockStateProvider extends BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<Supplier<Block>> CUBE_ALL = new ArrayList<>();

    public BlacksmitheryBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BlacksmitheryMain.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        if (!CUBE_ALL.isEmpty()) {
            for (Supplier<Block> supplier : CUBE_ALL) {
                simpleBlockWithItem(supplier.get(), cubeAll(supplier.get()));
            }
        }
    }
}
