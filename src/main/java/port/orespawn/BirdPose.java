package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class BirdPose extends CreaturePose {
    private final ModelPart Body, Head, Beak, LowerBeak, feather2, feather1, feather3, tailfeather1, rwing1, lwing1, leg, otherleg, lwing2, rwing2, tailfeather2, tailfeather3;
    BirdPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Body=root.getChild("Body");
        this.Head=root.getChild("Head");
        this.Beak=root.getChild("Beak");
        this.LowerBeak=root.getChild("LowerBeak");
        this.feather2=root.getChild("feather2");
        this.feather1=root.getChild("feather1");
        this.feather3=root.getChild("feather3");
        this.tailfeather1=root.getChild("tailfeather1");
        this.rwing1=root.getChild("rwing1");
        this.lwing1=root.getChild("lwing1");
        this.leg=root.getChild("leg");
        this.otherleg=root.getChild("otherleg");
        this.lwing2=root.getChild("lwing2");
        this.rwing2=root.getChild("rwing2");
        this.tailfeather2=root.getChild("tailfeather2");
        this.tailfeather3=root.getChild("tailfeather3");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      newangle = cos(f2 * 1.5F * this.wingspeed) * (float) Math.PI * 0.35F;
      this.lwing1.zRot = -1.5F + newangle;
      this.lwing2.zRot = newangle;
      this.rwing1.zRot = 1.5F - newangle;
      this.rwing2.zRot = -newangle;
      newangle = cos(f2 * 0.3F * this.wingspeed) * (float) Math.PI * 0.1F;
      this.tailfeather1.xRot = newangle;
      this.tailfeather2.xRot = newangle;
      this.tailfeather3.xRot = newangle;
      newangle = cos(f2 * 1.1F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.feather1.zRot = newangle;
      newangle = cos(f2 * 1.2F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.feather2.zRot = newangle;
      newangle = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.feather3.zRot = newangle;
    }
}
