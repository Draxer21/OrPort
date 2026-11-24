package port.orespawn;

import java.util.EnumSet;
import java.util.UUID;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.*;

final class Companion extends BreedingCreature {
    private static final EntityDataAccessor<Boolean> RESTING = SynchedEntityData.defineId(Companion.class,EntityDataSerializers.BOOLEAN);
    private UUID owner;
    Companion(EntityType<? extends PathfinderMob> type,Level level) {super(type,level);}
    static boolean supports(String name) {return switch(name){case "camarasaurus","velocityraptor","gammametroid","spyro" -> true;default -> false;};}
    private Item food(){return switch(species()){case "gammametroid" -> Items.IRON_INGOT;case "spyro" -> Items.BEEF;default -> Items.APPLE;};}
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder){super.defineSynchedData(builder);builder.define(RESTING,false);}
    @Override public boolean isResting(){return entityData.get(RESTING);}
    @Override protected InteractionResult mobInteract(Player player,InteractionHand hand){
        ItemStack stack=player.getItemInHand(hand);
        if(player.getUUID().equals(owner) && stack.is(food()) && getHealth()<getMaxHealth()){
            if(!level().isClientSide()){stack.consume(1,player);heal(getMaxHealth());}
            return InteractionResult.SUCCESS;
        }
        if(owner==null && stack.is(food())){
            if(level() instanceof ServerLevel server){
                stack.consume(1,player);
                boolean success=random.nextInt(species().equals("gammametroid")?3:2)==0;
                if(success){owner=player.getUUID();entityData.set(RESTING,true);setTarget(null);getNavigation().stop();setPersistenceRequired();}
                server.sendParticles(success?ParticleTypes.HEART:ParticleTypes.SMOKE,getX(),getEyeY(),getZ(),7,.25,.15,.25,.02);
            }
            return InteractionResult.SUCCESS;
        }
        if(stack.isEmpty() && player.getUUID().equals(owner)){
            if(!level().isClientSide()){entityData.set(RESTING,!isResting());getNavigation().stop();setTarget(null);}
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player,hand);
    }
    @Override public boolean canAttack(LivingEntity other){
        if(owner!=null && (other instanceof Player || other instanceof Companion c && owner.equals(c.owner))) return false;
        return super.canAttack(other);
    }
    @Override protected void registerGoals(){
        super.registerGoals();
        goalSelector.addGoal(1,new Goal(){
            {setFlags(EnumSet.of(Flag.MOVE,Flag.LOOK,Flag.JUMP));}
            public boolean canUse(){return isResting();}
            public void start(){getNavigation().stop();}
        });
        goalSelector.addGoal(2,new Goal(){
            Player leader;
            {setFlags(EnumSet.of(Flag.MOVE));}
            public boolean canUse(){leader=owner==null?null:level().getPlayerByUUID(owner);return leader!=null && !isResting() && distanceToSqr(leader)>100;}
            public boolean canContinueToUse(){return leader!=null && leader.isAlive() && leader.level()==level() && !isResting() && distanceToSqr(leader)>4;}
            public void tick(){if(tickCount%5==0)getNavigation().moveTo(leader,1.4);}
            public void stop(){leader=null;getNavigation().stop();}
        });
        goalSelector.addGoal(4,new TemptGoal(this,1.15,Ingredient.of(food()),false));
    }
    @Override protected void addAdditionalSaveData(ValueOutput out){super.addAdditionalSaveData(out);if(owner!=null)out.putString("PetOwner",owner.toString());out.putBoolean("Waiting",isResting());}
    private void restore(String id,boolean sitting){
        try{owner=id.isEmpty()?null:UUID.fromString(id);}catch(IllegalArgumentException ignored){owner=null;}
        entityData.set(RESTING,owner!=null && sitting);
        if(owner!=null)setPersistenceRequired();
    }
    @Override protected void readAdditionalSaveData(ValueInput in){super.readAdditionalSaveData(in);restore(in.getStringOr("PetOwner",""),in.getBooleanOr("Waiting",false));}
    void pack(ItemStack cage){
        if(owner!=null)CustomData.update(DataComponents.CUSTOM_DATA,cage,tag->{tag.putString("PetOwner",owner.toString());tag.putBoolean("Waiting",isResting());tag.putFloat("PetHealth",getHealth());});
    }
    void unpack(ItemStack cage){
        var data=cage.getOrDefault(DataComponents.CUSTOM_DATA,CustomData.EMPTY).copyTag();
        restore(data.getStringOr("PetOwner",""),data.getBooleanOr("Waiting",false));
        if(owner!=null)setHealth(Math.clamp(data.getFloatOr("PetHealth",getMaxHealth()),1,getMaxHealth()));
    }
    @Override protected void dropCustomDeathLoot(ServerLevel server,net.minecraft.world.damagesource.DamageSource cause,boolean recent){
        super.dropCustomDeathLoot(server,cause,recent);
        if(owner==null || species().equals("gammametroid"))return;
        boolean dragon=species().equals("spyro");
        spawnAtLocation(server,new ItemStack(dragon?Items.BEEF:Items.POPPY,dragon?random.nextInt(4)+1:random.nextInt(5)+2));
    }
}
