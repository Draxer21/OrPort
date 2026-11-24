package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class GammaMetroidPose extends CreaturePose {
    private final ModelPart Shell3, Shell4, Head, BeakUpper, BeakLower, LeftTusk, MiddleTusk, RightTusk, LeftFrontUpperLeg, LeftFrontLowerLeg, LeftRearUpperLeg, LeftRearLowerLeg, RightFrontUpperLeg, RightFrontLowerLeg, RightRearUpperLeg, RightRearLowerLeg, Core, Bellyinside, Bellyoutside, Shell1, Shell2;
    GammaMetroidPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Shell3=root.getChild("Shell3");
        this.Shell4=root.getChild("Shell4");
        this.Head=root.getChild("Head");
        this.BeakUpper=root.getChild("BeakUpper");
        this.BeakLower=root.getChild("BeakLower");
        this.LeftTusk=root.getChild("LeftTusk");
        this.MiddleTusk=root.getChild("MiddleTusk");
        this.RightTusk=root.getChild("RightTusk");
        this.LeftFrontUpperLeg=root.getChild("LeftFrontUpperLeg");
        this.LeftFrontLowerLeg=root.getChild("LeftFrontLowerLeg");
        this.LeftRearUpperLeg=root.getChild("LeftRearUpperLeg");
        this.LeftRearLowerLeg=root.getChild("LeftRearLowerLeg");
        this.RightFrontUpperLeg=root.getChild("RightFrontUpperLeg");
        this.RightFrontLowerLeg=root.getChild("RightFrontLowerLeg");
        this.RightRearUpperLeg=root.getChild("RightRearUpperLeg");
        this.RightRearLowerLeg=root.getChild("RightRearLowerLeg");
        this.Core=root.getChild("Core");
        this.Bellyinside=root.getChild("Bellyinside");
        this.Bellyoutside=root.getChild("Bellyoutside");
        this.Shell1=root.getChild("Shell1");
        this.Shell2=root.getChild("Shell2");
    }
    private void doLeftFLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
      seg2.xRot = angle - 0.17F;
      seg3.xRot = angle - 0.26F;
      seg3.z = (float)(seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5F;
      seg2.zRot = -upangle - 0.66F;
      seg3.zRot = -upangle;
      seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
      seg3.x = (float)(seg2.x + Math.abs(Math.sin(seg2.zRot) * 7.0) + 1.0);
    }
    private void doLeftRLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
      seg2.xRot = angle + 0.17F;
      seg3.xRot = angle + 0.31F;
      seg3.z = (float)(seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5F;
      seg2.zRot = -upangle - 0.82F;
      seg3.zRot = -upangle;
      seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
      seg3.x = (float)(seg2.x + Math.abs(Math.sin(seg2.zRot) * 7.0) + 1.5);
    }
    private void doRightFLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
      seg2.xRot = angle - 0.17F;
      seg3.xRot = angle - 0.26F;
      seg3.z = (float)(seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5F;
      seg2.zRot = -upangle + 0.34F;
      seg3.zRot = -upangle;
      seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
      seg3.x = (float)(seg2.x - Math.abs(Math.sin(seg2.zRot) * 7.0) - 1.0);
    }
    private void doRightRLeg(ModelPart seg2, ModelPart seg3, float angle, float upangle) {
      seg2.xRot = angle + 0.17F;
      seg3.xRot = angle + 0.31F;
      seg3.z = (float)(seg2.z + Math.sin(seg2.xRot) * 7.0) - 0.5F;
      seg2.zRot = -upangle + 0.82F;
      seg3.zRot = -upangle;
      seg3.y = seg2.y + (float)(5.0 * Math.cos(seg2.xRot));
      seg3.x = (float)(seg2.x - Math.abs(Math.sin(seg2.zRot) * 7.0) - 1.5);
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      newangle = cos(f2 * 0.81F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.LeftTusk.xRot = newangle;
      newangle = cos(f2 * 0.87F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.RightTusk.xRot = newangle;
      newangle = cos(f2 * 0.99F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.MiddleTusk.xRot = newangle;
      newangle = cos(f2 * 1.11F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.LeftTusk.yRot = newangle;
      newangle = cos(f2 * 1.17F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.RightTusk.yRot = newangle;
      newangle = cos(f2 * 1.25F * this.wingspeed) * (float) Math.PI * 0.08F;
      this.MiddleTusk.yRot = newangle;
      float nextangle = 0.0F;
      float upangle = 0.0F;
      newangle = cos(f2 * 2.0F * this.wingspeed) * (float) Math.PI * 0.12F * f1;
      nextangle = cos((f2 + 0.1F) * 2.0F * this.wingspeed) * (float) Math.PI * 0.12F * f1;
      upangle = 0.0F;
      if (nextangle > newangle) {
         upangle = 0.47F * f1 - Math.abs(newangle);
      }
      this.doLeftFLeg(this.LeftFrontUpperLeg, this.LeftFrontLowerLeg, newangle, upangle);
      this.doRightFLeg(this.RightFrontUpperLeg, this.RightFrontLowerLeg, -newangle, upangle);
      this.doLeftRLeg(this.LeftRearUpperLeg, this.LeftRearLowerLeg, -newangle, upangle);
      this.doRightRLeg(this.RightRearUpperLeg, this.RightRearLowerLeg, newangle, upangle);
      newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.05F;
      if (state.sitting) {
         newangle = 0.0F;
      }
      this.Shell1.xRot = newangle / 4.0F;
      this.Shell1.yRot = -(newangle / 4.0F);
      this.Shell2.xRot = newangle - 0.49F;
      this.Shell2.yRot = -newangle + 0.33F;
      this.Shell3.xRot = newangle - 0.96F;
      this.Shell3.yRot = -newangle + 0.63F;
      this.Shell4.xRot = newangle - 0.28F;
      newangle = cos(f2 * 0.75F * this.wingspeed) * (float) Math.PI * 0.1F;
      newangle = Math.abs(newangle);
      this.BeakLower.xRot = newangle + 0.14F;
      this.BeakLower.zRot = newangle + 0.14F;
    }
}
