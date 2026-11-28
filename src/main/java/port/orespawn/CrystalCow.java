package port.orespawn;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

final class CrystalCow extends BreedingCreature {
    CrystalCow(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }

    private static SoundEvent cowSound(String action) {
        return BuiltInRegistries.SOUND_EVENT.getValue(Identifier.withDefaultNamespace("entity.cow." + action));
    }

    @Override protected SoundEvent getAmbientSound() { return cowSound("ambient"); }
    @Override protected SoundEvent getHurtSound(DamageSource source) { return cowSound("hurt"); }
    @Override protected SoundEvent getDeathSound() { return cowSound("death"); }
}
