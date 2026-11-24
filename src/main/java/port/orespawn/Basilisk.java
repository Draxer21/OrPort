package port.orespawn;

import java.util.Comparator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

final class Basilisk extends Creatures.Creature {
    private int hurtCooldown;

    Basilisk(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        if (hurtCooldown > 0) hurtCooldown--;
        if (random.nextInt(75) == 0) heal(1);
        if (random.nextInt(5) != 0) return;

        var target = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(24, 7, 24),
            entity -> !(entity instanceof Basilisk) && canAttack(entity) && hasLineOfSight(entity))
            .stream().min(Comparator.comparingDouble(entity -> distanceToSqr(entity))).orElse(null);
        setTarget(target);
        if (target == null) {
            setAggressive(false);
            return;
        }
        getLookControl().setLookAt(target);
        target.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 100, 5), this);
        double reach = 6 + target.getBbWidth() / 2;
        setAggressive(distanceToSqr(target) < reach * reach);
        if (isAggressive()) {
            if (random.nextBoolean()) doHurtTarget(level, target);
        } else {
            getNavigation().moveTo(target, 1.25);
        }
    }

    @Override public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) return false;
        if (target instanceof LivingEntity victim) {
            if (random.nextInt(3) == 0) {
                int seconds = 8 + level.getDifficulty().getId() * 2;
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, seconds * 20, 0), this);
            }
            var direction = victim.position().subtract(position()).multiply(1, 0, 1).normalize();
            victim.push(direction.x * 1.5, victim instanceof Player ? .30 : .15, direction.z * 1.5);
        }
        return true;
    }

    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source.is(DamageTypes.GENERIC_KILL) || source.is(DamageTypes.FELL_OUT_OF_WORLD))
            return super.hurtServer(level, source, amount);
        if (hurtCooldown > 0) return false;
        hurtCooldown = 30;
        return super.hurtServer(level, source, amount);
    }
}
