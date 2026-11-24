package port.orespawn;

import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

final class RedAnt extends Creatures.Creature {
    static final ResourceKey<Level> MINING = ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath("orespawn","mining"));
    RedAnt(EntityType<? extends PathfinderMob> type, Level level) { super(type,level); }

    @Override protected void registerGoals() {
        goalSelector.addGoal(0,new PanicGoal(this,1.4));
        goalSelector.addGoal(1,new FloatGoal(this));
        goalSelector.addGoal(2,new MeleeAttackGoal(this,1,false));
        var wander=new WaterAvoidingRandomStrollGoal(this,1);
        wander.setInterval(10);
        goalSelector.addGoal(3,wander);
        targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this,Player.class,true,
            (candidate,world) -> world.getDifficulty()!=Difficulty.PEACEFUL));
    }
    @Override public boolean doHurtTarget(ServerLevel level, Entity entity) {
        return level.getDifficulty()!=Difficulty.PEACEFUL && getRandom().nextInt(15)==0 && super.doHurtTarget(level,entity);
    }
    @Override protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand!=InteractionHand.MAIN_HAND || !player.getItemInHand(hand).isEmpty()) return super.mobInteract(player,hand);
        if (!(player instanceof ServerPlayer traveler)) return InteractionResult.SUCCESS;
        var from=traveler.level();
        if (!from.dimension().equals(MINING) && !from.dimension().equals(Level.OVERWORLD)) return InteractionResult.PASS;
        var dest=from.getServer().getLevel(from.dimension().equals(MINING)?Level.OVERWORLD:MINING);
        if (dest==null) return explain(traveler,"dimension");
        var origin=dest.getWorldBorder().clampToBounds(traveler.blockPosition());
        var pos=landing(dest,origin);
        if (pos==null) return explain(traveler,"terrain");
        if (dest.dimension().equals(MINING) && dest.getEntitiesOfClass(RedAnt.class,new AABB(pos).inflate(6),Entity::isAlive).isEmpty()) {
            var ant=Creatures.SPECIES.get("red_ant").type().get().create(dest,EntitySpawnReason.TRIGGERED);
            if(ant==null)return explain(traveler,"travel");
            ant.snapTo(pos.getX()+.5,pos.getY(),pos.getZ()+.5,0,0);
            ant.setPersistenceRequired();
            if(!dest.addFreshEntity(ant))return explain(traveler,"travel");
        }
        if (!traveler.teleportTo(dest,pos.getX()+0.5,pos.getY(),pos.getZ()+0.5,Set.of(),traveler.getYRot(),traveler.getXRot(),true)) return explain(traveler,"travel");
        traveler.resetFallDistance();
        return InteractionResult.SUCCESS;
    }

    private static InteractionResult explain(ServerPlayer player,String problem) {
        player.sendSystemMessage(Component.translatable("message.orespawn.ant."+problem));
        return InteractionResult.FAIL;
    }
    private static BlockPos landing(ServerLevel level,BlockPos origin) {
        BlockPos water=null;
        for(int r=0;r<=16;r++) for(int x=-r;x<=r;x++) for(int z=-r;z<=r;z++) {
            if(Math.max(Math.abs(x),Math.abs(z))!=r) continue;
            var column=origin.offset(x,0,z);
            if (!level.getWorldBorder().isWithinBounds(column)) continue;
            var chunk=level.getChunkAt(column);
            int y=chunk.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,column.getX()&15,column.getZ()&15)+1;
            var feet=new BlockPos(column.getX(),y,column.getZ());
            if (y<=level.getMinY() || y+1>level.getMaxY() || !level.isEmptyBlock(feet) || !level.isEmptyBlock(feet.above())) continue;
            var floor=feet.below();
            var state=level.getBlockState(floor);
            if(state.isFaceSturdy(level,floor,Direction.UP) && state.getFluidState().isEmpty() && !state.is(Blocks.MAGMA_BLOCK) && !state.is(Blocks.CAMPFIRE) && !state.is(Blocks.CACTUS)) return feet;
            if(water==null && state.is(Blocks.WATER) && level.isEmptyBlock(feet.above(2))) water=feet;
        }
        if(water!=null && level.setBlockAndUpdate(water,Blocks.COBBLESTONE.defaultBlockState())) return water.above();
        return null;
    }
}
