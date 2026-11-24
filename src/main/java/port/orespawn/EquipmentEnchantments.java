package port.orespawn;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid="orespawn")
public final class EquipmentEnchantments {
    @SubscribeEvent public static void onPlayerTick(PlayerTickEvent.Post event){
        if(!(event.getEntity() instanceof ServerPlayer player) || player.tickCount%10!=0)return;
        var inventory=player.getInventory();
        for(int slot=0;slot<inventory.getContainerSize();slot++)apply(player,inventory.getItem(slot));
        for(var slot:net.minecraft.world.entity.EquipmentSlot.values())apply(player,player.getItemBySlot(slot));
    }
    static void apply(ServerPlayer player,ItemStack stack){
        if(stack.isEmpty() || stack.isEnchanted())return;
        var id=BuiltInRegistries.ITEM.getKey(stack.getItem());
        if(!id.getNamespace().equals("orespawn"))return;
        var reg=player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        switch(id.getPath()){
            case "ultimate_bow" -> {stack.enchant(reg.getOrThrow(Enchantments.POWER),5);stack.enchant(reg.getOrThrow(Enchantments.UNBREAKING),3);}
            case "ultimate_sword" -> {stack.enchant(reg.getOrThrow(Enchantments.LOOTING),6);stack.enchant(reg.getOrThrow(Enchantments.UNBREAKING),6);}
            case "ultimate_pickaxe" -> {stack.enchant(reg.getOrThrow(Enchantments.EFFICIENCY),5);stack.enchant(reg.getOrThrow(Enchantments.FORTUNE),5);}
            case "ultimate_axe","ultimate_shovel" -> stack.enchant(reg.getOrThrow(Enchantments.EFFICIENCY),5);
            case "ultimate_hoe" -> stack.enchant(reg.getOrThrow(Enchantments.EFFICIENCY),2);
            case "emerald_pickaxe" -> stack.enchant(reg.getOrThrow(Enchantments.SILK_TOUCH),1);
            case "ultimate_helmet","ultimate_chestplate","ultimate_leggings","ultimate_boots" -> {
                stack.enchant(reg.getOrThrow(Enchantments.RESPIRATION),2);
                stack.enchant(reg.getOrThrow(Enchantments.AQUA_AFFINITY),2);
                stack.enchant(reg.getOrThrow(Enchantments.PROTECTION),5);
                stack.enchant(reg.getOrThrow(Enchantments.FIRE_PROTECTION),5);
                stack.enchant(reg.getOrThrow(Enchantments.PROJECTILE_PROTECTION),5);
                stack.enchant(reg.getOrThrow(Enchantments.UNBREAKING),5);
                stack.enchant(reg.getOrThrow(Enchantments.FEATHER_FALLING),3);
            }
        }
    }
}
