package net.hirukarogue.blacksmithery.datagen.itemdata.providers;

import com.mojang.logging.LogUtils;
import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class BlacksmitheryItemModelProvider extends ItemModelProvider {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final List<Supplier<Item>> BASIC_ITEM = new ArrayList<>();
    public static final List<Supplier<Item>> KNIFE_ITEM = new ArrayList<>();

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

        for (Supplier<Item> supplier : KNIFE_ITEM) {
            knifeItem(supplier.get());
        }
    }

    private void knifeItem(Item item) {
        String name = item.toString();
        ResourceLocation itemId = item.builtInRegistryHolder().key().location();
        String path = itemId.getPath();

        ItemModelBuilder flat = withExistingParent(path + "_2d", mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + path));


        ItemModelBuilder inHand = withExistingParent(path + "_in_hand", modLoc("item/knife_in_hand"))
                .texture("layer0", modLoc("item/" + path));

        getBuilder(path)
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(inHand)
                .perspective(ItemDisplayContext.GUI, flat)
                .perspective(ItemDisplayContext.GROUND, flat)
                .perspective(ItemDisplayContext.FIXED, flat)
                .end();
    }
}
