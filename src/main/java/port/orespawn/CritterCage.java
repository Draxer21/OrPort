package port.orespawn;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.registries.DeferredItem;

public final class CritterCage extends Item {
    static final Map<String, DeferredItem<CritterCage>> CAGES = new HashMap<>();
    static DeferredItem<CritterCage> empty;
    final String species;
    static final Map<String,Float> CAPTURE_CHANCE = Map.ofEntries(Map.entry("alosaurus_cage", 0.4f), Map.entry("trex_cage", 0.4f), Map.entry("cow_cage", 0.4f), Map.entry("creeper_cage", 0.4f), Map.entry("ghast_cage", 0.4f), Map.entry("horse_cage", 0.4f), Map.entry("pig_cage", 0.4f), Map.entry("zombie_cage", 0.4f), Map.entry("gammametroid_cage", 0.4f), Map.entry("spyro_cage", 0.4f), Map.entry("dragonfly_cage", 0.4f), Map.entry("firefly_cage", 0.4f), Map.entry("nastysaurus_cage", 0.4f), Map.entry("alien_cage", 0.4f), Map.entry("velocityraptor_cage", 0.4f), Map.entry("smallworm_cage", 0.4f), Map.entry("mediumworm_cage", 0.4f), Map.entry("largeworm_cage", 0.4f), Map.entry("kyuubi_cage", 0.4f), Map.entry("scorpion_cage", 0.4f), Map.entry("emperor_scorpion_cage", 0.4f), Map.entry("mantis_cage", 0.4f), Map.entry("mothra_cage", 0.4f), Map.entry("brutalfly_cage", 0.4f), Map.entry("beaver_cage", 0.4f), Map.entry("redcow_cage", 0.4f), Map.entry("stinkbug_cage", 0.4f), Map.entry("cassowary_cage", 0.4f));
    private CritterCage(Properties properties, String species) { super(properties); this.species = species; }

    static void register() {
        add("empty_cage", null);
        add("alosaurus_cage", "orespawn:alosaurus");
        add("trex_cage", "orespawn:trex");
        add("cow_cage", "minecraft:cow");
        add("creeper_cage", "minecraft:creeper");
        add("ghast_cage", "minecraft:ghast");
        add("horse_cage", "minecraft:horse");
        add("pig_cage", "minecraft:pig");
        add("zombie_cage", "minecraft:zombie");
        add("gammametroid_cage", "orespawn:gammametroid");
        add("spyro_cage", "orespawn:spyro");
        add("dragonfly_cage", "orespawn:dragonfly");
        add("firefly_cage", "orespawn:firefly");
        add("nastysaurus_cage", "orespawn:nastysaurus");
        add("alien_cage", "orespawn:alien");
        add("velocityraptor_cage", "orespawn:velocityraptor");
        add("smallworm_cage", "orespawn:small_worm");
        add("mediumworm_cage", "orespawn:medium_worm");
        add("largeworm_cage", "orespawn:large_worm");
        add("kyuubi_cage", "orespawn:kyuubi");
        add("scorpion_cage", "orespawn:scorpion");
        add("emperor_scorpion_cage", "orespawn:emperor_scorpion");
        add("mantis_cage", "orespawn:mantis");
        add("mothra_cage", "orespawn:mothra");
        add("brutalfly_cage", "orespawn:brutalfly");
        add("beaver_cage", "orespawn:beaver");
        add("redcow_cage", "orespawn:redcow");
        add("stinkbug_cage", "orespawn:stinkbug");
        add("cassowary_cage", "orespawn:cassowary");
    }
    private static void add(String id, String species) {
        var item = OreSpawn.ITEMS.registerItem(id, p -> new CritterCage(p.stacksTo(species == null ? 64 : 1), species));
        if (species == null) empty = item;
        else CAGES.put(species, item);
    }
    @Override public InteractionResult use(net.minecraft.world.level.Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel server) {
            var stack = player.getItemInHand(hand);
            var shot = new CageProjectile(player, server, stack.copyWithCount(1));
            shot.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1.5f, 1);
            if (!server.addFreshEntity(shot)) return InteractionResult.FAIL;
            stack.consume(1, player);
        }
        return InteractionResult.SUCCESS;
    }
    @Override public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (species != null || !(target instanceof Mob mob) || !mob.isAlive() || mob.isPassenger() || mob.isVehicle()) return InteractionResult.PASS;
        var cage = CAGES.get(BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType()).toString());
        if (cage == null) return InteractionResult.PASS;
        if (player.level() instanceof ServerLevel) {
            var filled = new ItemStack(cage.get());
            if (mob instanceof Companion pet) pet.pack(filled);
            if (mob.hasCustomName()) filled.set(DataComponents.CUSTOM_NAME, mob.getCustomName());
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, filled));
            mob.discard();
        }
        return InteractionResult.SUCCESS;
    }
    @Override public InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;
        if (species == null) return use(context.getLevel(), player, context.getHand());
        if (context.getLevel() instanceof ServerLevel server) {
            var type = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(species));
            var pos = context.getClickedPos().relative(context.getClickedFace());
            if (type == null || !player.mayUseItemAt(pos, context.getClickedFace(), context.getItemInHand())) return InteractionResult.FAIL;
            var mob = type.spawn(server, context.getItemInHand(), player, pos, EntitySpawnReason.BUCKET, true, false);
            if (mob == null) return InteractionResult.FAIL;
            if (mob instanceof Companion pet) pet.unpack(context.getItemInHand());
            if (mob instanceof Mob living) living.setPersistenceRequired();
            player.setItemInHand(context.getHand(), ItemUtils.createFilledResult(context.getItemInHand(), player, new ItemStack(empty.get())));
        }
        return InteractionResult.SUCCESS;
    }
}
