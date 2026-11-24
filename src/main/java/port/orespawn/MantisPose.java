package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class MantisPose extends CreaturePose {
    private final ModelPart lfleg1, lfleg2, lfleg3, lfleg4, lrleg1, lrleg2, lrleg3, lrleg4, abdomen, thorax, neck1, neck2, head1, head2, leye, reye, lantenna, rantenna, larm1, larm2, larm3, lfwing, rfwing, lrwing, rrwing, rarm1, rarm2, rarm3, rlfleg3, rfleg4, rfleg2, rfleg1, rrleg3, rrleg4, rrleg2, rrleg1;
    MantisPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.lfleg1=root.getChild("lfleg1");
        this.lfleg2=root.getChild("lfleg2");
        this.lfleg3=root.getChild("lfleg3");
        this.lfleg4=root.getChild("lfleg4");
        this.lrleg1=root.getChild("lrleg1");
        this.lrleg2=root.getChild("lrleg2");
        this.lrleg3=root.getChild("lrleg3");
        this.lrleg4=root.getChild("lrleg4");
        this.abdomen=root.getChild("abdomen");
        this.thorax=root.getChild("thorax");
        this.neck1=root.getChild("neck1");
        this.neck2=root.getChild("neck2");
        this.head1=root.getChild("head1");
        this.head2=root.getChild("head2");
        this.leye=root.getChild("leye");
        this.reye=root.getChild("reye");
        this.lantenna=root.getChild("lantenna");
        this.rantenna=root.getChild("rantenna");
        this.larm1=root.getChild("larm1");
        this.larm2=root.getChild("larm2");
        this.larm3=root.getChild("larm3");
        this.lfwing=root.getChild("lfwing");
        this.rfwing=root.getChild("rfwing");
        this.lrwing=root.getChild("lrwing");
        this.rrwing=root.getChild("rrwing");
        this.rarm1=root.getChild("rarm1");
        this.rarm2=root.getChild("rarm2");
        this.rarm3=root.getChild("rarm3");
        this.rlfleg3=root.getChild("rlfleg3");
        this.rfleg4=root.getChild("rfleg4");
        this.rfleg2=root.getChild("rfleg2");
        this.rfleg1=root.getChild("rfleg1");
        this.rrleg3=root.getChild("rrleg3");
        this.rrleg4=root.getChild("rrleg4");
        this.rrleg2=root.getChild("rrleg2");
        this.rrleg1=root.getChild("rrleg1");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      newangle = cos(f2 * 0.9F * this.wingspeed) * (float) Math.PI * 0.25F;
      this.lfwing.zRot = -0.698F - newangle;
      this.rfwing.zRot = 0.698F + newangle;
      newangle = cos(f2 * 0.9F * this.wingspeed) * (float) Math.PI * 0.35F;
      this.lrwing.zRot = -0.349F + newangle;
      this.rrwing.zRot = 0.349F - newangle;
      float a1;
      if ((state.attacking ? 1 : 0) == 0) {
         newangle = cos(f2 * 0.051F * this.wingspeed) * (float) Math.PI * 0.013F;
         a1 = -0.2F;
      } else {
         newangle = cos(f2 * 0.51F * this.wingspeed) * (float) Math.PI * 0.25F;
         a1 = -0.698F;
      }
      this.larm1.xRot = a1 + newangle;
      this.larm2.z = (float)(this.larm1.z + 1.0F + Math.sin(this.larm1.xRot) * 22.0);
      this.larm2.y = (float)(this.larm1.y + Math.cos(this.larm1.xRot) * 22.0);
      this.larm2.xRot = -a1 - newangle;
      this.larm3.z = (float)(this.larm2.z + 1.0F - Math.sin(this.larm2.xRot) * 17.0);
      this.larm3.y = (float)(this.larm2.y - Math.cos(this.larm2.xRot) * 17.0);
      this.larm3.xRot = a1 + newangle;
      this.rarm1.xRot = a1 - newangle;
      this.rarm2.z = (float)(this.rarm1.z + 1.0F + Math.sin(this.rarm1.xRot) * 22.0);
      this.rarm2.y = (float)(this.rarm1.y + Math.cos(this.rarm1.xRot) * 22.0);
      this.rarm2.xRot = -a1 + newangle;
      this.rarm3.z = (float)(this.rarm2.z + 1.0F - Math.sin(this.rarm2.xRot) * 17.0);
      this.rarm3.y = (float)(this.rarm2.y - Math.cos(this.rarm2.xRot) * 17.0);
      this.rarm3.xRot = a1 - newangle;
    }
}
