package net.hirukarogue.blacksmithery.events;

import net.hirukarogue.blacksmithery.BlacksmitheryMain;
import net.hirukarogue.blacksmithery.items.weapons.onehand.HuntingKnife;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EventBusSubscriber(modid = BlacksmitheryMain.MOD_ID)
public class HuntingKnifeExtraDrops {
    private static int LOOT_MULTIPLIER = 3;

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.level().isClientSide) {
            return;
        }

        if (!(event.getSource().getEntity() instanceof Player player)){
            return;
        }

        ItemStack weapon = player.getMainHandItem();
        if (!(weapon.getItem() instanceof HuntingKnife)) {
            return;
        }

        Collection<ItemEntity> drops = event.getDrops();
        List<ItemEntity> extraDrops = new ArrayList<>();

        for (ItemEntity drop : drops) {
            ItemStack stack = drop.getItem();

            for (int i = 1; i < LOOT_MULTIPLIER; i++) {
                ItemStack extraStack = stack.copy();

                ItemEntity extra = new ItemEntity(entity.level(), drop.getX(), drop.getY(), drop.getZ(), extraStack);

                extra.setDefaultPickUpDelay();
                extraDrops.add(extra);
            }
        }
    }
}
