package port.orespawn;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

final class Butterfly extends Creatures.Creature {
    static final ResourceKey<Level> CHAOS = ResourceKey.create(Registries.DIMENSION,
        Identifier.fromNamespaceAndPath("orespawn", "chaos"));

    Butterfly(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }

    @Override protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND || !player.getItemInHand(hand).isEmpty()) {
            return super.mobInteract(player, hand);
        }
        return player instanceof ServerPlayer traveler
            ? RedAnt.travel(traveler, CHAOS, species()) : InteractionResult.SUCCESS;
    }
}
