package net.hirukarogue.blacksmithery.datagen.itemdata.providers;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlacksmitheryItemModelProvider extends ItemModelProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<Supplier<Item>> BASIC_ITEM = new ArrayList<>();

    public BlacksmitheryItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BlacksmitheryMain.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        if (!BASIC_ITEM.isEmpty()) {
            for (Supplier<Item> supplier : BASIC_ITEM) {
                this.basicItem(supplier.get());
            }
        }
    }
}
