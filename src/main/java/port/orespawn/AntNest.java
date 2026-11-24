package port.orespawn;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

final class AntNest extends Block {
    private final String species;
    AntNest(Properties properties, String species) { super(properties); this.species = species; }
    @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isEmptyBlock(pos.above()) || !level.canSeeSky(pos.above())) return;
        var ant = Creatures.SPECIES.get(species).type().get();
        int nearby = level.getEntitiesOfClass(Creatures.Creature.class, new AABB(pos).inflate(16), e -> e.getType() == ant).size();
        int count = Math.min(20 - nearby, 2 + random.nextInt(6));
        for (int i = 0; i < count; i++) ant.spawn(level, pos.above(), EntitySpawnReason.TRIGGERED);
    }
}
