package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;
final class HerculesBeetlePose extends CreaturePose {
    private final ModelPart body1, body2, head1, head2, head3, head4, head5, head6, head8, jaw1, jaw2, jaw3, jaw4, head7, lfleg1, lfleg2, lfleg3, lmleg1, lmleg2, lmleg3, lrleg1, lrleg2, lrleg3, jaw5, jaw6, jaw7, jaw8, rfleg1, rfleg2, rfleg3, rmleg1, rmleg2, rmleg3, rrleg1, rrleg2, rrleg3, jaw9;
    HerculesBeetlePose(ModelPart root,float speed,float scale){super(root,speed,scale);
        body1=root.getChild("body1");
        body2=root.getChild("body2");
        head1=root.getChild("head1");
        head2=root.getChild("head2");
        head3=root.getChild("head3");
        head4=root.getChild("head4");
        head5=root.getChild("head5");
        head6=root.getChild("head6");
        head8=root.getChild("head8");
        jaw1=root.getChild("jaw1");
        jaw2=root.getChild("jaw2");
        jaw3=root.getChild("jaw3");
        jaw4=root.getChild("jaw4");
        head7=root.getChild("head7");
        lfleg1=root.getChild("lfleg1");
        lfleg2=root.getChild("lfleg2");
        lfleg3=root.getChild("lfleg3");
        lmleg1=root.getChild("lmleg1");
        lmleg2=root.getChild("lmleg2");
        lmleg3=root.getChild("lmleg3");
        lrleg1=root.getChild("lrleg1");
        lrleg2=root.getChild("lrleg2");
        lrleg3=root.getChild("lrleg3");
        jaw5=root.getChild("jaw5");
        jaw6=root.getChild("jaw6");
        jaw7=root.getChild("jaw7");
        jaw8=root.getChild("jaw8");
        rfleg1=root.getChild("rfleg1");
        rfleg2=root.getChild("rfleg2");
        rfleg3=root.getChild("rfleg3");
        rmleg1=root.getChild("rmleg1");
        rmleg2=root.getChild("rmleg2");
        rmleg3=root.getChild("rmleg3");
        rrleg1=root.getChild("rrleg1");
        rrleg2=root.getChild("rrleg2");
        rrleg3=root.getChild("rrleg3");
        jaw9=root.getChild("jaw9");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4){
float newangle;

      newangle = cos(f2 * this.wingspeed * 0.45F) * (float) Math.PI * 0.12F * f1;
      this.lfleg1.yRot = 0.349F + newangle;
      this.lfleg3.yRot = this.lfleg2.yRot = this.lfleg1.yRot;
      this.lmleg1.yRot = -newangle;
      this.lmleg3.yRot = this.lmleg2.yRot = this.lmleg1.yRot;
      this.lrleg1.yRot = -0.349F + newangle;
      this.lrleg3.yRot = this.lrleg2.yRot = this.lrleg1.yRot;
      this.rfleg1.yRot = -0.349F + newangle;
      this.rfleg3.yRot = this.rfleg2.yRot = this.rfleg1.yRot;
      this.rmleg1.yRot = -newangle;
      this.rmleg3.yRot = this.rmleg2.yRot = this.rmleg1.yRot;
      this.rrleg1.yRot = 0.349F + newangle;
      this.rrleg3.yRot = this.rrleg2.yRot = this.rrleg1.yRot;
      if (!state.attacking) {
         newangle = cos(f2 * 0.051F * this.wingspeed) * (float) Math.PI * 0.01F;
      } else {
         newangle = cos(f2 * 0.51F * this.wingspeed) * (float) Math.PI * 0.07F;
      }

      this.jaw1.xRot = 0.122F + newangle;
      this.jaw2.xRot = 0.122F + newangle;
      this.jaw3.xRot = 0.0F + newangle;
      this.jaw4.xRot = 0.0F + newangle;
      this.jaw5.xRot = 0.122F + newangle;
      this.jaw6.xRot = 0.122F + newangle;
      this.jaw7.xRot = 0.0F + newangle;
      this.jaw8.xRot = 0.0F + newangle;
      this.jaw9.xRot = 0.314F + newangle;
    }

}
