package port.orespawn;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

final class AntNest extends Block {
    private final String species;
    AntNest(Properties properties, String species) { super(properties); this.species = species; }
    @Override protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState old, boolean moved) {
        super.onPlace(state, level, pos, old, moved);
        if (level instanceof ServerLevel server && !old.is(this)) server.scheduleTick(pos, this, 100);
    }

    @Override protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        releaseAnts(level, pos, random);
        level.scheduleTick(pos, this, 400 + random.nextInt(201));
    }

    @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Natural nests and nests from older saves may have no scheduled tick yet.
        if (!level.getBlockTicks().hasScheduledTick(pos, this)) level.scheduleTick(pos, this, 1);
    }

    private void releaseAnts(ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isEmptyBlock(pos.above())) return;
        var ant = Creatures.SPECIES.get(species).type().get();
        int nearby = level.getEntitiesOfClass(Creatures.Creature.class, new AABB(pos).inflate(16), e -> e.getType() == ant).size();
        int count = Math.min(20 - nearby, 2 + random.nextInt(6));
        for (int i = 0; i < count; i++) ant.spawn(level, pos.above(), EntitySpawnReason.TRIGGERED);
    }
}
