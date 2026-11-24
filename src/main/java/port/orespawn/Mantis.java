package port.orespawn;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

final class Mantis extends Creatures.Creature {
    private final ServerBossEvent bar = new ServerBossEvent(java.util.UUID.randomUUID(), getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);

    Mantis(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }

    @Override protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        bar.setProgress(getHealth() / getMaxHealth());
    }
    @Override public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bar.setName(getDisplayName());
        bar.setProgress(getHealth() / getMaxHealth());
        bar.addPlayer(player);
    }
    @Override public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bar.removePlayer(player);
    }
}

