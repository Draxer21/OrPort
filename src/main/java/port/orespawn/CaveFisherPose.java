package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class CaveFisherPose extends CreaturePose {
    private final ModelPart Nose, EyeLeft, HeadMid, HeadEnd, TailTuft, EyeRight, BodyTopLeft4, BodyTopRight4, BodyTopLeft1, BodyTopRight1, BodyTopRight2, BodyTopLeft2, BodyTopRight3, BodyTopLeft3, HeadBase, TailBase, BodyLow2, BodyLow1, Spine5, Spine1, Spine2, Spine3, Spine4, RightArmSeg4, LeftArmSeg1, LeftArmSeg3, RightArmSeg2, RightArmSeg1, LeftArmSeg5, LeftArmSeg2, LeftClawTop, RightArmSeg3, RightArmSeg5, LeftArmSeg4, LeftClawBase, RightClawBase, LeftClawLow, RightClawTop, RightClawLow, LBLeg1, LBLeg3, RBLeg1, RBLeg3, LBLeg2, RBLeg2, LBLeg4, RBLeg4, RBLeg5, LBLeg6, RBLeg6, LBLeg5, RFLeg1, RFLeg2, RFLeg3, RFLeg4, RFLeg5, RFLeg6, RMLeg1, RMLeg2, RMLeg3, RMLeg4, RMLeg5, RMLeg6, LFLeg1, LFLeg2, LFLeg3, LFLeg4, LFLeg5, LFLeg6, LMLeg1, LMLeg2, LMLeg4, LMLeg3, LMLeg5, LMLeg6;
    CaveFisherPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Nose=root.getChild("Nose");
        this.EyeLeft=root.getChild("EyeLeft");
        this.HeadMid=root.getChild("HeadMid");
        this.HeadEnd=root.getChild("HeadEnd");
        this.TailTuft=root.getChild("TailTuft");
        this.EyeRight=root.getChild("EyeRight");
        this.BodyTopLeft4=root.getChild("BodyTopLeft4");
        this.BodyTopRight4=root.getChild("BodyTopRight4");
        this.BodyTopLeft1=root.getChild("BodyTopLeft1");
        this.BodyTopRight1=root.getChild("BodyTopRight1");
        this.BodyTopRight2=root.getChild("BodyTopRight2");
        this.BodyTopLeft2=root.getChild("BodyTopLeft2");
        this.BodyTopRight3=root.getChild("BodyTopRight3");
        this.BodyTopLeft3=root.getChild("BodyTopLeft3");
        this.HeadBase=root.getChild("HeadBase");
        this.TailBase=root.getChild("TailBase");
        this.BodyLow2=root.getChild("BodyLow2");
        this.BodyLow1=root.getChild("BodyLow1");
        this.Spine5=root.getChild("Spine5");
        this.Spine1=root.getChild("Spine1");
        this.Spine2=root.getChild("Spine2");
        this.Spine3=root.getChild("Spine3");
        this.Spine4=root.getChild("Spine4");
        this.RightArmSeg4=root.getChild("RightArmSeg4");
        this.LeftArmSeg1=root.getChild("LeftArmSeg1");
        this.LeftArmSeg3=root.getChild("LeftArmSeg3");
        this.RightArmSeg2=root.getChild("RightArmSeg2");
        this.RightArmSeg1=root.getChild("RightArmSeg1");
        this.LeftArmSeg5=root.getChild("LeftArmSeg5");
        this.LeftArmSeg2=root.getChild("LeftArmSeg2");
        this.LeftClawTop=root.getChild("LeftClawTop");
        this.RightArmSeg3=root.getChild("RightArmSeg3");
        this.RightArmSeg5=root.getChild("RightArmSeg5");
        this.LeftArmSeg4=root.getChild("LeftArmSeg4");
        this.LeftClawBase=root.getChild("LeftClawBase");
        this.RightClawBase=root.getChild("RightClawBase");
        this.LeftClawLow=root.getChild("LeftClawLow");
        this.RightClawTop=root.getChild("RightClawTop");
        this.RightClawLow=root.getChild("RightClawLow");
        this.LBLeg1=root.getChild("LBLeg1");
        this.LBLeg3=root.getChild("LBLeg3");
        this.RBLeg1=root.getChild("RBLeg1");
        this.RBLeg3=root.getChild("RBLeg3");
        this.LBLeg2=root.getChild("LBLeg2");
        this.RBLeg2=root.getChild("RBLeg2");
        this.LBLeg4=root.getChild("LBLeg4");
        this.RBLeg4=root.getChild("RBLeg4");
        this.RBLeg5=root.getChild("RBLeg5");
        this.LBLeg6=root.getChild("LBLeg6");
        this.RBLeg6=root.getChild("RBLeg6");
        this.LBLeg5=root.getChild("LBLeg5");
        this.RFLeg1=root.getChild("RFLeg1");
        this.RFLeg2=root.getChild("RFLeg2");
        this.RFLeg3=root.getChild("RFLeg3");
        this.RFLeg4=root.getChild("RFLeg4");
        this.RFLeg5=root.getChild("RFLeg5");
        this.RFLeg6=root.getChild("RFLeg6");
        this.RMLeg1=root.getChild("RMLeg1");
        this.RMLeg2=root.getChild("RMLeg2");
        this.RMLeg3=root.getChild("RMLeg3");
        this.RMLeg4=root.getChild("RMLeg4");
        this.RMLeg5=root.getChild("RMLeg5");
        this.RMLeg6=root.getChild("RMLeg6");
        this.LFLeg1=root.getChild("LFLeg1");
        this.LFLeg2=root.getChild("LFLeg2");
        this.LFLeg3=root.getChild("LFLeg3");
        this.LFLeg4=root.getChild("LFLeg4");
        this.LFLeg5=root.getChild("LFLeg5");
        this.LFLeg6=root.getChild("LFLeg6");
        this.LMLeg1=root.getChild("LMLeg1");
        this.LMLeg2=root.getChild("LMLeg2");
        this.LMLeg4=root.getChild("LMLeg4");
        this.LMLeg3=root.getChild("LMLeg3");
        this.LMLeg5=root.getChild("LMLeg5");
        this.LMLeg6=root.getChild("LMLeg6");
    }
    private void doLeftClaw(float angle) {
      this.LeftArmSeg1.xRot = Math.abs(angle);
      this.LeftArmSeg2.xRot = Math.abs(angle);
      this.LeftArmSeg3.xRot = Math.abs(angle);
      this.LeftArmSeg4.xRot = Math.abs(angle);
      this.LeftArmSeg5.xRot = Math.abs(angle);
      this.LeftClawBase.xRot = Math.abs(angle);
      this.LeftClawTop.xRot = Math.abs(angle) - 0.54F;
      this.LeftClawLow.xRot = Math.abs(angle) + 0.35F;
    }
    private void doRightClaw(float angle) {
      this.RightArmSeg1.xRot = Math.abs(angle);
      this.RightArmSeg2.xRot = Math.abs(angle);
      this.RightArmSeg3.xRot = Math.abs(angle);
      this.RightArmSeg4.xRot = Math.abs(angle);
      this.RightArmSeg5.xRot = Math.abs(angle);
      this.RightClawBase.xRot = Math.abs(angle);
      this.RightClawTop.xRot = Math.abs(angle) - 0.54F;
      this.RightClawLow.xRot = Math.abs(angle) + 0.35F;
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      float upangle = 0.0F;
      float nextangle = 0.0F;
      float pi4 = 1.570795F;
      newangle = cos(f2 * 2.0F * this.wingspeed) * (float) Math.PI * 0.12F * f1;
      this.LFLeg1.yRot = newangle;
      this.LFLeg2.yRot = newangle;
      this.LFLeg3.yRot = newangle;
      this.LFLeg4.yRot = newangle;
      this.LFLeg5.yRot = newangle;
      this.LFLeg6.yRot = newangle;
      this.RFLeg1.yRot = -newangle;
      this.RFLeg2.yRot = -newangle;
      this.RFLeg3.yRot = -newangle;
      this.RFLeg4.yRot = -newangle;
      this.RFLeg5.yRot = -newangle;
      this.RFLeg6.yRot = -newangle;
      newangle = cos(f2 * 2.0F * this.wingspeed - 1.0F * pi4) * (float) Math.PI * 0.12F * f1;
      this.LMLeg1.yRot = newangle;
      this.LMLeg2.yRot = newangle;
      this.LMLeg3.yRot = newangle;
      this.LMLeg4.yRot = newangle;
      this.LMLeg5.yRot = newangle;
      this.LMLeg6.yRot = newangle;
      this.RMLeg1.yRot = -newangle;
      this.RMLeg2.yRot = -newangle;
      this.RMLeg3.yRot = -newangle;
      this.RMLeg4.yRot = -newangle;
      this.RMLeg5.yRot = -newangle;
      this.RMLeg6.yRot = -newangle;
      newangle = cos(f2 * 2.0F * this.wingspeed - 2.0F * pi4) * (float) Math.PI * 0.12F * f1;
      this.LBLeg1.yRot = newangle;
      this.LBLeg2.yRot = newangle;
      this.LBLeg3.yRot = newangle;
      this.LBLeg4.yRot = newangle;
      this.LBLeg5.yRot = newangle;
      this.LBLeg6.yRot = newangle;
      this.RBLeg1.yRot = -newangle;
      this.RBLeg2.yRot = -newangle;
      this.RBLeg3.yRot = -newangle;
      this.RBLeg4.yRot = -newangle;
      this.RBLeg5.yRot = -newangle;
      this.RBLeg6.yRot = -newangle;
      newangle = cos(f2 * 3.0F * this.wingspeed) * (float) Math.PI * 0.15F;
      nextangle = cos((f2 + 0.1F) * 3.0F * this.wingspeed) * (float) Math.PI * 0.15F;
      if ((state.attacking ? 1 : 0) == 0) {
         newangle = 0.0F;
      }
      if (nextangle > 0.0F && newangle < 0.0F) {
         state.ri1 = 0;
         if ((state.attacking ? 1 : 0) == 0) {
            state.ri1 = state.random.nextInt(20);
            state.ri2 = state.random.nextInt(25);
         } else {
            state.ri1 = state.random.nextInt(4);
            state.ri2 = state.random.nextInt(3);
         }
      }
      if (state.ri1 != 1 && state.ri1 != 3) {
         this.doLeftClaw(0.0F);
         this.doRightClaw(0.0F);
      } else {
         this.doLeftClaw(newangle);
         this.doRightClaw(newangle);
      }
    }
}
