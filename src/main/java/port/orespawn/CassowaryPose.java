package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class CassowaryPose extends CreaturePose {
    private final ModelPart tail, body, neck1, neck, head, beak, leg1, leg2, crest, foot1, foot2, gobbler;
    CassowaryPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.tail=root.getChild("tail");
        this.body=root.getChild("body");
        this.neck1=root.getChild("neck1");
        this.neck=root.getChild("neck");
        this.head=root.getChild("head");
        this.beak=root.getChild("beak");
        this.leg1=root.getChild("leg1");
        this.leg2=root.getChild("leg2");
        this.crest=root.getChild("crest");
        this.foot1=root.getChild("foot1");
        this.foot2=root.getChild("foot2");
        this.gobbler=root.getChild("gobbler");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      float newangle2 = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.15F * f1;
         newangle2 = cos(f2 * 2.6F * this.wingspeed) * (float) Math.PI * 0.1F * f1;
      } else {
         newangle2 = 0.0F;
         newangle = 0.0F;
      }
      this.leg1.xRot = this.foot2.xRot = newangle;
      this.leg2.xRot = this.foot1.xRot = -newangle;
      this.neck.xRot = -2.827F + newangle2;
      this.gobbler.xRot = newangle2;
      this.head.z = this.crest.z = this.beak.z = this.neck.z
         + sin(this.neck.xRot) * 7.0F;
      this.head.y = this.crest.y = this.beak.y = this.neck.y
         + cos(this.neck.xRot) * 7.0F;
    }
}
