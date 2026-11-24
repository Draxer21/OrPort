package port.orespawn;

import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;

final class Triffid extends Creatures.Creature {
    private static final EntityDataAccessor<Boolean> OPEN=SynchedEntityData.defineId(Triffid.class,EntityDataSerializers.BOOLEAN);
    private int recovery;
    Triffid(EntityType<? extends PathfinderMob> type,Level level){super(type,level);}
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder){super.defineSynchedData(builder);builder.define(OPEN,false);}
    @Override public boolean isResting(){return !entityData.get(OPEN);}
    @Override protected void registerGoals(){targetSelector.addGoal(1,new NearestAttackableTargetGoal<>(this,Player.class,true));}
    @Override protected void customServerAiStep(ServerLevel level){
        super.customServerAiStep(level);
        if(random.nextInt(250)==0)heal(1);
        if(recovery>0){recovery--;return;}
        if(random.nextInt(80)==0)entityData.set(OPEN,random.nextInt(8)==0);
        if(tickCount%10!=0)return;
        var target=getTarget();
        boolean visible=target!=null && target.isAlive() && hasLineOfSight(target);
        setAggressive(visible && distanceToSqr(target)<25);
        if(visible){entityData.set(OPEN,true);getLookControl().setLookAt(target);if(isAggressive())doHurtTarget(level,target);}
    }
    @Override public boolean hurtServer(ServerLevel level,DamageSource source,float amount){
        if(source.is(DamageTypes.GENERIC_KILL)||source.is(DamageTypes.FELL_OUT_OF_WORLD))return super.hurtServer(level,source,amount);
        boolean vulnerable=entityData.get(OPEN) && recovery==0;
        recovery=300;entityData.set(OPEN,false);setAggressive(false);
        return vulnerable && super.hurtServer(level,source,amount);
    }
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);out.putInt("Recovery",recovery);out.putBoolean("BloomOpen",entityData.get(OPEN));}
    @Override protected void readAdditionalSaveData(ValueInput in){super.readAdditionalSaveData(in);recovery=in.getIntOr("Recovery",0);entityData.set(OPEN,recovery==0 && in.getBooleanOr("BloomOpen",false));}
}
