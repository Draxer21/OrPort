package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class VelocityRaptorPose extends CreaturePose {
    private final ModelPart hf3, hf4, hf2, hf1, lff2, lff1, lff3, rff2, rff3, rff1, tf4, tf1, Shape1, neck, head1, lf1, lf2, head2, tail1, tail2, bl1, br1, bl2, br2, bl3, br3, rf1, rf2, tf2, tf3, bl4, br4, Hat1, Hat2;
    VelocityRaptorPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.hf3=root.getChild("hf3");
        this.hf4=root.getChild("hf4");
        this.hf2=root.getChild("hf2");
        this.hf1=root.getChild("hf1");
        this.lff2=root.getChild("lff2");
        this.lff1=root.getChild("lff1");
        this.lff3=root.getChild("lff3");
        this.rff2=root.getChild("rff2");
        this.rff3=root.getChild("rff3");
        this.rff1=root.getChild("rff1");
        this.tf4=root.getChild("tf4");
        this.tf1=root.getChild("tf1");
        this.Shape1=root.getChild("Shape1");
        this.neck=root.getChild("neck");
        this.head1=root.getChild("head1");
        this.lf1=root.getChild("lf1");
        this.lf2=root.getChild("lf2");
        this.head2=root.getChild("head2");
        this.tail1=root.getChild("tail1");
        this.tail2=root.getChild("tail2");
        this.bl1=root.getChild("bl1");
        this.br1=root.getChild("br1");
        this.bl2=root.getChild("bl2");
        this.br2=root.getChild("br2");
        this.bl3=root.getChild("bl3");
        this.br3=root.getChild("br3");
        this.rf1=root.getChild("rf1");
        this.rf2=root.getChild("rf2");
        this.tf2=root.getChild("tf2");
        this.tf3=root.getChild("tf3");
        this.bl4=root.getChild("bl4");
        this.br4=root.getChild("br4");
        this.Hat1=root.getChild("Hat1");
        this.Hat2=root.getChild("Hat2");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float hf = 0.0F;
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.25F * f1;
      } else {
         newangle = 0.0F;
      }
      this.bl1.xRot = newangle;
      this.bl2.xRot = newangle + 0.488F;
      this.bl3.xRot = newangle;
      this.bl4.xRot = newangle + 0.628F;
      this.br1.xRot = -newangle;
      this.br2.xRot = -newangle + 0.488F;
      this.br3.xRot = -newangle;
      this.br4.xRot = -newangle + 0.628F;
      hf = state.healthFraction;
      newangle = cos(f2 * 1.25F * this.wingspeed * hf) * (float) Math.PI * 0.1F * hf;
      this.hf1.yRot = newangle;
      this.hf2.yRot = -newangle;
      this.hf3.yRot = newangle;
      this.hf4.yRot = -newangle;
      newangle = cos(f2 * 0.3F) * (float) Math.PI * 0.05F;
      this.lf1.xRot = newangle + 0.279F;
      this.lf2.xRot = newangle - 0.436F;
      this.lff1.xRot = newangle - 0.279F;
      this.lff2.xRot = newangle - 0.453F;
      this.lff3.xRot = newangle - 1.047F;
      this.rf1.xRot = -newangle + 0.279F;
      this.rf2.xRot = -newangle - 0.436F;
      this.rff1.xRot = -newangle - 0.279F;
      this.rff2.xRot = -newangle - 0.453F;
      this.rff3.xRot = -newangle - 1.047F;
      newangle = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.1F;
      this.lff1.yRot = newangle;
      this.lff2.yRot = -newangle;
      this.lff3.yRot = newangle;
      this.rff1.yRot = -newangle;
      this.rff2.yRot = newangle;
      this.rff3.yRot = -newangle;
      if (state.sitting) {
         newangle = 0.0F;
      } else {
         newangle = cos(f2 * 1.4F * this.wingspeed * hf) * (float) Math.PI * 0.25F * hf;
      }
      this.tf1.zRot = newangle;
      this.tf2.zRot = -newangle;
      this.tf3.zRot = newangle;
      this.tf4.zRot = -newangle;
    }
}
