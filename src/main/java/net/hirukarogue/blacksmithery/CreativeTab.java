package net.hirukarogue.blacksmithery;

import net.hirukarogue.blacksmithery.items.WeaponAndToolPieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlacksmitheryMain.MOD_ID);

    public static final Supplier<CreativeModeTab> BLACKSMITHERY_CREATIVE_MODE_TAB = CREATIVE_MODE_TABS.register("blacksmithery_tab",
            () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(WeaponAndToolPieces.GOLD_AXE_HEAD.get()))
                    .title(Component.translatable("creativetab.blacksmithery.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //axe heads
                        output.accept(WeaponAndToolPieces.WOODEN_AXE_HEAD);
                        output.accept(WeaponAndToolPieces.STONE_AXE_HEAD);
                        output.accept(WeaponAndToolPieces.IRON_AXE_HEAD);
                        output.accept(WeaponAndToolPieces.GOLD_AXE_HEAD);
                        output.accept(WeaponAndToolPieces.DIAMOND_AXE_HEAD);

                        //hammer heads
                        output.accept(WeaponAndToolPieces.STONE_HAMMER_HEAD);
                        output.accept(WeaponAndToolPieces.IRON_HAMMER_HEAD);
                        output.accept(WeaponAndToolPieces.GOLD_HAMMER_HEAD);
                        output.accept(WeaponAndToolPieces.DIAMOND_HAMMER_HEAD);

                        //spear heads
                        output.accept(WeaponAndToolPieces.STONE_SPEAR_HEAD);
                        output.accept(WeaponAndToolPieces.IRON_SPEAR_HEAD);
                        output.accept(WeaponAndToolPieces.GOLD_SPEAR_HEAD);
                        output.accept(WeaponAndToolPieces.DIAMOND_SPEAR_HEAD);

                        //blades
                        output.accept(WeaponAndToolPieces.STONE_BLADE);
                        output.accept(WeaponAndToolPieces.IRON_BLADE);
                        output.accept(WeaponAndToolPieces.GOLD_BLADE);
                        output.accept(WeaponAndToolPieces.DIAMOND_BLADE);

                        //welding rings
                        output.accept(WeaponAndToolPieces.IRON_WELDING_RING);
                        output.accept(WeaponAndToolPieces.GOLD_WELDING_RING);
                        output.accept(WeaponAndToolPieces.DIAMOND_WELDING_RING);
                    }).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
