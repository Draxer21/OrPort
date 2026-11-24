package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;
final class CowPose extends CreaturePose {
    private final ModelPart root;
    CowPose(ModelPart root){super(root,1,1);this.root=root;}
    @Override void animate(CreatureFrame state,float walk,float speed,float age,float yaw,float pitch){
        var h=root.getChild("head");h.xRot=(float)Math.toRadians(pitch);h.yRot=(float)Math.toRadians(yaw);
        float step=cos(walk*.6662f)*1.4f*speed;
        root.getChild("left_front_leg").xRot=root.getChild("right_hind_leg").xRot=step;
        root.getChild("right_front_leg").xRot=root.getChild("left_hind_leg").xRot=-step;
    }
}
