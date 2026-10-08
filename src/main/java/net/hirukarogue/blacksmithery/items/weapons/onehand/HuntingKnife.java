package net.hirukarogue.blacksmithery.items.weapons.onehand;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;

public class HuntingKnife extends Item {
    public HuntingKnife(Item.Properties properties, float durabilityModifier, float damageByHearts) {
        super(properties
                .stacksTo(1)
                .component(DataComponents.TOOL, knifeComponent((int) Math.ceil(damageByHearts*2 - 1)))
                .durability((int) Math.ceil(durabilityModifier*200))
                .attributes(createAttributes((int) Math.ceil(damageByHearts*2 - 1)))
        );
    }

    private static Tool knifeComponent(int damage) {
        return new Tool(
                List.of(),
                1.0f,
                damage
        );
    }

    private static ItemAttributeModifiers createAttributes(int damage) {
        return ItemAttributeModifiers.builder()
                // Dano de ataque (ajuste o valor como preferir)
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("blacksmithery", "knife_damage"),
                                damage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("blacksmithery", "knife_speed"),
                                -0.8,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )

                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("blacksmithery", "knife_reach"),
                                -1.0,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        if (ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility)) {
            return true;
        }

        return super.canPerformAction(stack, itemAbility);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context);
    }
}
