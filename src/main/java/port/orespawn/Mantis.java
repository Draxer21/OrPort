package port.orespawn;

import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

final class Mantis extends FlyingBoss {
    Mantis(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level, BossEvent.BossBarColor.GREEN);
    }
}
