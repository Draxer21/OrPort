package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class BeaverPose extends CreaturePose {
    private final ModelPart head, nose, teeth, body, tail, rff, lff, rrf, lrf;
    BeaverPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.head=root.getChild("head");
        this.nose=root.getChild("nose");
        this.teeth=root.getChild("teeth");
        this.body=root.getChild("body");
        this.tail=root.getChild("tail");
        this.rff=root.getChild("rff");
        this.lff=root.getChild("lff");
        this.rrf=root.getChild("rrf");
        this.lrf=root.getChild("lrf");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      newangle = cos(f2 * 3.7F * this.wingspeed) * (float) Math.PI * 0.45F * f1;
      this.rff.xRot = this.lrf.xRot = newangle;
      this.lff.xRot = this.rrf.xRot = -newangle;
      newangle = cos(f2 * 2.7F * this.wingspeed) * (float) Math.PI * 0.25F;
      this.teeth.xRot = newangle;
      newangle = cos(f2 * 0.5F * this.wingspeed) * (float) Math.PI * 0.05F;
      this.tail.xRot = newangle;
    }
}
