package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class SpyroPose extends CreaturePose {
    private final ModelPart RightFrontPaw, WingLeft, LegRightFrontTop, LegRightFrontBottom, LegRightBackTop, LegRightBackBottom, RightBackPaw, LegLeftFrontTop, SnoutRight, LeftFrontPaw, LegLeftBackTop, LegLeftBackBottom, LeftBackPaw, LegLeftFrontBottom, TailPieceSmall, JawPiece, HeadPieceBottom, HeadPieceTop, HornRightBottom, HornLeftBottom, HornRightTop, HornLeftTop, Torso, SnoutLeft, WingPieceLeft, WingRight, WingPieceRight, Neck, TailBack, TailFront, ScaleBackHead, TailPieceLarge, ScaleTailPiece, ScaleHead, ScaleTop1, ScaleBackPiece1, ScaleBackPiece2;
    SpyroPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.RightFrontPaw=root.getChild("RightFrontPaw");
        this.WingLeft=root.getChild("WingLeft");
        this.LegRightFrontTop=root.getChild("LegRightFrontTop");
        this.LegRightFrontBottom=root.getChild("LegRightFrontBottom");
        this.LegRightBackTop=root.getChild("LegRightBackTop");
        this.LegRightBackBottom=root.getChild("LegRightBackBottom");
        this.RightBackPaw=root.getChild("RightBackPaw");
        this.LegLeftFrontTop=root.getChild("LegLeftFrontTop");
        this.SnoutRight=root.getChild("SnoutRight");
        this.LeftFrontPaw=root.getChild("LeftFrontPaw");
        this.LegLeftBackTop=root.getChild("LegLeftBackTop");
        this.LegLeftBackBottom=root.getChild("LegLeftBackBottom");
        this.LeftBackPaw=root.getChild("LeftBackPaw");
        this.LegLeftFrontBottom=root.getChild("LegLeftFrontBottom");
        this.TailPieceSmall=root.getChild("TailPieceSmall");
        this.JawPiece=root.getChild("JawPiece");
        this.HeadPieceBottom=root.getChild("HeadPieceBottom");
        this.HeadPieceTop=root.getChild("HeadPieceTop");
        this.HornRightBottom=root.getChild("HornRightBottom");
        this.HornLeftBottom=root.getChild("HornLeftBottom");
        this.HornRightTop=root.getChild("HornRightTop");
        this.HornLeftTop=root.getChild("HornLeftTop");
        this.Torso=root.getChild("Torso");
        this.SnoutLeft=root.getChild("SnoutLeft");
        this.WingPieceLeft=root.getChild("WingPieceLeft");
        this.WingRight=root.getChild("WingRight");
        this.WingPieceRight=root.getChild("WingPieceRight");
        this.Neck=root.getChild("Neck");
        this.TailBack=root.getChild("TailBack");
        this.TailFront=root.getChild("TailFront");
        this.ScaleBackHead=root.getChild("ScaleBackHead");
        this.TailPieceLarge=root.getChild("TailPieceLarge");
        this.ScaleTailPiece=root.getChild("ScaleTailPiece");
        this.ScaleHead=root.getChild("ScaleHead");
        this.ScaleTop1=root.getChild("ScaleTop1");
        this.ScaleBackPiece1=root.getChild("ScaleBackPiece1");
        this.ScaleBackPiece2=root.getChild("ScaleBackPiece2");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float hf = 0.0F;
      float newangle = 0.0F;
      int current_activity = (state.sitting ? 3 : 0);
      if (f1 > 0.1) {
         newangle = cos(f2 * 2.3F * this.wingspeed) * (float) Math.PI * 0.4F * f1;
      } else {
         newangle = 0.0F;
      }
      if (current_activity == 3) {
         newangle *= 0.5F;
      }
      this.WingLeft.zRot = newangle;
      this.WingRight.zRot = -newangle;
      if (f1 > 0.1) {
         newangle = cos(f2 * 2.0F * this.wingspeed) * (float) Math.PI * 0.25F * f1;
      } else {
         newangle = 0.0F;
      }
      if (current_activity == 3) {
         newangle = 0.0F;
      }
      if (current_activity != 2) {
         this.LegRightFrontTop.xRot = newangle - 0.087F;
         this.LegRightFrontBottom.xRot = newangle - 0.17F;
         this.RightFrontPaw.xRot = newangle;
         this.LegLeftFrontTop.xRot = -newangle - 0.087F;
         this.LegLeftFrontBottom.xRot = -newangle - 0.17F;
         this.LeftFrontPaw.xRot = -newangle;
         this.LegRightBackBottom.xRot = -newangle + 0.139F;
         this.LegRightBackTop.xRot = -newangle - 0.174F;
         this.RightBackPaw.xRot = -newangle;
         this.LegLeftBackBottom.xRot = newangle + 0.139F;
         this.LegLeftBackTop.xRot = newangle - 0.174F;
         this.LeftBackPaw.xRot = newangle;
      } else {
         newangle = -1.0F;
         this.LegRightFrontTop.xRot = newangle - 0.087F;
         this.LegRightFrontBottom.xRot = newangle - 0.17F;
         this.RightFrontPaw.xRot = newangle;
         this.LegLeftFrontTop.xRot = newangle - 0.087F;
         this.LegLeftFrontBottom.xRot = newangle - 0.17F;
         this.LeftFrontPaw.xRot = newangle;
         newangle = 1.0F;
         this.LegRightBackBottom.xRot = newangle + 0.139F;
         this.LegRightBackTop.xRot = newangle - 0.174F;
         this.RightBackPaw.xRot = newangle;
         this.LegLeftBackBottom.xRot = newangle + 0.139F;
         this.LegLeftBackTop.xRot = newangle - 0.174F;
         this.LeftBackPaw.xRot = newangle;
      }
      newangle = cos(f2 * 1.2F * this.wingspeed) * (float) Math.PI * 0.25F;
      if (state.sitting || current_activity == 3) {
         newangle = 0.0F;
      }
      this.TailBack.yRot = newangle;
      this.ScaleTailPiece.yRot = newangle;
      this.TailFront.z = this.TailBack.z + (float)Math.cos(this.TailBack.yRot) * 3.0F;
      this.TailFront.x = this.TailBack.x + (float)Math.sin(this.TailBack.yRot) * 3.0F - 0.5F;
      this.TailFront.yRot = newangle * 1.6F;
      this.TailPieceLarge.z = this.TailFront.z;
      this.TailPieceLarge.x = this.TailFront.x;
      this.TailPieceLarge.yRot = this.TailFront.yRot;
      this.TailPieceSmall.z = this.TailFront.z;
      this.TailPieceSmall.x = this.TailFront.x;
      this.TailPieceSmall.yRot = this.TailFront.yRot;
      this.HeadPieceTop.yRot = (float)Math.toRadians(f3);
      this.HeadPieceBottom.yRot = (float)Math.toRadians(f3);
      this.JawPiece.yRot = (float)Math.toRadians(f3);
      this.SnoutRight.yRot = (float)Math.toRadians(f3);
      this.SnoutLeft.yRot = (float)Math.toRadians(f3);
      this.ScaleTop1.yRot = (float)Math.toRadians(f3);
      this.ScaleHead.yRot = (float)Math.toRadians(f3);
      this.ScaleBackHead.yRot = (float)Math.toRadians(f3);
      this.HornRightBottom.yRot = (float)Math.toRadians(f3) + 0.785F;
      this.HornRightTop.yRot = (float)Math.toRadians(f3) + 0.785F;
      this.HornLeftBottom.yRot = (float)Math.toRadians(f3) - 0.785F;
      this.HornLeftTop.yRot = (float)Math.toRadians(f3) - 0.785F;
      this.HeadPieceTop.xRot = (float)Math.toRadians(f4);
      this.HeadPieceBottom.xRot = (float)Math.toRadians(f4);
      this.JawPiece.xRot = (float)Math.toRadians(f4);
      this.SnoutRight.xRot = (float)Math.toRadians(f4);
      this.SnoutLeft.xRot = (float)Math.toRadians(f4);
      this.ScaleTop1.xRot = (float)Math.toRadians(f4);
      this.ScaleHead.xRot = (float)Math.toRadians(f4);
      this.ScaleBackHead.xRot = (float)Math.toRadians(f4);
      this.HornRightBottom.xRot = (float)Math.toRadians(f4) - 0.785F;
      this.HornRightTop.xRot = (float)Math.toRadians(f4) - 0.785F;
      this.HornLeftBottom.xRot = (float)Math.toRadians(f4) - 0.785F;
      this.HornLeftTop.xRot = (float)Math.toRadians(f4) - 0.785F;
    }
}
