package net.hirukarogue.blacksmithery.datagen.itemdata.providers;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.itemdata.ItemDataBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlacksmitheryItemModelProvider extends ItemModelProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Map<String, List<Supplier<Object>>> BRAIN = new HashMap<>();

    public BlacksmitheryItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<Supplier<Object>> funcion = BRAIN.getOrDefault("basic_item", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier.get() instanceof Item item) {
                    this.basicItem(item);
                } else {
                    LOGGER.error("Invalid item for model generation: {}", supplier.get());
                }
            }
        }
    }
}
