package port.orespawn;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Hormigas arcoíris e inestable (EntityRainbowAnt, EntityUnstableAnt). Son pasivas, huyen al
 * recibir daño y, con clic derecho y la mano vacía, llevan a su dimensión o devuelven al mundo
 * principal si el jugador ya está en ella.
 */
final class TravelAnt extends Creatures.Creature {
    static final ResourceKey<Level> VILLAGES = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath("orespawn", "villages"));
    static final ResourceKey<Level> DANGER = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath("orespawn", "danger"));

    TravelAnt(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }

    ResourceKey<Level> destination() { return species().equals("rainbow_ant") ? VILLAGES : DANGER; }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new PanicGoal(this, 1.4));
        goalSelector.addGoal(1, new FloatGoal(this));
        var wander = new WaterAvoidingRandomStrollGoal(this, 1);
        wander.setInterval(9);
        goalSelector.addGoal(2, wander);
    }

    /** En 1.7.10 el clic ampliaba la caja 0,1 por lado (getCollisionBorderSize); sin esto la hormiga mide 0,1. */
    @Override public float getPickRadius() { return 0.1f; }

    @Override protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND || !player.getItemInHand(hand).isEmpty()) return super.mobInteract(player, hand);
        if (!(player instanceof ServerPlayer traveler)) return InteractionResult.SUCCESS;
        return RedAnt.travel(traveler, destination(), species());
    }
}
