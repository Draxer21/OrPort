package port.orespawn;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

final class DungeonBeast extends Creatures.Creature {
    DungeonBeast(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        xpReward = 60;
    }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1));
        goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8));
        goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this, DungeonBeast.class));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, true,
            (mob, level) -> !(mob instanceof DungeonBeast || mob instanceof Termite || mob instanceof TravelAnt || mob instanceof RedAnt)));
    }

    static boolean canSpawn(EntityType<Creatures.Creature> type, ServerLevelAccessor level,
            EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() == Difficulty.PEACEFUL) return false;
        if (reason == EntitySpawnReason.SPAWNER) return true;
        if (!level.getLevel().dimension().equals(Termite.CRYSTAL) || pos.getY() <= CrystalMaze.FLOOR_Y
                || pos.getY() > CrystalMaze.FLOOR_Y + 4) return false;
        var floor = level.getBlockState(pos.below());
        if (!(floor.is(Blocks.BEDROCK) || floor.is(CrystalBlocks.STONE.get())) ||
                !level.isEmptyBlock(pos) || !level.isEmptyBlock(pos.above()) ||
                !Monster.isDarkEnoughToSpawn(level, pos, random)) return false;
        int free = 0;
        for (int x = -1; x <= 1; x++) for (int z = -1; z <= 1; z++)
            if (level.isEmptyBlock(pos.offset(x, 1, z))) free++;
        return free >= 6;
    }

    @Override public int getMaxSpawnClusterSize() { return 6; }

    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.CACTUS)) return false;
        return super.hurtServer(level, source, amount);
    }
}
