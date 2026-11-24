package port.orespawn;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

final class AntModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart[] forward,reverse;
    private final ModelPart leftJaw,rightJaw;
    AntModel(ModelPart root) {
        super(root);
        forward=parts(root,"llegtop1","llegbot1","rlegtop2","rlegbot2","rlegtop3","rlegbot3");
        reverse=parts(root,"rlegtop1","rlegbot1","llegtop2","llegbot2","llegtop3","llegbot3");
        leftJaw=root.getChild("jawsl");
        rightJaw=root.getChild("jawsr");
    }
    private static ModelPart[] parts(ModelPart root,String... names) {
        return java.util.Arrays.stream(names).map(root::getChild).toArray(ModelPart[]::new);
    }
    @Override public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float step=(float)(Math.cos(state.ageInTicks*2.7)*Math.PI*0.45)*state.walkAnimationSpeed;
        for(var p:forward) p.xRot=step;
        for(var p:reverse) p.xRot=-step;
        leftJaw.yRot=(float)(Math.cos(state.ageInTicks*0.4)*Math.PI*0.05);
        rightJaw.yRot=-leftJaw.yRot;
    }
}
