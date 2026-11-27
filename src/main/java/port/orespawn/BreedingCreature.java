package port.orespawn;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;

class BreedingCreature extends Creatures.Creature {
    private static final EntityDataAccessor<Boolean> BABY=SynchedEntityData.defineId(BreedingCreature.class,EntityDataSerializers.BOOLEAN);
    private int age,love;
    BreedingCreature(EntityType<? extends PathfinderMob> type,Level level){super(type,level);}
    private Item breedingFood(){return switch(species()){
        case "redcow","crystal_cow" -> Items.WHEAT; case "baryonyx","camarasaurus" -> Items.GOLDEN_APPLE;
        case "velocityraptor" -> Items.APPLE;
        case "gammametroid" -> Items.IRON_INGOT;
        default -> Items.AIR;
    };}
    @Override protected void defineSynchedData(SynchedEntityData.Builder b){super.defineSynchedData(b);b.define(BABY,false);}
    @Override public boolean isBaby(){return entityData.get(BABY);}
    @Override public void setBaby(boolean baby){age=baby?-24000:0;entityData.set(BABY,baby);}
    @Override protected void registerGoals(){super.registerGoals();if(!Companion.supports(species()))goalSelector.addGoal(3,new TemptGoal(this,1.15,Ingredient.of(breedingFood()),false));}
    @Override protected InteractionResult mobInteract(Player player,InteractionHand hand){
        var stack=player.getItemInHand(hand);
        if((species().equals("redcow") || species().equals("crystal_cow")) && !isBaby() && stack.is(Items.BUCKET)){
            if(!level().isClientSide()){player.setItemInHand(hand,ItemUtils.createFilledResult(stack,player,new ItemStack(Items.MILK_BUCKET)));playSound(net.minecraft.sounds.SoundEvents.COW_MILK,1,1);}
            return InteractionResult.SUCCESS;
        }
        if(!stack.isEmpty() && stack.is(breedingFood()) && (age<0 || (age==0 && love==0))){
            if(level() instanceof ServerLevel server){
                stack.consume(1,player);
                if(age<0){age=Math.min(0,age+Math.max(1,-age/10));entityData.set(BABY,age<0);}
                else{love=600;server.sendParticles(ParticleTypes.HEART,getX(),getEyeY(),getZ(),5,.2,.2,.2,.02);}
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player,hand);
    }
    @Override public void aiStep(){
        super.aiStep();
        if(!(level() instanceof ServerLevel server))return;
        if(age<0 && ++age==0)entityData.set(BABY,false);
        else if(age>0)age--;
        if(love==0 || --love%20!=0 || isResting())return;
        var mate=server.getEntitiesOfClass(BreedingCreature.class,getBoundingBox().inflate(8),e->e!=this && e.getType()==getType() && e.love>0 && !e.isResting()).stream().min(java.util.Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
        if(mate==null)return;
        if(distanceToSqr(mate)>9){getNavigation().moveTo(mate,1);return;}
        var baby=(BreedingCreature)getType().create(server,EntitySpawnReason.BREEDING);
        if(baby==null)return;
        baby.setBaby(true);baby.snapTo(position());baby.setPersistenceRequired();
        if(server.addFreshEntity(baby)){age=mate.age=6000;love=mate.love=0;server.sendParticles(ParticleTypes.HEART,getX(),getEyeY(),getZ(),7,.3,.3,.3,.02);}
    }
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);out.putInt("Age",age);out.putInt("InLove",love);}
    @Override protected void readAdditionalSaveData(ValueInput in){super.readAdditionalSaveData(in);age=in.getIntOr("Age",0);love=in.getIntOr("InLove",0);entityData.set(BABY,age<0);}
}

