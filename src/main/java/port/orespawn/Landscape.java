package port.orespawn;
import net.minecraft.core.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

final class Landscape {
    static void register(IEventBus bus) {
        var features = DeferredRegister.<Feature<?>>create(Registries.FEATURE, "orespawn");
        features.register("mining_dungeon", () -> new Dungeon());
        features.register("scraggly_tree", () -> new ScragglyTree());
        features.register(bus);
        for (String name : new String[]{"corn","butterfly","mosquito","firefly","moth"}) {
            var crop = OreSpawn.BLOCKS.registerBlock(name+"_plant", p -> new Plant(p, name), p -> p.noCollision().noOcclusion().randomTicks().instabreak().sound(SoundType.CROP));
            String seed = name.equals("corn") ? name : name+"_seed";
            OreSpawn.ITEMS.registerItem(seed, p -> new BlockItem(crop.get(), name.equals("corn") ? p.food(new FoodProperties.Builder().nutrition(1).saturationModifier(1f).build()) : p));
        }
        var torch = OreSpawn.BLOCKS.registerBlock("extreme_torch", p -> new TorchBlock(ParticleTypes.FLAME,p){}, p -> p.noCollision().instabreak().lightLevel(s->15));
        var wall = OreSpawn.BLOCKS.registerBlock("extreme_torch_wall", p -> new WallTorchBlock(ParticleTypes.FLAME,p){}, p -> p.noCollision().instabreak().lightLevel(s->15));
        OreSpawn.ITEMS.registerItem("extreme_torch", p -> new StandingAndWallBlockItem(torch.get(), wall.get(), Direction.DOWN,p.useBlockDescriptionPrefix()));
        OreSpawn.ITEMS.registerSimpleBlockItem(OreSpawn.BLOCKS.registerBlock("red_ant_troll_block", p -> new Block(p) {
            @Override public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
                if (level instanceof ServerLevel server) for (int i=0;i<20;i++)
                    Creatures.SPECIES.get("red_ant").type().get().spawn(server,pos.above(),EntitySpawnReason.TRIGGERED);
                return super.playerWillDestroy(level,pos,state,player);
            }
        },p -> p.strength(1.5f)));
    }
    private static final class Plant extends CropBlock {
        final String species;
        Plant(Properties p,String species) { super(p); this.species=species; }
        @Override protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
            return state.is(BlockTags.DIRT) || super.mayPlaceOn(state,level,pos);
        }
        @Override protected ItemLike getBaseSeedId() { return asItem(); }
        @Override protected boolean isRandomlyTicking(BlockState state) { return true; }
        @Override protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
            if (!isMaxAge(state)) {
                if (level.getRawBrightness(pos,0)>=9 && random.nextInt(3)==0) level.setBlock(pos,getStateForAge(getAge(state)+1),2);
                return;
            }
            if (species.equals("corn") || !level.isEmptyBlock(pos.above()) || random.nextInt(4)!=0) return;
            var type = Creatures.SPECIES.get(species).type().get();
            if (level.getEntitiesOfClass(Creatures.Creature.class,new AABB(pos).inflate(12), e->e.getType()==type).size()<4)
                type.spawn(level,pos.above(),EntitySpawnReason.TRIGGERED);
        }
    }
    private static final class Dungeon extends Feature<NoneFeatureConfiguration> {
        Dungeon() {super(NoneFeatureConfiguration.CODEC);}
        @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
            var level=context.level(); var random=context.random(); var origin=context.origin();
            for(int x=0;x<12;x++) for(int z=0;z<12;z++) for(int y=0;y<6;y++) {
                boolean wall=x==0||x==11||z==0||z==11||y==5;
                var block=wall ? (random.nextBoolean()?Blocks.OBSIDIAN:Blocks.COBBLESTONE) : y==0?Blocks.OBSIDIAN:Blocks.AIR;
                level.setBlock(origin.offset(x,y,z),block.defaultBlockState(),2);
            }
            var center=origin.offset(6,1,6);
            level.setBlock(center,Blocks.SPAWNER.defaultBlockState(),2);
            if(level.getBlockEntity(center) instanceof SpawnerBlockEntity spawner) {
                String id=new String[]{"alien","gammametroid","cryolophosaurus"}[random.nextInt(3)];
                spawner.setEntityId(Creatures.SPECIES.get(id).type().get(),random);
            }
            var pos=origin.offset(6,1,1);
            level.setBlock(pos,Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING,Direction.SOUTH),2);
            if(level.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                chest.setLootTable(ResourceKey.create(Registries.LOOT_TABLE,Identifier.fromNamespaceAndPath("orespawn","chests/generic_dungeon")));
                chest.setLootTableSeed(random.nextLong());
            }
            return true;
        }
    }

    /**
     * ScragglyTreeWithBranches de ChunkProviderOreSpawn4: tronco corto que sigue subiendo en pasos
     * torcidos, ramas al azar y hojas sueltas alrededor de cada tramo.
     */
    private static final class ScragglyTree extends Feature<NoneFeatureConfiguration> {
        ScragglyTree() { super(NoneFeatureConfiguration.CODEC); }
        private static final BlockState LOG = Blocks.OAK_LOG.defaultBlockState();
        private static final BlockState LEAVES = Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true);

        @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
            var level = context.level(); var random = context.random(); var origin = context.origin();
            // Como el clásico, busca en la columna el césped con aire encima en lugar de fiarse del mapa de alturas.
            int bottom = Math.max(level.getMinY(), origin.getY() - 64), top = Math.min(level.getMaxY() - 1, origin.getY() + 128);
            origin = null;
            for (int y = top; y > bottom; y--) {
                var pos = new BlockPos(context.origin().getX(), y, context.origin().getZ());
                if (level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && level.isEmptyBlock(pos)) { origin = pos; break; }
            }
            if (origin == null) return false;
            int trunk = 1 + random.nextInt(3), height = trunk + random.nextInt(12);
            for (int i = 1; i < trunk; i++) if (!open(level, origin.above(i))) return false;
            for (int i = 0; i < trunk; i++) level.setBlock(origin.above(i), LOG, 2);
            var pos = origin.above(trunk - 1);
            for (int i = trunk; i < height; i++) {
                pos = pos.offset(random.nextInt(2) - random.nextInt(2), random.nextInt(4) > 0 ? 1 : 0, random.nextInt(2) - random.nextInt(2));
                if (!open(level, pos)) break;
                level.setBlock(pos, LOG, 2);
                if (random.nextInt(4) == 1) branch(level, random, pos, random.nextInt(1 + height - i),
                    random.nextInt(2) - random.nextInt(2), random.nextInt(2) - random.nextInt(2));
                leaves(level, random, pos);
            }
            return true;
        }

        private static void branch(WorldGenLevel level, RandomSource random, BlockPos pos, int length, int dx, int dz) {
            for (int i = 0; i < length; i++) {
                int x = Math.clamp(random.nextInt(2) - random.nextInt(2) + dx, -1, 1);
                int z = Math.clamp(random.nextInt(2) - random.nextInt(2) + dz, -1, 1);
                pos = pos.offset(x, random.nextInt(3) > 0 ? 1 : 0, z);
                if (!open(level, pos)) return;
                level.setBlock(pos, LOG, 2);
                leaves(level, random, pos);
            }
        }

        private static void leaves(WorldGenLevel level, RandomSource random, BlockPos pos) {
            for (int x = -1; x < 2; x++) for (int z = -1; z < 2; z++)
                if (random.nextInt(2) == 1 && level.isEmptyBlock(pos.offset(x, 0, z))) level.setBlock(pos.offset(x, 0, z), LEAVES, 2);
            if (random.nextInt(2) == 1 && level.isEmptyBlock(pos.above())) level.setBlock(pos.above(), LEAVES, 2);
        }

        private static boolean open(WorldGenLevel level, BlockPos pos) {
            var state = level.getBlockState(pos);
            return state.isAir() || state.is(Blocks.OAK_LOG) || state.is(Blocks.OAK_LEAVES);
        }
    }
}
