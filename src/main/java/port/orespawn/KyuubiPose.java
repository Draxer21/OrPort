package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class KyuubiPose extends CreaturePose {
    private final ModelPart rtHorn5, lfHorn5, tail9, tail8, tail7, tail6, tail5, tail2, tail1, tail0, lfLegLower, rtLegLower, head, chest, lfArmUpper, rtArmLower, lfLegUpper, rtLegUpper, body, rtArmUpper, lfArmLower, tail3, tail4, lfHorn2, rtHorn1, rtHorn2, lfHorn1, lfHorn3, rtHorn3, lfHorn4, rtHorn4, headFire, lfArmUpperFire, chestFire, bodyFire, lfArmLowerFire, rtArmUpperFire, rtArmLowerFire, lfLegUppperFire, lfLegLowerFire, rtLegUpperFire, rtLegLowerFire;
    KyuubiPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.rtHorn5=root.getChild("rtHorn5");
        this.lfHorn5=root.getChild("lfHorn5");
        this.tail9=root.getChild("tail9");
        this.tail8=root.getChild("tail8");
        this.tail7=root.getChild("tail7");
        this.tail6=root.getChild("tail6");
        this.tail5=root.getChild("tail5");
        this.tail2=root.getChild("tail2");
        this.tail1=root.getChild("tail1");
        this.tail0=root.getChild("tail0");
        this.lfLegLower=root.getChild("lfLegLower");
        this.rtLegLower=root.getChild("rtLegLower");
        this.head=root.getChild("head");
        this.chest=root.getChild("chest");
        this.lfArmUpper=root.getChild("lfArmUpper");
        this.rtArmLower=root.getChild("rtArmLower");
        this.lfLegUpper=root.getChild("lfLegUpper");
        this.rtLegUpper=root.getChild("rtLegUpper");
        this.body=root.getChild("body");
        this.rtArmUpper=root.getChild("rtArmUpper");
        this.lfArmLower=root.getChild("lfArmLower");
        this.tail3=root.getChild("tail3");
        this.tail4=root.getChild("tail4");
        this.lfHorn2=root.getChild("lfHorn2");
        this.rtHorn1=root.getChild("rtHorn1");
        this.rtHorn2=root.getChild("rtHorn2");
        this.lfHorn1=root.getChild("lfHorn1");
        this.lfHorn3=root.getChild("lfHorn3");
        this.rtHorn3=root.getChild("rtHorn3");
        this.lfHorn4=root.getChild("lfHorn4");
        this.rtHorn4=root.getChild("rtHorn4");
        this.headFire=root.getChild("headFire");
        this.lfArmUpperFire=root.getChild("lfArmUpperFire");
        this.chestFire=root.getChild("chestFire");
        this.bodyFire=root.getChild("bodyFire");
        this.lfArmLowerFire=root.getChild("lfArmLowerFire");
        this.rtArmUpperFire=root.getChild("rtArmUpperFire");
        this.rtArmLowerFire=root.getChild("rtArmLowerFire");
        this.lfLegUppperFire=root.getChild("lfLegUppperFire");
        this.lfLegLowerFire=root.getChild("lfLegLowerFire");
        this.rtLegUpperFire=root.getChild("rtLegUpperFire");
        this.rtLegLowerFire=root.getChild("rtLegLowerFire");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 1.1F * this.wingspeed) * (float) Math.PI * 0.2F * f1;
      } else {
         newangle = 0.0F;
      }
      this.rtLegUpper.xRot = 0.59F + newangle;
      this.rtLegUpperFire.xRot = 0.59F + newangle;
      this.rtLegLower.xRot = -0.15F + newangle;
      this.rtLegLowerFire.xRot = -0.15F + newangle;
      this.rtLegLower.z = (float)(Math.sin(this.rtLegUpperFire.xRot) * 8.0);
      this.rtLegLowerFire.z = (float)(Math.sin(this.rtLegUpperFire.xRot) * 8.0);
      this.lfLegUpper.xRot = 0.26F - newangle;
      this.lfLegUppperFire.xRot = 0.26F - newangle;
      this.lfLegLower.xRot = -0.44F - newangle;
      this.lfLegLowerFire.xRot = -0.44F - newangle;
      this.lfLegLower.z = (float)(Math.sin(this.lfLegUppperFire.xRot) * 8.0);
      this.lfLegLowerFire.z = (float)(Math.sin(this.lfLegUppperFire.xRot) * 8.0);
      newangle = cos(f2 * 1.1F * this.wingspeed) * (float) Math.PI * 0.08F * f1;
      newangle += cos(f2 * 0.5F * this.wingspeed) * (float) Math.PI * 0.01F;
      this.rtArmUpper.xRot = newangle;
      this.rtArmUpperFire.xRot = newangle;
      this.rtArmLower.xRot = 0.48F + newangle;
      this.rtArmLowerFire.xRot = 0.48F + newangle;
      this.rtArmLower.z = (float)(Math.sin(this.rtArmUpperFire.xRot) * 8.0);
      this.rtArmLowerFire.z = (float)(Math.sin(this.rtArmUpperFire.xRot) * 8.0);
      this.lfArmUpper.xRot = -newangle;
      this.lfArmUpperFire.xRot = -newangle;
      this.lfArmLower.xRot = 0.48F - newangle;
      this.lfArmLowerFire.xRot = 0.48F - newangle;
      this.lfArmLower.z = (float)(Math.sin(this.lfArmUpperFire.xRot) * 8.0);
      this.lfArmLowerFire.z = (float)(Math.sin(this.lfArmUpperFire.xRot) * 8.0);
      float pi4 = 0.7853975F;
      this.head.yRot = (float)Math.toRadians(f3) + pi4 * 4.0F;
      this.headFire.yRot = (float)Math.toRadians(f3);
      float fc = (float)Math.cos(this.headFire.yRot + pi4);
      float fs = (float)Math.sin(this.headFire.yRot + pi4);
      this.lfHorn1.z = this.headFire.z - fc * 3.6F;
      this.lfHorn1.x = this.headFire.x - fs * 3.6F;
      this.lfHorn1.yRot = this.headFire.yRot + 0.244F + cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.1F;
      this.lfHorn2.z = this.lfHorn1.z - (float)Math.cos(this.lfHorn1.yRot) * 2.0F;
      this.lfHorn2.x = this.lfHorn1.x - (float)Math.sin(this.lfHorn1.yRot) * 2.0F;
      this.lfHorn2.yRot = this.headFire.yRot + 0.244F + cos(f2 * 1.3F * this.wingspeed - pi4) * (float) Math.PI * 0.1F;
      this.lfHorn3.z = this.lfHorn2.z - (float)Math.cos(this.lfHorn2.yRot) * 4.0F;
      this.lfHorn3.x = this.lfHorn2.x - (float)Math.sin(this.lfHorn2.yRot) * 4.0F;
      this.lfHorn3.yRot = this.headFire.yRot
         + 0.244F
         + cos(f2 * 1.3F * this.wingspeed - 2.0F * pi4) * (float) Math.PI * 0.1F;
      this.lfHorn4.z = this.lfHorn3.z - (float)Math.cos(this.lfHorn3.yRot) * 3.0F;
      this.lfHorn4.x = this.lfHorn3.x - (float)Math.sin(this.lfHorn3.yRot) * 3.0F;
      this.lfHorn4.yRot = this.headFire.yRot
         + 0.244F
         + cos(f2 * 1.3F * this.wingspeed - 3.0F * pi4) * (float) Math.PI * 0.1F;
      this.lfHorn5.z = this.lfHorn4.z - (float)Math.cos(this.lfHorn4.yRot) * 2.0F;
      this.lfHorn5.x = this.lfHorn4.x - (float)Math.sin(this.lfHorn4.yRot) * 2.0F;
      this.lfHorn5.yRot = this.headFire.yRot
         + 0.244F
         + cos(f2 * 1.3F * this.wingspeed - 4.0F * pi4) * (float) Math.PI * 0.1F;
      fc = (float)Math.cos(this.headFire.yRot - pi4);
      fs = (float)Math.sin(this.headFire.yRot - pi4);
      this.rtHorn1.z = this.headFire.z - fc * 3.6F;
      this.rtHorn1.x = this.headFire.x - fs * 3.6F;
      this.rtHorn1.yRot = this.headFire.yRot + -0.244F - cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.1F;
      this.rtHorn2.z = this.rtHorn1.z - (float)Math.cos(this.rtHorn1.yRot) * 2.0F;
      this.rtHorn2.x = this.rtHorn1.x - (float)Math.sin(this.rtHorn1.yRot) * 2.0F;
      this.rtHorn2.yRot = this.headFire.yRot + -0.244F - cos(f2 * 1.3F * this.wingspeed - pi4) * (float) Math.PI * 0.1F;
      this.rtHorn3.z = this.rtHorn2.z - (float)Math.cos(this.rtHorn2.yRot) * 4.0F;
      this.rtHorn3.x = this.rtHorn2.x - (float)Math.sin(this.rtHorn2.yRot) * 4.0F;
      this.rtHorn3.yRot = this.headFire.yRot
         + -0.244F
         - cos(f2 * 1.3F * this.wingspeed - 2.0F * pi4) * (float) Math.PI * 0.1F;
      this.rtHorn4.z = this.rtHorn3.z - (float)Math.cos(this.rtHorn3.yRot) * 3.0F;
      this.rtHorn4.x = this.rtHorn3.x - (float)Math.sin(this.rtHorn3.yRot) * 3.0F;
      this.rtHorn4.yRot = this.headFire.yRot
         + -0.244F
         - cos(f2 * 1.3F * this.wingspeed - 3.0F * pi4) * (float) Math.PI * 0.1F;
      this.rtHorn5.z = this.rtHorn4.z - (float)Math.cos(this.rtHorn4.yRot) * 2.0F;
      this.rtHorn5.x = this.rtHorn4.x - (float)Math.sin(this.rtHorn4.yRot) * 2.0F;
      this.rtHorn5.yRot = this.headFire.yRot
         + -0.244F
         - cos(f2 * 1.3F * this.wingspeed - 4.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail1.yRot = cos(f2 * 0.9F * this.wingspeed) * (float) Math.PI * 0.2F;
      this.tail2.x = this.tail1.x - (float)Math.sin(this.tail1.yRot) * 3.0F;
      this.tail2.yRot = cos(f2 * 0.9F * this.wingspeed - pi4) * (float) Math.PI * 0.2F;
      this.tail3.x = this.tail2.x - (float)Math.sin(this.tail2.yRot) * 4.0F;
      this.tail3.yRot = cos(f2 * 0.9F * this.wingspeed - 2.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail4.x = this.tail3.x - (float)Math.sin(this.tail3.yRot) * 3.5F;
      this.tail4.yRot = cos(f2 * 0.9F * this.wingspeed - 3.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail5.x = this.tail4.x - (float)Math.sin(this.tail4.yRot) * 5.0F;
      this.tail5.yRot = cos(f2 * 0.9F * this.wingspeed - 4.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail6.x = this.tail5.x - (float)Math.sin(this.tail5.yRot) * 4.0F;
      this.tail6.yRot = cos(f2 * 0.9F * this.wingspeed - 5.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail7.x = this.tail6.x - (float)Math.sin(this.tail6.yRot) * 3.0F;
      this.tail7.yRot = cos(f2 * 0.9F * this.wingspeed - 6.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail8.x = this.tail7.x - (float)Math.sin(this.tail7.yRot) * 2.0F;
      this.tail8.yRot = cos(f2 * 0.9F * this.wingspeed - 7.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail9.x = this.tail8.x - (float)Math.sin(this.tail8.yRot) * 1.0F;
      this.tail9.yRot = cos(f2 * 0.9F * this.wingspeed - 8.0F * pi4) * (float) Math.PI * 0.2F;
      this.tail1.xRot = -0.26F + cos(f2 * 0.5F * this.wingspeed) * (float) Math.PI * 0.1F;
      this.tail2.y = this.tail1.y + (float)Math.sin(this.tail1.xRot) * 3.0F;
      this.tail2.z = this.tail1.z - (float)Math.cos(this.tail1.xRot) * 3.0F;
      this.tail2.xRot = -0.78F + cos(f2 * 0.5F * this.wingspeed - pi4) * (float) Math.PI * 0.1F;
      this.tail3.y = this.tail2.y + (float)Math.sin(this.tail2.xRot) * 4.0F;
      this.tail3.z = this.tail2.z - (float)Math.cos(this.tail2.xRot) * 4.0F;
      this.tail3.xRot = -1.11F + cos(f2 * 0.5F * this.wingspeed - 2.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail4.y = this.tail3.y + (float)Math.sin(this.tail3.xRot) * 3.5F;
      this.tail4.z = this.tail3.z - (float)Math.cos(this.tail3.xRot) * 3.5F;
      this.tail4.xRot = -0.18F + cos(f2 * 0.5F * this.wingspeed - 3.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail5.y = this.tail4.y + (float)Math.sin(this.tail4.xRot) * 5.0F;
      this.tail5.z = this.tail4.z - (float)Math.cos(this.tail4.xRot) * 5.0F;
      this.tail5.xRot = 0.22F + cos(f2 * 0.5F * this.wingspeed - 4.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail6.y = this.tail5.y + (float)Math.sin(this.tail5.xRot) * 4.0F;
      this.tail6.z = this.tail5.z - (float)Math.cos(this.tail5.xRot) * 4.0F;
      this.tail6.xRot = 0.63F + cos(f2 * 0.5F * this.wingspeed - 5.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail7.y = this.tail6.y + (float)Math.sin(this.tail6.xRot) * 3.0F;
      this.tail7.z = this.tail6.z - (float)Math.cos(this.tail6.xRot) * 3.0F;
      this.tail7.xRot = 0.89F + cos(f2 * 0.5F * this.wingspeed - 6.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail8.y = this.tail7.y + (float)Math.sin(this.tail7.xRot) * 2.0F;
      this.tail8.z = this.tail7.z - (float)Math.cos(this.tail7.xRot) * 2.0F;
      this.tail8.xRot = 1.52F + cos(f2 * 0.5F * this.wingspeed - 7.0F * pi4) * (float) Math.PI * 0.1F;
      this.tail9.y = this.tail8.y + (float)Math.sin(this.tail8.xRot) * 2.0F;
      this.tail9.z = this.tail8.z - (float)Math.cos(this.tail8.xRot) * 2.0F;
      this.tail9.xRot = 2.0F + cos(f2 * 0.5F * this.wingspeed - 8.0F * pi4) * (float) Math.PI * 0.1F;
    }
}
