package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;
final class BasiliskPose extends CreaturePose {
    private final ModelPart body3, body2, body1, body4, body5, body6, tail1, tail2, tail3, tail4, neck2, neck1, head, rog_1, rog_2, rog_3, rog_4, rog_5, rog_6, snout, jaw;
    BasiliskPose(ModelPart root,float speed,float scale){super(root,speed,scale);
        body3=root.getChild("body3");
        body2=root.getChild("body2");
        body1=root.getChild("body1");
        body4=root.getChild("body4");
        body5=root.getChild("body5");
        body6=root.getChild("body6");
        tail1=root.getChild("tail1");
        tail2=root.getChild("tail2");
        tail3=root.getChild("tail3");
        tail4=root.getChild("tail4");
        neck2=root.getChild("neck2");
        neck1=root.getChild("neck1");
        head=root.getChild("head");
        rog_1=root.getChild("rog_1");
        rog_2=root.getChild("rog_2");
        rog_3=root.getChild("rog_3");
        rog_4=root.getChild("rog_4");
        rog_5=root.getChild("rog_5");
        rog_6=root.getChild("rog_6");
        snout=root.getChild("snout");
        jaw=root.getChild("jaw");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4){

      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.1F * f1;
      } else {
         newangle = 0.0F;
      }

      float pi4 = 0.7853975F;
      this.body1.yRot = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.1F * f1;
      this.body2.z = this.body1.z + (float)Math.cos(this.body1.yRot) * 12.0F;
      this.body2.x = this.body1.x + (float)Math.sin(this.body1.yRot) * 12.0F;
      this.body2.yRot = cos(f2 * 1.3F * this.wingspeed - pi4) * (float) Math.PI * 0.1F * f1;
      this.body3.z = this.body2.z + (float)Math.cos(this.body2.yRot) * 11.0F;
      this.body3.x = this.body2.x + (float)Math.sin(this.body2.yRot) * 11.0F;
      this.body3.yRot = cos(f2 * 1.3F * this.wingspeed - 2.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.body4.z = this.body3.z + (float)Math.cos(this.body3.yRot) * 12.0F;
      this.body4.x = this.body3.x + (float)Math.sin(this.body3.yRot) * 12.0F;
      this.body4.yRot = cos(f2 * 1.3F * this.wingspeed - 3.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.body5.z = this.body4.z + (float)Math.cos(this.body4.yRot) * 12.0F;
      this.body5.x = this.body4.x + (float)Math.sin(this.body4.yRot) * 12.0F;
      this.body5.yRot = cos(f2 * 1.3F * this.wingspeed - 4.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.body6.z = this.body5.z + (float)Math.cos(this.body5.yRot) * 12.0F;
      this.body6.x = this.body5.x + 0.5F + (float)Math.sin(this.body5.yRot) * 12.0F;
      this.body6.yRot = cos(f2 * 1.3F * this.wingspeed - 5.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.tail1.z = this.body6.z + (float)Math.cos(this.body6.yRot) * 12.0F;
      this.tail1.x = this.body6.x + 1.0F + (float)Math.sin(this.body6.yRot) * 12.0F;
      this.tail1.yRot = cos(f2 * 1.3F * this.wingspeed - 6.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 10.0F;
      this.tail2.x = this.tail1.x + 1.5F + (float)Math.sin(this.tail1.yRot) * 10.0F;
      this.tail2.yRot = cos(f2 * 1.3F * this.wingspeed - 7.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 10.0F;
      this.tail3.x = this.tail2.x + 1.0F + (float)Math.sin(this.tail2.yRot) * 10.0F;
      this.tail3.yRot = cos(f2 * 1.3F * this.wingspeed - 8.0F * pi4) * (float) Math.PI * 0.1F * f1;
      this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 10.0F;
      this.tail4.x = this.tail3.x + 1.0F + (float)Math.sin(this.tail3.yRot) * 10.0F;
      this.tail4.yRot = cos(f2 * 1.3F * this.wingspeed - 9.0F * pi4) * (float) Math.PI * 0.1F * f1;
      if (state.attacking) {
         this.jaw.xRot = -1.0F + cos(f2 * 0.45F) * (float) Math.PI * 0.18F;
      } else {
         this.jaw.xRot = -1.1F;
      }

    }

}
