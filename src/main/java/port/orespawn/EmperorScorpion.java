package port.orespawn;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

final class EmperorScorpion extends Creatures.Creature {
    private int hurtCooldown;

    EmperorScorpion(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        if (hurtCooldown > 0) hurtCooldown--;
        if (random.nextInt(100) == 0) heal(2);
        if (random.nextInt(4) != 0) return;
        var target = getTarget();
        if (target == null || !target.isAlive() || !hasLineOfSight(target)) {
            setAggressive(false);
            return;
        }
        getLookControl().setLookAt(target);
        double reach = 6 + target.getBbWidth() / 2;
        setAggressive(distanceToSqr(target) < reach * reach);
        if (isAggressive()) {
            if (random.nextInt(4) == 0 || random.nextInt(6) == 1) doHurtTarget(level, target);
        } else {
            getNavigation().moveTo(target, 1.2);
        }
        if (random.nextInt(20) == 1) {
            var ally = Creatures.SPECIES.get("scorpion").type().get().create(level, EntitySpawnReason.TRIGGERED);
            if (ally == null) return;
            double x = (getX() + target.getX()) / 2 + random.nextInt(5) - random.nextInt(5);
            double z = (getZ() + target.getZ()) / 2 + random.nextInt(5) - random.nextInt(5);
            ally.snapTo(x, target.getY(), z, getYRot(), 0);
            if (level.noCollision(ally) && level.addFreshEntity(ally)) ally.setTarget(target);
        }
    }

    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source.is(DamageTypes.GENERIC_KILL) || source.is(DamageTypes.FELL_OUT_OF_WORLD))
            return super.hurtServer(level, source, amount);
        if (hurtCooldown > 0 || source.is(DamageTypes.CACTUS)) return false;
        hurtCooldown = 30;
        return super.hurtServer(level, source, amount);
    }

    @Override public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) return false;
        if (target instanceof LivingEntity victim) {
            if (random.nextInt(3) == 1) {
                int duration = (6 + 2 * level.getDifficulty().getId()) * 15;
                victim.addEffect(new MobEffectInstance(MobEffects.POISON, duration, 0), this);
            }
            var direction = victim.position().subtract(position()).multiply(1, 0, 1).normalize();
            victim.push(3 * direction.x, victim instanceof Player ? .4 : .2, 3 * direction.z);
        }
        return true;
    }
}
