package port.orespawn;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

final class Termite extends Creatures.Creature {
    static final ResourceKey<Level> CRYSTAL = ResourceKey.create(Registries.DIMENSION,
        Identifier.fromNamespaceAndPath("orespawn", "crystal"));

    Termite(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }

    @Override public float getPickRadius() { return 0.1f; }

    @Override protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND || !player.getItemInHand(hand).isEmpty()) {
            return super.mobInteract(player, hand);
        }
        if (!(player instanceof ServerPlayer traveler)) return InteractionResult.SUCCESS;
        if (!traveler.level().dimension().equals(CRYSTAL) && !traveler.getInventory().isEmpty()) {
            traveler.sendSystemMessage(Component.translatable("message.orespawn.termite.empty_inventory"));
            return InteractionResult.FAIL;
        }
        return RedAnt.travel(traveler, CRYSTAL, species());
    }
}
