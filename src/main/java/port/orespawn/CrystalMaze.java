package port.orespawn;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

final class CrystalMaze extends Feature<NoneFeatureConfiguration> {
    static final int FLOOR_Y = 24;
    private static final Direction[] SIDES = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
    private record Edge(int x, int z, Direction side) {}

    CrystalMaze() {
        super(NoneFeatureConfiguration.CODEC);
    }

    // Cada celda mide 4 x 4. Abrir ambos lados de una arista evita paredes dobles en el pasillo.
    private static void visit(int x, int z, boolean[][] visited, List<Edge> frontier) {
        visited[x][z] = true;
        for (var side : SIDES) {
            int nx = x + side.getStepX(), nz = z + side.getStepZ();
            if (nx >= 0 && nx < 4 && nz >= 0 && nz < 4 && !visited[nx][nz])
                frontier.add(new Edge(x, z, side));
        }
    }

    private static boolean[][][] passages(RandomSource random) {
        var open = new boolean[4][4][6];
        var visited = new boolean[4][4];
        var frontier = new ArrayList<Edge>();
        visit(random.nextInt(4), random.nextInt(4), visited, frontier);
        while (!frontier.isEmpty()) {
            Edge edge = frontier.remove(random.nextInt(frontier.size()));
            int nx = edge.x + edge.side.getStepX(), nz = edge.z + edge.side.getStepZ();
            if (visited[nx][nz]) continue;
            open[edge.x][edge.z][edge.side.ordinal()] = true;
            open[nx][nz][edge.side.getOpposite().ordinal()] = true;
            visit(nx, nz, visited, frontier);
        }
        return open;
    }

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var random = context.random();
        var open = passages(random);
        var origin = new BlockPos(context.origin().getX() & ~15, FLOOR_Y, context.origin().getZ() & ~15);
        var holes = new ArrayList<BlockPos>();
        var bedrock = Blocks.BEDROCK.defaultBlockState();
        var air = Blocks.AIR.defaultBlockState();
        for (int x = 0; x < 16; x++) for (int z = 0; z < 16; z++) {
            var pos = origin.offset(x, 0, z);
            var cell = open[x / 4][z / 4];
            boolean perimeter = x == 0 || x == 15 || z == 0 || z == 15;
            boolean wall = !perimeter && (
                (x % 4 == 0 && !cell[Direction.WEST.ordinal()]) ||
                (x % 4 == 3 && !cell[Direction.EAST.ordinal()]) ||
                (z % 4 == 0 && !cell[Direction.NORTH.ordinal()]) ||
                (z % 4 == 3 && !cell[Direction.SOUTH.ordinal()]));
            level.setBlock(pos, bedrock, 2);
            level.setBlock(pos.above(4), bedrock, 2);
            for (int dy = 1; dy <= 3; dy++) level.setBlock(pos.above(dy), wall ? bedrock : air, 2);
            if (!wall) holes.add(pos);
        }
        var stone = CrystalBlocks.STONE.get().defaultBlockState();
        for (int i = 0; i < 4; i++)
            level.setBlock(holes.remove(random.nextInt(holes.size())).above(4), stone, 2);
        level.setBlock(holes.get(random.nextInt(holes.size())), stone, 2);
        return true;
    }
}
