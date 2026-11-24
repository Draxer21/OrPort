package port.orespawn;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

final class BigHammer extends Item {
    BigHammer(Properties properties){super(properties.sword(ToolMaterial.DIAMOND,11,-2.8f).durability(9000));}
    @Override public void postHurtEnemy(ItemStack stack,LivingEntity victim,LivingEntity wielder){
        super.postHurtEnemy(stack,victim,wielder);
        if(!victim.level().isClientSide())victim.push(0,victim.getRandom().nextFloat()*2/3,0);
    }
}
