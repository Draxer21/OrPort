package port.orespawn;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;

final class WoodForagingGoal extends MoveToBlockGoal {
    private final Creatures.Creature animal;
    private final boolean termite;
    WoodForagingGoal(Creatures.Creature animal){super(animal,1,7,4);this.animal=animal;termite=animal.species().equals("termite");}
    @Override public boolean canUse(){return animal.level() instanceof ServerLevel level && level.getGameRules().get(GameRules.MOB_GRIEFING) && super.canUse();}
    @Override protected boolean isValidTarget(LevelReader level,BlockPos pos){
        var block=level.getBlockState(pos);
        return block.is(BlockTags.LOGS) || (termite && block.is(BlockTags.PLANKS));
    }
    @Override public void tick(){
        super.tick();
        if(!(animal.level() instanceof ServerLevel level) || !isReachedTarget() || !isValidTarget(level,blockPos)
            || !level.getGameRules().get(GameRules.MOB_GRIEFING) || !level.mayInteract(animal,blockPos))return;
        if(termite && animal.getRandom().nextInt(3)!=0)level.setBlockAndUpdate(blockPos,Blocks.DIRT.defaultBlockState());
        else level.destroyBlock(blockPos,!termite,animal);
        animal.heal(1);
        if(termite && level.getEntitiesOfClass(Creatures.Creature.class,animal.getBoundingBox().inflate(3),other->other.getType()==animal.getType()).size()<10)
            Creatures.SPECIES.get("termite").type().get().spawn(level,animal.blockPosition(),EntitySpawnReason.BREEDING);
    }
}
