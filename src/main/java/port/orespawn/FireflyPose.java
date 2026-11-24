package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class FireflyPose extends CreaturePose {
    private final ModelPart body, wing_left, wing_right, head, mouth, eye_left, eye_right, front_leg_left_, front_leg_right, back_leg_left, back_leg_right, TailLight;
    FireflyPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.body=root.getChild("body");
        this.wing_left=root.getChild("wing_left");
        this.wing_right=root.getChild("wing_right");
        this.head=root.getChild("head");
        this.mouth=root.getChild("mouth");
        this.eye_left=root.getChild("eye_left");
        this.eye_right=root.getChild("eye_right");
        this.front_leg_left_=root.getChild("front_leg_left_");
        this.front_leg_right=root.getChild("front_leg_right");
        this.back_leg_left=root.getChild("back_leg_left");
        this.back_leg_right=root.getChild("back_leg_right");
        this.TailLight=root.getChild("TailLight");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      this.wing_left.zRot = 1.11F + cos(f2 * this.wingspeed) * (float) Math.PI * 0.35F;
      this.wing_right.zRot = -1.11F - cos(f2 * this.wingspeed) * (float) Math.PI * 0.35F;
    }
}
