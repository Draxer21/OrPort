package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class CamarasaurusPose extends CreaturePose {
    private final ModelPart Body1, Body2, Body3, Body4, Tail0, Neck1, Neck2, Neck3, Head1, Head2, Tail1, Tail2, Tail3, BLegupleft, FLegupleft, BLegupright, FLegupright, BLegdownright, FLegdownleft, FLegdownright, BLegdownleft;
    CamarasaurusPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Body1=root.getChild("Body1");
        this.Body2=root.getChild("Body2");
        this.Body3=root.getChild("Body3");
        this.Body4=root.getChild("Body4");
        this.Tail0=root.getChild("Tail0");
        this.Neck1=root.getChild("Neck1");
        this.Neck2=root.getChild("Neck2");
        this.Neck3=root.getChild("Neck3");
        this.Head1=root.getChild("Head1");
        this.Head2=root.getChild("Head2");
        this.Tail1=root.getChild("Tail1");
        this.Tail2=root.getChild("Tail2");
        this.Tail3=root.getChild("Tail3");
        this.BLegupleft=root.getChild("BLegupleft");
        this.FLegupleft=root.getChild("FLegupleft");
        this.BLegupright=root.getChild("BLegupright");
        this.FLegupright=root.getChild("FLegupright");
        this.BLegdownright=root.getChild("BLegdownright");
        this.FLegdownleft=root.getChild("FLegdownleft");
        this.FLegdownright=root.getChild("FLegdownright");
        this.BLegdownleft=root.getChild("BLegdownleft");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float hf = 0.0F;
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.25F * f1;
      } else {
         newangle = 0.0F;
      }
      this.FLegupleft.xRot = newangle;
      this.FLegdownleft.xRot = newangle;
      this.FLegupright.xRot = -newangle;
      this.FLegdownright.xRot = -newangle;
      this.BLegupleft.xRot = -0.15F - newangle;
      this.BLegdownleft.xRot = -newangle;
      this.BLegupright.xRot = -0.15F + newangle;
      this.BLegdownright.xRot = newangle;
      hf = state.healthFraction;
      newangle = cos(f2 * 0.8F * this.wingspeed * hf) * (float) Math.PI * 0.25F * hf;
      if (state.sitting) {
         newangle = 0.0F;
      }
      this.Tail0.yRot = newangle * 0.25F;
      this.Tail1.z = this.Tail0.z + (float)Math.cos(this.Tail0.yRot) * 5.0F;
      this.Tail1.x = this.Tail0.x + (float)Math.sin(this.Tail0.yRot) * 5.0F;
      this.Tail1.yRot = newangle * 0.5F;
      this.Tail2.z = this.Tail1.z + (float)Math.cos(this.Tail1.yRot) * 8.0F;
      this.Tail2.x = this.Tail1.x + (float)Math.sin(this.Tail1.yRot) * 8.0F;
      this.Tail2.yRot = newangle * 0.75F;
      this.Tail3.z = this.Tail2.z + (float)Math.cos(this.Tail2.yRot) * 7.0F;
      this.Tail3.x = this.Tail2.x + (float)Math.sin(this.Tail2.yRot) * 7.0F;
      this.Tail3.yRot = newangle * 1.0F;
      this.Neck1.yRot = (float)Math.toRadians(f3) * 0.125F;
      this.Neck2.z = this.Neck1.z;
      this.Neck2.x = this.Neck1.x;
      this.Neck2.yRot = (float)Math.toRadians(f3) * 0.25F;
      this.Neck3.z = this.Neck2.z - (float)Math.cos(this.Neck2.yRot) * 6.0F;
      this.Neck3.x = this.Neck2.x - (float)Math.sin(this.Neck2.yRot) * 6.0F;
      this.Neck3.yRot = (float)Math.toRadians(f3) * 0.38F;
      this.Head1.z = this.Neck3.z - (float)Math.cos(this.Neck3.yRot) * 7.0F;
      this.Head1.x = this.Neck3.x - (float)Math.sin(this.Neck3.yRot) * 7.0F;
      this.Head1.yRot = (float)Math.toRadians(f3);
      this.Head2.z = this.Head1.z - (float)Math.cos(this.Head1.yRot) * 5.0F;
      this.Head2.x = this.Head1.x - (float)Math.sin(this.Head1.yRot) * 5.0F;
      this.Head2.yRot = (float)Math.toRadians(f3);
    }
}
