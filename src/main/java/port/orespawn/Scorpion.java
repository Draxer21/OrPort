package port.orespawn;

import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

/** Escorpión común de OreSpawn 1.7.10: 15 de vida, 4 de ataque, 10 de armadura. */
final class Scorpion extends Creatures.Creature {
    /** Criaturas que MyUtils.isIgnoreable descarta como presa en el clásico. */
    private static final Set<String> IGNORED = Set.of("red_ant", "rainbow_ant", "unstable_ant", "termite", "butterfly", "moth", "mosquito", "dragonfly", "firefly");

    Scorpion(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this, Scorpion.class, EmperorScorpion.class));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 6, true, false,
            (target, level) -> isPrey(target, false)));
    }

    /** El clásico no recibe daño de cactus. */
    @Override public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source.is(DamageTypes.CACTUS)) return false;
        return super.hurtServer(level, source, amount);
    }

    /**
     * isSuitableTarget del clásico. El común caza animales, aldeanos, jugadores, arañas, creepers y
     * Velocity Raptor, pero no otros monstruos. El emperador ataca también a los monstruos salvo
     * endermans y creepers. Ninguno ataca a otros escorpiones ni a los insectos ignorados.
     */
    static boolean isPrey(LivingEntity target, boolean emperor) {
        if (!target.isAlive() || target instanceof Scorpion || target instanceof EmperorScorpion) return false;
        // En el clásico las tres hormigas heredan de EntityAnt, que MyUtils.isIgnoreable descarta.
        if (target instanceof RedAnt || target instanceof TravelAnt) return false;
        if (target instanceof Creatures.Creature creature && IGNORED.contains(creature.species())) return false;
        var type = target.getType();
        String id = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(type).toString();
        if (emperor) return !id.equals("minecraft:enderman") && !id.equals("minecraft:creeper");
        if (id.equals("minecraft:spider") || id.equals("minecraft:cave_spider") || id.equals("minecraft:creeper")) return true;
        return !(target instanceof Monster) && type.getCategory() != MobCategory.MONSTER;
    }

    /** Aparición natural del común: solo con poca luz, como un monstruo del clásico. */
    static boolean canSpawn(EntityType<? extends Mob> type, ServerLevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        if (!Mob.checkMobSpawnRules(type, level, reason, pos, random)) return false;
        return reason == EntitySpawnReason.SPAWNER || Monster.isDarkEnoughToSpawn(level, pos, random);
    }

    /** Aparición natural del emperador: oscuridad, altura de 50 o más y ningún otro emperador cerca. */
    static boolean canSpawnEmperor(EntityType<? extends Mob> type, ServerLevelAccessor level, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        if (!canSpawn(type, level, reason, pos, random)) return false;
        if (reason == EntitySpawnReason.SPAWNER) return true;
        return pos.getY() >= 50 && level.getEntitiesOfClass(EmperorScorpion.class, new AABB(pos).inflate(20, 6, 20)).isEmpty();
    }
}
