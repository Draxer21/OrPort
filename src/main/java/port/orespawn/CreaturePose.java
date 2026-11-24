package port.orespawn;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
abstract class CreaturePose extends EntityModel<LivingEntityRenderState> {
    final float wingspeed,scale;
    CreaturePose(ModelPart root,float wingspeed,float scale){super(root);this.wingspeed=wingspeed;this.scale=scale;}
    static float cos(float x){return (float)Math.cos(x);}
    static float sin(float x){return (float)Math.sin(x);}
    @Override public final void setupAnim(LivingEntityRenderState raw){
        super.setupAnim(raw);
        var state=(CreatureFrame)raw;
        state.random.setSeed(state.individual ^ (long)(state.ageInTicks/8));
        animate(state,state.walkAnimationPos,state.walkAnimationSpeed,state.ageInTicks,state.yRot,state.xRot);
    }
    abstract void animate(CreatureFrame state,float walk,float amplitude,float age,float yaw,float pitch);
}
