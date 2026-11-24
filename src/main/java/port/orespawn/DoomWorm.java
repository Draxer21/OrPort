package port.orespawn;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

final class DoomWorm extends Creatures.Creature {
    private final Vec3[] trail=new Vec3[100];
    private int newest;
    private float desiredYaw,turn,cycle;
    private double lift;
    DoomWorm(EntityType<? extends PathfinderMob> type,Level level){
        super(type,level);noPhysics=true;setNoGravity(true);cycle=random.nextFloat()*360;
    }
    @Override protected void registerGoals(){}
    @Override public void aiStep(){
        super.aiStep();
        if(level().isClientSide()){
            if(trail[newest]==null)java.util.Arrays.fill(trail,position());
            newest=(newest+1)%trail.length;trail[newest]=position();return;
        }
        if(random.nextInt(100)==0)desiredYaw=random.nextFloat()*360;
        turn=turn*.95f+Mth.wrapDegrees(desiredYaw-getYRot())*.05f;
        setYRot(getYRot()+turn);yBodyRot=getYRot();setYHeadRot(getYRot());
        BlockPos center=blockPosition();
        int ground=6;
        while(ground>=-6 && level().isEmptyBlock(center.above(ground)))ground--;
        cycle=(cycle+.5f)%360;
        lift=Math.clamp(lift*.98+.008*(ground-Math.sin(Math.toRadians(cycle*10))*6),-.5,.5);
        if(getY()<level().getMinY()+3)lift=.3;
        double yaw=Math.toRadians(getYRot());
        setDeltaMovement(-Math.sin(yaw)*.3,lift,Math.cos(yaw)*.3);
        setXRot((float)Math.toDegrees(Math.atan2(-lift,.3)));
    }
    Vec3 segment(int back){Vec3 sample=trail[Math.floorMod(newest-back,trail.length)];return sample==null?position():sample;}
    @Override public boolean hurtServer(ServerLevel level,DamageSource source,float amount){
        // The vanilla attribute tops out at 1024. Keep normal damage proportional to a 3000-point life bar.
        float scaled=source.is(DamageTypes.GENERIC_KILL)?amount:amount*(getMaxHealth()/3000f);
        return super.hurtServer(level,source,scaled);
    }
}
