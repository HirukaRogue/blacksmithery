package net.hirukarogue.blacksmithery.items.tools;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;

public class SmithingHammer extends Item {
    public SmithingHammer(Properties properties, float durabilityModifier) {
        super(properties
                .stacksTo(1)
                .component(DataComponents.TOOL, smithingHammerToolComponent())
                .durability((int) Math.ceil(durabilityModifier*200))
        );
    }

    private static Tool smithingHammerToolComponent() {
        return new Tool(
                List.of(),
                1.0f,
                3
        );
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        if (itemAbility == BlacksmitheryToolAbilties.SMITHING) {
            return true;
        }

        return super.canPerformAction(stack, itemAbility);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context);
    }
}
