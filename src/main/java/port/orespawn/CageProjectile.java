package port.orespawn;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

final class CageProjectile extends ThrowableItemProjectile {
    CageProjectile(EntityType<? extends CageProjectile> type, Level level) { super(type, level); }
    CageProjectile(LivingEntity owner, ServerLevel level, ItemStack stack) { super(Creatures.PROJECTILE.get(), owner, level, stack); }
    @Override protected Item getDefaultItem() { return CritterCage.empty.get(); }
    @Override protected void onHit(HitResult hit) {
        super.onHit(hit);
        if (!(level() instanceof ServerLevel server) || !(getItem().getItem() instanceof CritterCage cage)) return;
        ItemStack result = getItem().copyWithCount(1);
        if (cage.species != null) {
            var type = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(cage.species));
            var pos = hit instanceof BlockHitResult b ? b.getBlockPos().relative(b.getDirection()) : blockPosition();
            var released = type == null ? null : type.spawn(server, pos, EntitySpawnReason.BUCKET);
            if (released != null) {
                if (released instanceof Companion pet) pet.unpack(getItem());
                if (getItem().has(DataComponents.CUSTOM_NAME)) released.setCustomName(getItem().get(DataComponents.CUSTOM_NAME));
                if (released instanceof Mob mob) mob.setPersistenceRequired();
                result = new ItemStack(CritterCage.empty.get());
            }
        } else if (hit instanceof EntityHitResult e && e.getEntity() instanceof Mob mob && mob.isAlive() && !mob.isVehicle() && !mob.isPassenger()) {
            var filled = CritterCage.CAGES.get(BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType()).toString());
            if (filled != null && server.getRandom().nextFloat() < CritterCage.CAPTURE_CHANCE.getOrDefault(filled.getId().getPath(),1f)) {
                result = new ItemStack(filled.get());
                if (mob instanceof Companion pet) pet.pack(result);
                if (mob.hasCustomName()) result.set(DataComponents.CUSTOM_NAME,mob.getCustomName());
                mob.discard();
            }
        }
        spawnAtLocation(server,result);
        discard();
    }
}
