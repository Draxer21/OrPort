package port.orespawn;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;

final class CrystalBlocks {
    static void register() {
        var blocks = OreSpawn.BLOCKS;
        var items = OreSpawn.ITEMS;
        items.registerSimpleBlockItem(blocks.registerSimpleBlock("crystal_stone",
            p -> p.strength(1.5f, 6).requiresCorrectToolForDrops()));
        items.registerSimpleBlockItem(blocks.registerSimpleBlock("crystal_coal_ore",
            p -> p.strength(3).requiresCorrectToolForDrops()));
        items.registerSimpleBlockItem(blocks.registerSimpleBlock("crystal_grass",
            p -> p.strength(0.6f).sound(SoundType.GRASS)));
        items.registerSimpleBlockItem(blocks.registerBlock("crystal_log", RotatedPillarBlock::new,
            p -> p.strength(2).sound(SoundType.WOOD)));
        items.registerSimpleBlockItem(blocks.registerSimpleBlock("crystal_planks",
            p -> p.strength(2, 3).sound(SoundType.WOOD)));
        items.registerSimpleBlockItem(blocks.registerBlock("crystal_leaves",
            p -> new TintedParticleLeavesBlock(0.01f, p),
            p -> p.strength(0.2f).sound(SoundType.GRASS).randomTicks().noOcclusion()));
        items.registerSimpleBlockItem(blocks.registerBlock("termite_nest", p -> new AntNest(p, "termite"),
            p -> p.strength(0.6f).sound(SoundType.GRASS).randomTicks()));
    }
}
