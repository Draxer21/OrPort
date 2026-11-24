package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class PointysaurusPose extends CreaturePose {
    private final ModelPart lfleg, rfleg, lrleg, rrleg, body1, head, body2, body3, guard, nose, lhorn, rhorn, chorn, tail, bump1, bump2, bump3, bump4, bump5, bump6, bump7, bump8, bump9, bump10, bump11, bump12, bump13, bump14, bump15, bump16;
    PointysaurusPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.lfleg=root.getChild("lfleg");
        this.rfleg=root.getChild("rfleg");
        this.lrleg=root.getChild("lrleg");
        this.rrleg=root.getChild("rrleg");
        this.body1=root.getChild("body1");
        this.head=root.getChild("head");
        this.body2=root.getChild("body2");
        this.body3=root.getChild("body3");
        this.guard=root.getChild("guard");
        this.nose=root.getChild("nose");
        this.lhorn=root.getChild("lhorn");
        this.rhorn=root.getChild("rhorn");
        this.chorn=root.getChild("chorn");
        this.tail=root.getChild("tail");
        this.bump1=root.getChild("bump1");
        this.bump2=root.getChild("bump2");
        this.bump3=root.getChild("bump3");
        this.bump4=root.getChild("bump4");
        this.bump5=root.getChild("bump5");
        this.bump6=root.getChild("bump6");
        this.bump7=root.getChild("bump7");
        this.bump8=root.getChild("bump8");
        this.bump9=root.getChild("bump9");
        this.bump10=root.getChild("bump10");
        this.bump11=root.getChild("bump11");
        this.bump12=root.getChild("bump12");
        this.bump13=root.getChild("bump13");
        this.bump14=root.getChild("bump14");
        this.bump15=root.getChild("bump15");
        this.bump16=root.getChild("bump16");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.25F * f1;
      } else {
         newangle = 0.0F;
      }
      this.lfleg.xRot = newangle;
      this.rrleg.xRot = newangle;
      this.rfleg.xRot = -newangle;
      this.lrleg.xRot = -newangle;
      this.head.yRot = (float)Math.toRadians(f3) * 0.45F;
      this.nose.yRot = this.head.yRot;
      this.chorn.yRot = this.head.yRot;
      this.lhorn.yRot = this.head.yRot - 0.14F;
      this.rhorn.yRot = this.head.yRot + 0.14F;
      this.guard.yRot = this.head.yRot;
      this.bump1.yRot = this.head.yRot;
      this.bump2.yRot = this.head.yRot;
      this.bump3.yRot = this.head.yRot;
      this.bump4.yRot = this.head.yRot;
      this.bump5.yRot = this.head.yRot;
      this.bump6.yRot = this.head.yRot;
      this.bump7.yRot = this.head.yRot;
      this.bump8.yRot = this.head.yRot;
      this.bump9.yRot = this.head.yRot;
      this.bump10.yRot = this.head.yRot;
      this.bump11.yRot = this.head.yRot;
      this.bump12.yRot = this.head.yRot;
      this.bump13.yRot = this.head.yRot;
      this.bump14.yRot = this.head.yRot;
      this.bump15.yRot = this.head.yRot;
      this.bump16.yRot = this.head.yRot;
      this.head.xRot = (float)Math.toRadians(f4) * 0.45F;
      this.nose.xRot = this.head.xRot;
      this.chorn.xRot = this.head.xRot;
      this.lhorn.xRot = this.head.xRot - 0.16F;
      this.rhorn.xRot = this.head.xRot - 0.16F;
      this.guard.xRot = this.head.xRot - 0.262F;
      this.bump1.xRot = this.guard.xRot;
      this.bump2.xRot = this.guard.xRot;
      this.bump3.xRot = this.guard.xRot;
      this.bump4.xRot = this.guard.xRot;
      this.bump5.xRot = this.guard.xRot;
      this.bump6.xRot = this.guard.xRot;
      this.bump7.xRot = this.guard.xRot;
      this.bump8.xRot = this.guard.xRot;
      this.bump9.xRot = this.guard.xRot;
      this.bump10.xRot = this.guard.xRot;
      this.bump11.xRot = this.guard.xRot;
      this.bump12.xRot = this.guard.xRot;
      this.bump13.xRot = this.guard.xRot;
      this.bump14.xRot = this.guard.xRot;
      this.bump15.xRot = this.guard.xRot;
      this.bump16.xRot = this.guard.xRot;
      if ((state.attacking ? 1 : 0) != 0) {
         newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.25F;
      } else {
         newangle = cos(f2 * 0.3F * this.wingspeed) * (float) Math.PI * 0.05F;
      }
      this.tail.yRot = newangle;
      newangle = cos(f2 * 0.02F * this.wingspeed) * (float) Math.PI * 0.15F;
      this.tail.xRot = newangle + 0.28F;
    }
}
