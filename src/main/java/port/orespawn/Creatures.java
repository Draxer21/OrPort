package port.orespawn;

import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.*;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public final class Creatures {
    record Species(DeferredHolder<EntityType<?>, EntityType<Creature>> type, double health, double speed, double damage) {}
    static final Map<String, Species> SPECIES = new LinkedHashMap<>();
    private static final DeferredRegister<EntityType<?>> TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, "orespawn");
    static final DeferredHolder<EntityType<?>,EntityType<CageProjectile>> PROJECTILE = TYPES.register("thrown_critter_cage", key ->
        EntityType.Builder.<CageProjectile>of(CageProjectile::new, MobCategory.MISC).sized(0.25f,0.25f)
            .clientTrackingRange(5).build(ResourceKey.create(Registries.ENTITY_TYPE,key)));

    static void register(IEventBus bus) {
        add("alosaurus", 1.9f, 3.6f, 50, 0.35, 18.0, true, "alosaurus_egg");
        add("trex", 2.0f, 4.2f, 160, 0.38, 22.0, true, "trex_egg");
        add("baryonyx", 1.5f, 2.8f, 40, 0.25, 8.0, false, "baryonyx_egg");
        add("camarasaurus", 0.5f, 1.2f, 20, 0.2, 1.0, false, "camarasaurus_egg");
        add("pointysaurus", 2.9f, 2.9f, 40, 0.35, 15.0, true, "pointysaurus_egg");
        add("cryolophosaurus", 0.75f, 0.75f, 45, 0.25, 8.0, true, "cryolophosaurus_egg");
        add("cavefisher", 1.35f, 0.75f, 20, 0.2, 4.0, true, "cavefisher_egg");
        add("red_ant", 0.2f, 0.2f, 2, 0.2, 1.0, false, "red_ant_egg");
        add("rainbow_ant", 0.1f, 0.1f, 1, 0.15, 0, false, "rainbow_ant_egg");
        add("unstable_ant", 0.1f, 0.1f, 1, 0.15, 0, false, "unstable_ant_egg");
        add("bird", 0.4f, 0.4f, 2, 0.1, 2, false, "bird_egg");
        add("butterfly", 0.4f, 0.4f, 1, 0.1, 2, false, "butterfly_egg");
        add("gammametroid", 1.5f, 1.5f, 100, 0.15, 10.0, true, "gammametroid_egg");
        add("spyro", 0.5f, 0.5f, 200, 0.3, 5.0, false, "spyro_egg");
        add("dragonfly", 1.5f, 0.5f, 10, 0.25, 2.0, false, "dragonfly_egg");
        add("firefly", 0.4f, 0.8f, 1, 0.25, 0.0, false, "firefly_egg");
        add("mosquito", 0.2f, 0.2f, 2, 0.25, 0.0, false, "mosquito_egg");
        add("nastysaurus", 2.2f, 4.6f, 200, 0.35, 32.0, true, "nastysaurus_egg");
        add("alien", 1.1f, 3.25f, 100, 0.65, 12.0, true, "alien_egg");
        add("velocityraptor", 0.5f, 0.6f, 10, 0.55, 2.0, false, "velocityraptor_egg");
        add("small_worm", 0.25f, 1.0f, 10, 0.25, 5.0, true, "smallworm_egg");
        add("medium_worm", 0.5f, 2.0f, 30, 0.25, 10.0, true, "mediumworm_egg");
        add("large_worm", 1.55f, 2.5f, 90, 0.25, 18.0, true, "largeworm_egg");
        add("moth", 0.5f, 0.5f, 1, 0.1, 0.0, false, "moth_egg");
        add("kyuubi", 0.5f, 1.25f, 125, 0.25, 10.0, true, "kyuubi_egg");
        add("hercules_beetle", 3.25f, 2.75f, 250, 0.25, 30, true, "hercules_beetle_egg");
        add("triffid", 2f, 4f, 100, 0, 20, true, "triffid_egg");
        add("scorpion", 0.85f, 0.55f, 15, 0.2, 4, true, "scorpion_egg");
        add("emperor_scorpion", 3.5f, 3f, 350, 0.35, 35, true, "emperor_scorpion_egg");
        add("basilisk", 1.6f, 3.5f, 200, 0.4, 24, true, "basilisk_egg");
        add("mantis", 2.2f, 3f, 120, 0.32, 16, true, "mantis_egg");
        add("mothra", 5.0f, 2.0f, 150, 0.35, 12.0, true, "mothra_egg");
        add("brutalfly", 5.0f, 2.0f, 110, 0.35, 10.0, true, "brutalfly_egg");
        add("beaver", 0.6f, 0.8f, 15, 0.15, 1.0, false, "beaver_egg");
        add("cassowary", 0.5f, 1.2f, 10, 0.25, 8.0, false, "cassowary_egg");
        add("termite", 0.2f, 0.2f, 5, 0.2, 2.0, false, "termite_egg");
        add("redcow", 0.8f, 0.8f, 10, 0.25, 2, false, "redcow_egg");
        add("stinkbug", 0.55f, 0.55f, 5, 0.15, 0.0, false, "stinkbug_egg");
        add("doom_worm", 0.8f, 0.8f, 3000, 0.1, 2, true, "doomworm_egg");
        TYPES.register(bus);
        CreatureSounds.register(bus);
        bus.addListener(Creatures::placements);
        bus.addListener(Creatures::attributes);
    }
    private static void add(String id, float width, float height, double health, double speed, double damage, boolean hostile, String egg) {
        var category = hostile ? MobCategory.MONSTER : MobCategory.CREATURE;
        EntityType.EntityFactory<Creature> factory=switch(id){
            case "scorpion" -> Scorpion::new;
            case "emperor_scorpion" -> EmperorScorpion::new;
            case "basilisk" -> Basilisk::new;
            case "triffid" -> Triffid::new;
            case "mantis" -> Mantis::new;
            case "brutalfly" -> Brutalfly::new;
            case "red_ant" -> RedAnt::new;
            case "rainbow_ant", "unstable_ant" -> TravelAnt::new;
            case "doom_worm" -> DoomWorm::new;
            case "redcow","baryonyx" -> BreedingCreature::new;
            default -> Companion.supports(id)?Companion::new:Creature::new;
        };
        var type = TYPES.register(id, key -> EntityType.Builder.<Creature>of(factory, category)
            .sized(width, height).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
        SPECIES.put(id, new Species(type, health, speed, damage));
        if (egg != null) OreSpawn.ITEMS.registerItem(egg, SpawnEggItem::new, p -> p.spawnEgg(type.get()));
    }
    private static void placements(net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent event) {
        SPECIES.forEach((id, data) -> event.register(data.type.get(), SpawnPlacementTypes.ON_GROUND,
            net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            switch (id) {
                case "scorpion" -> Scorpion::canSpawn;
                case "emperor_scorpion" -> Scorpion::canSpawnEmperor;
                case "mantis" -> Creatures::canSpawnMantis;
                default -> Mob::checkMobSpawnRules;
            },
            net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation.REPLACE));
    }
    /** Mantis.getCanSpawnHere del clásico: altura 50 o más y ninguna otra mantis en 32 bloques. */
    private static boolean canSpawnMantis(EntityType<Creature> type, net.minecraft.world.level.ServerLevelAccessor level,
            EntitySpawnReason reason, net.minecraft.core.BlockPos pos, net.minecraft.util.RandomSource random) {
        if (!Mob.checkMobSpawnRules(type, level, reason, pos, random)) return false;
        if (reason == EntitySpawnReason.SPAWNER) return true;
        return pos.getY() >= 50 && level.getEntitiesOfClass(Mantis.class, new net.minecraft.world.phys.AABB(pos).inflate(32, 16, 32)).isEmpty();
    }
    private static void attributes(EntityAttributeCreationEvent event) {
        SPECIES.forEach((id,data)->event.put(data.type.get(), Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, data.health).add(Attributes.MOVEMENT_SPEED, data.speed)
            .add(Attributes.ARMOR, switch(id){case "trex" -> 14; case "hercules_beetle" -> 19; case "scorpion" -> 10; case "emperor_scorpion" -> 20; case "basilisk" -> 15; case "triffid" -> 12; case "mantis" -> 10; case "mothra" -> 8; case "brutalfly" -> 6; default -> 0;})
            .add(Attributes.FOLLOW_RANGE, switch(id){case "scorpion" -> 8; case "emperor_scorpion" -> 24; default -> 16;})
            .add(Attributes.TEMPT_RANGE, 10).add(Attributes.FLYING_SPEED, data.speed).add(Attributes.ATTACK_DAMAGE, data.damage).build()));
    }
    public static class Creature extends PathfinderMob {
        public Creature(EntityType<? extends PathfinderMob> type, Level level) { super(type, level);
            if (flies()) {
                moveControl = new net.minecraft.world.entity.ai.control.FlyingMoveControl<>(this, 18, true);
                setNoGravity(true);
            }
        }
        @Override protected net.minecraft.sounds.SoundEvent getAmbientSound() {
            String sound = switch (net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(getType()).getPath()) {
                case "basilisk" -> getRandom().nextBoolean() ? "entity.basilisk.ambient" : null;
                case "alosaurus" -> "entity.alosaurus.living";
                case "trex" -> "entity.trex.ambient";
                case "pointysaurus" -> "entity.alosaurus.living";
                case "cryolophosaurus" -> "entity.cryo.living";
                case "gammametroid" -> "entity.gammametroid.living";
                case "nastysaurus" -> "entity.alosaurus.living";
                case "alien" -> "entity.alien.living";
                case "bird" -> "entity.birds.birds"+(1+getRandom().nextInt(23));
                default -> null;
            };
            return sound == null ? super.getAmbientSound() : net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(net.minecraft.resources.Identifier.fromNamespaceAndPath("orespawn", sound));
        }
        @Override protected net.minecraft.sounds.SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource cause) {
            String sound = switch (net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(getType()).getPath()) {
                case "basilisk" -> "entity.basilisk.hurt";
                case "scorpion" -> "entity.scorpion.hit";
                case "emperor_scorpion" -> "entity.basilisk.hurt";
                case "alosaurus" -> "entity.alosaurus.hurt";
                case "trex" -> "entity.alosaurus.hurt";
                case "baryonyx" -> "entity.duck.hurt";
                case "camarasaurus" -> "entity.cryo.hurt";
                case "pointysaurus" -> "entity.alosaurus.hurt";
                case "cryolophosaurus" -> "entity.cryo.hurt";
                case "gammametroid" -> "entity.duck.hurt";
                case "spyro" -> "entity.duck.hurt";
                case "nastysaurus" -> "entity.alosaurus.hurt";
                case "alien" -> "entity.alien.hurt";
                case "velocityraptor" -> "entity.cryo.hurt";
                case "kyuubi" -> "entity.alosaurus.hurt";
                case "cassowary" -> "entity.duck.hurt";
                case "doom_worm" -> "entity.alosaurus.hurt";
                case "small_worm" -> "entity.misc.little_splat";
                case "medium_worm" -> "entity.misc.big_splat";
                default -> null;
            };
            return sound == null ? super.getHurtSound(cause) : net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(net.minecraft.resources.Identifier.fromNamespaceAndPath("orespawn", sound));
        }
        @Override protected net.minecraft.sounds.SoundEvent getDeathSound() {
            String sound = switch (net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(getType()).getPath()) {
                case "basilisk" -> "entity.basilisk.death";
                case "scorpion" -> "entity.cryo.death";
                case "emperor_scorpion" -> "entity.basilisk.death";
                case "alosaurus" -> "entity.alosaurus.death";
                case "trex" -> "entity.trex.death";
                case "baryonyx" -> "entity.duck.hurt";
                case "camarasaurus" -> "entity.cryo.death";
                case "pointysaurus" -> "entity.alosaurus.death";
                case "cryolophosaurus" -> "entity.cryo.death";
                case "cavefisher" -> "entity.alosaurus.death";
                case "gammametroid" -> "entity.alosaurus.death";
                case "doom_worm" -> "entity.alosaurus.death";
                case "stinkbug" -> "entity.stinkbug.fart"+(1+getRandom().nextInt(9));
                default -> null;
            };
            return sound == null ? super.getDeathSound() : net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(net.minecraft.resources.Identifier.fromNamespaceAndPath("orespawn", sound));
        }
        private int fireCooldown;
        @Override public void aiStep() {
            super.aiStep();
            if (!(level() instanceof net.minecraft.server.level.ServerLevel server)) return;
            if (fireCooldown > 0) fireCooldown--;
            var target = getTarget();
            if (target == null || !target.isAlive() || fireCooldown != 0 || !hasLineOfSight(target)) return;
            var id = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(getType()).getPath();
            if (!id.equals("mothra") && !id.equals("spyro") && !id.equals("kyuubi") && !id.equals("brutalfly")) return;
            if (distanceToSqr(target) < 16 || distanceToSqr(target) > 1024) return;
            var direction = target.getEyePosition().subtract(getEyePosition()).normalize();
            boolean mothra=id.equals("mothra");
            var difficulty=server.getDifficulty();
            if(mothra && difficulty==net.minecraft.world.Difficulty.PEACEFUL)return;
            boolean explosive=mothra && (difficulty==net.minecraft.world.Difficulty.HARD ||
                difficulty==net.minecraft.world.Difficulty.NORMAL && getRandom().nextBoolean());
            var fire = explosive
                ? new net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball(server,this,direction,1)
                : new net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball(server,this,direction);
            fire.setPos(getEyePosition().add(direction.scale(mothra || id.equals("brutalfly") ? getBbWidth()/2+.3 : .75)));
            if(!server.addFreshEntity(fire))return;
            if(mothra)heal(1);
            fireCooldown = id.equals("mothra") ? 35 : 55;
        }
        public boolean isResting() {return false;}
        @Override public boolean doHurtTarget(net.minecraft.server.level.ServerLevel level,Entity target){
            if(!super.doHurtTarget(level,target))return false;
            if(!(target instanceof LivingEntity victim))return true;
            if (species().equals("scorpion") || species().equals("emperor_scorpion")) {
                String sound = species().equals("emperor_scorpion") && random.nextInt(3) != 1
                    ? "entity.scorpion.living" : "entity.scorpion.attack";
                playSound(net.minecraft.core.registries.BuiltInRegistries.SOUND_EVENT.getValue(
                    net.minecraft.resources.Identifier.fromNamespaceAndPath("orespawn", sound)), 1, 1);
            }
            double strength=switch(species()){case "alien" -> 1.1;case "trex","alosaurus","nastysaurus" -> 1.2;case "pointysaurus" -> .8;default -> 0;};
            if(species().equals("hercules_beetle")){
                var away=victim.position().subtract(position()).multiply(1,0,1).normalize();
                victim.push(away.x*.45,random.nextFloat()*(victim instanceof Player?2.5:1.25),away.z*.45);
            }
            if(strength>0){
                var away=victim.position().subtract(position()).multiply(1,0,1).normalize();
                victim.push(away.x*strength,victim instanceof Player?.2:.1,away.z*strength);
            }
            if(species().equals("alien") && random.nextInt(5)==0)victim.addEffect(new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.POISON,level.getDifficulty()==net.minecraft.world.Difficulty.EASY?40:30),this);
            return true;
        }
        @Override public void die(net.minecraft.world.damagesource.DamageSource source){
            if(!dead && species().equals("stinkbug") && level() instanceof net.minecraft.server.level.ServerLevel server)
                for(var nearby:server.getEntitiesOfClass(LivingEntity.class,getBoundingBox().inflate(8,5,8),e->e!=this && e.isAlive()))
                    nearby.addEffect(new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.NAUSEA,300),this);
            super.die(source);
        }
        final String species(){return net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(getType()).getPath();}
        @Override protected void dropCustomDeathLoot(net.minecraft.server.level.ServerLevel level,net.minecraft.world.damagesource.DamageSource source,boolean recent){
            super.dropCustomDeathLoot(level,source,recent);
            String swarm=switch(species()){case "brutalfly" -> "butterfly";case "mothra" -> "moth";default -> null;};
            if(swarm!=null)for(int i=0;i<20;i++){
                var mob=SPECIES.get(swarm).type().get().create(level,EntitySpawnReason.TRIGGERED);
                if(mob!=null){mob.snapTo(getX()+random.nextDouble()-.5,getY()+1,getZ()+random.nextDouble()-.5,random.nextFloat()*360,0);level.addFreshEntity(mob);}
            }
        }
        private boolean flies() {
            String id = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(getType()).getPath();
            return switch (id) {
                case "mantis", "bird", "butterfly", "firefly", "mosquito", "dragonfly", "moth", "mothra", "brutalfly", "spyro", "gammametroid" -> true;
                default -> false;
            };
        }
        @Override protected net.minecraft.world.entity.ai.navigation.PathNavigation createNavigation(Level level) {
            return flies() ? new net.minecraft.world.entity.ai.navigation.FlyingPathNavigation(this, level) : super.createNavigation(level);
        }

        @Override protected void registerGoals() {
            goalSelector.addGoal(0, new FloatGoal(this));
            if(species().equals("termite") || species().equals("beaver"))goalSelector.addGoal(4,new WoodForagingGoal(this));
            switch(species()){
                case "beaver","cassowary","stinkbug" -> goalSelector.addGoal(4,new AvoidEntityGoal<>(this,Player.class,species().equals("stinkbug")?4:8,1,1.4));
            }
            switch(species()){
                case "baryonyx","beaver","cassowary","camarasaurus","spyro" -> goalSelector.addGoal(3,new AvoidEntityGoal<>(this,net.minecraft.world.entity.monster.Monster.class,8,1,1.4));
            }
            switch(species()){
                case "baryonyx","beaver","cassowary","termite","stinkbug","cryolophosaurus","velocityraptor","spyro","camarasaurus","kyuubi" -> goalSelector.addGoal(3,new PanicGoal(this,1.4));
            }
            if (getType().getCategory() == MobCategory.MONSTER) {
                targetSelector.addGoal(1, new HurtByTargetGoal(this));
                targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
                goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1, false));
            }
            targetSelector.addGoal(3, new HurtByTargetGoal(this));
            goalSelector.addGoal(5, flies() ? new WaterAvoidingRandomFlyingGoal(this, 1.0) : new WaterAvoidingRandomStrollGoal(this, 1.0));
            goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8));
            goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        }
    }
}



