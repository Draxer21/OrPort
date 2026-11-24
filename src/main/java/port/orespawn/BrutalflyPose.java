package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class BrutalflyPose extends CreaturePose {
    private final ModelPart body, leftwing, rightwing, leftwing2, rightwing2, leftwing3, rightwing3, head, leftwing4, rightwing4, leftwing5, leftwing6, rightwing5, rightwing6;
    BrutalflyPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.body=root.getChild("body");
        this.leftwing=root.getChild("leftwing");
        this.rightwing=root.getChild("rightwing");
        this.leftwing2=root.getChild("leftwing2");
        this.rightwing2=root.getChild("rightwing2");
        this.leftwing3=root.getChild("leftwing3");
        this.rightwing3=root.getChild("rightwing3");
        this.head=root.getChild("head");
        this.leftwing4=root.getChild("leftwing4");
        this.rightwing4=root.getChild("rightwing4");
        this.leftwing5=root.getChild("leftwing5");
        this.leftwing6=root.getChild("leftwing6");
        this.rightwing5=root.getChild("rightwing5");
        this.rightwing6=root.getChild("rightwing6");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      this.rightwing.zRot = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.25F;
      this.rightwing2.zRot = this.rightwing.zRot;
      this.rightwing3.zRot = this.rightwing.zRot;
      this.rightwing4.zRot = this.rightwing.zRot;
      this.rightwing5.zRot = this.rightwing.zRot;
      this.rightwing6.zRot = this.rightwing.zRot;
      this.leftwing.zRot = -this.rightwing.zRot;
      this.leftwing2.zRot = -this.rightwing.zRot;
      this.leftwing3.zRot = -this.rightwing.zRot;
      this.leftwing4.zRot = -this.rightwing.zRot;
      this.leftwing5.zRot = -this.rightwing.zRot;
      this.leftwing6.zRot = -this.rightwing.zRot;
    }
}
