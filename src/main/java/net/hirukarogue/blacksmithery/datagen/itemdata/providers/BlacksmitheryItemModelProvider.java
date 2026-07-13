package net.hirukarogue.blacksmithery.datagen.itemdata.providers;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.datagen.itemdata.ItemDataBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Supplier;

public class BlacksmitheryItemModelProvider extends ItemModelProvider {
    public BlacksmitheryItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<Supplier<Object>> funcion = ItemDataBuilder.brain.getOrDefault("basic_item", List.of());
        if (!funcion.isEmpty()) {
            for (Supplier<Object> supplier : funcion) {
                if (supplier.get() instanceof Item item) {
                    this.basicItem(item);
                } else {
                    System.err.println("Invalid item for model generation: " + supplier.get());
                }
            }
        }
    }
}
