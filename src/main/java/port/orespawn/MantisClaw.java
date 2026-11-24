package port.orespawn;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

final class MantisClaw extends Item {
    MantisClaw(Properties properties) { super(properties.sword(ToolMaterial.DIAMOND, 3, -2.4f).durability(1000)); }

    @Override public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHurtEnemy(stack, target, attacker);
        if (!attacker.level().isClientSide()) attacker.heal(1);
    }
}
