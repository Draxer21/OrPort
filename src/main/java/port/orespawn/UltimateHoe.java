package port.orespawn;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.*;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;

final class UltimateHoe extends HoeItem {
    UltimateHoe(ToolMaterial mat,float damage,Properties properties){super(mat,damage,-1,properties);}
    private static boolean soil(Level level,BlockPos pos){var state=level.getBlockState(pos);return state.is(Blocks.DIRT)||state.is(Blocks.GRASS_BLOCK);}
    @Override public InteractionResult useOn(UseOnContext ctx){
        var level=ctx.getLevel();var center=ctx.getClickedPos();var player=ctx.getPlayer();
        if(ctx.getClickedFace()==Direction.DOWN || !soil(level,center) || !level.isEmptyBlock(center.above()))return super.useOn(ctx);
        if(player!=null && !level.mayInteract(player,center))return InteractionResult.FAIL;
        if(!level.isClientSide()){
            var moist=Blocks.FARMLAND.defaultBlockState().setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.MOISTURE,7);
            for(int x=-1;x<=1;x++)for(int z=-1;z<=1;z++){
                var pos=center.offset(x,0,z);
                if(level.isEmptyBlock(pos))pos=pos.below();
                else if(soil(level,pos) && soil(level,pos.above()))pos=pos.above();
                if(soil(level,pos) && level.isEmptyBlock(pos.above()) && (player==null || (level.mayInteract(player,pos) && player.mayUseItemAt(pos,Direction.UP,ctx.getItemInHand()))))
                    level.setBlockAndUpdate(pos,moist);
            }
            level.playSound(null,center,SoundEvents.HOE_TILL,SoundSource.BLOCKS,1,1);
        }
        return InteractionResult.SUCCESS;
    }
}
