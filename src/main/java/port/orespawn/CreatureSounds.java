package port.orespawn;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
final class CreatureSounds {
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT,"orespawn");
    static void register(IEventBus bus) {
        for (String name : new String[]{"entity.basilisk.ambient",
            "entity.basilisk.hurt",
            "entity.basilisk.death",
            "entity.scorpion.attack",
            "entity.scorpion.hit",
            "entity.scorpion.living",
            "entity.alosaurus.living",
            "entity.alosaurus.hurt",
            "entity.alosaurus.death",
            "entity.trex.ambient",
            "entity.trex.death",
            "entity.duck.hurt",
            "entity.cryo.hurt",
            "entity.cryo.death",
            "entity.cryo.living",
            "entity.birds.birds1",
            "entity.birds.birds2",
            "entity.birds.birds3",
            "entity.birds.birds4",
            "entity.birds.birds5",
            "entity.birds.birds6",
            "entity.birds.birds7",
            "entity.birds.birds8",
            "entity.birds.birds9",
            "entity.birds.birds10",
            "entity.birds.birds11",
            "entity.birds.birds12",
            "entity.birds.birds13",
            "entity.birds.birds14",
            "entity.birds.birds15",
            "entity.birds.birds16",
            "entity.birds.birds17",
            "entity.birds.birds18",
            "entity.birds.birds19",
            "entity.birds.birds20",
            "entity.birds.birds21",
            "entity.birds.birds22",
            "entity.birds.birds23",
            "entity.gammametroid.living",
            "entity.alien.living",
            "entity.alien.hurt",
            "entity.alien.death",
            "entity.misc.little_splat",
            "entity.misc.big_splat",
            "entity.stinkbug.fart1",
            "entity.stinkbug.fart2",
            "entity.stinkbug.fart3",
            "entity.stinkbug.fart4",
            "entity.stinkbug.fart5",
            "entity.stinkbug.fart6",
            "entity.stinkbug.fart7",
            "entity.stinkbug.fart8",
            "entity.stinkbug.fart9"})
            SOUNDS.register(name, id -> SoundEvent.createVariableRangeEvent(id));
        SOUNDS.register(bus);
    }
}
