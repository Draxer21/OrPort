package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class NastysaurusPose extends CreaturePose {
    private final ModelPart lclaw1, body, leftleg1, tail1, leftleg2, body2, leftleg3, tail2, tail3, lclaw2, lclaw3, lclaw4, lclaw5, lclaw6, lclaw7, neck3, head3, jaw1, tooth1, tooth2, tooth3, tooth4, tooth5, jaw5, head7, tooth6, tooth7, tooth8, tooth9, tooth10, tooth11, tooth12, tooth13, rightleg1, rightleg2, tooth14, tooth15, tooth16, tooth17, tooth18, tooth19, tooth20, tooth21, tooth22, tooth23, rightleg3, rclaw2, rclaw4, rclaw1, rclaw5, rclaw7, rclaw3, rclaw6, neck1, neck2, tail4, Spike1, Spike2, Spike3;
    NastysaurusPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.lclaw1=root.getChild("lclaw1");
        this.body=root.getChild("body");
        this.leftleg1=root.getChild("leftleg1");
        this.tail1=root.getChild("tail1");
        this.leftleg2=root.getChild("leftleg2");
        this.body2=root.getChild("body2");
        this.leftleg3=root.getChild("leftleg3");
        this.tail2=root.getChild("tail2");
        this.tail3=root.getChild("tail3");
        this.lclaw2=root.getChild("lclaw2");
        this.lclaw3=root.getChild("lclaw3");
        this.lclaw4=root.getChild("lclaw4");
        this.lclaw5=root.getChild("lclaw5");
        this.lclaw6=root.getChild("lclaw6");
        this.lclaw7=root.getChild("lclaw7");
        this.neck3=root.getChild("neck3");
        this.head3=root.getChild("head3");
        this.jaw1=root.getChild("jaw1");
        this.tooth1=root.getChild("tooth1");
        this.tooth2=root.getChild("tooth2");
        this.tooth3=root.getChild("tooth3");
        this.tooth4=root.getChild("tooth4");
        this.tooth5=root.getChild("tooth5");
        this.jaw5=root.getChild("jaw5");
        this.head7=root.getChild("head7");
        this.tooth6=root.getChild("tooth6");
        this.tooth7=root.getChild("tooth7");
        this.tooth8=root.getChild("tooth8");
        this.tooth9=root.getChild("tooth9");
        this.tooth10=root.getChild("tooth10");
        this.tooth11=root.getChild("tooth11");
        this.tooth12=root.getChild("tooth12");
        this.tooth13=root.getChild("tooth13");
        this.rightleg1=root.getChild("rightleg1");
        this.rightleg2=root.getChild("rightleg2");
        this.tooth14=root.getChild("tooth14");
        this.tooth15=root.getChild("tooth15");
        this.tooth16=root.getChild("tooth16");
        this.tooth17=root.getChild("tooth17");
        this.tooth18=root.getChild("tooth18");
        this.tooth19=root.getChild("tooth19");
        this.tooth20=root.getChild("tooth20");
        this.tooth21=root.getChild("tooth21");
        this.tooth22=root.getChild("tooth22");
        this.tooth23=root.getChild("tooth23");
        this.rightleg3=root.getChild("rightleg3");
        this.rclaw2=root.getChild("rclaw2");
        this.rclaw4=root.getChild("rclaw4");
        this.rclaw1=root.getChild("rclaw1");
        this.rclaw5=root.getChild("rclaw5");
        this.rclaw7=root.getChild("rclaw7");
        this.rclaw3=root.getChild("rclaw3");
        this.rclaw6=root.getChild("rclaw6");
        this.neck1=root.getChild("neck1");
        this.neck2=root.getChild("neck2");
        this.tail4=root.getChild("tail4");
        this.Spike1=root.getChild("Spike1");
        this.Spike2=root.getChild("Spike2");
        this.Spike3=root.getChild("Spike3");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      float pscale = 2.0F;
      float tailspeed = 0.0F;
      float tailamp = 0.0F;
      float clawZ = 15.0F;
      float clawY = 21.0F;
      float clawZamp = 5.0F * pscale;
      float clawYamp = 2.0F * pscale;
      float pi4 = (float) (Math.PI / 4);
      f3 %= 360.0F;
      float var22;
      f3 = var22 = f3 * 0.35F;
      this.neck2.yRot = (float)Math.toRadians(f3) * 0.5F;
      this.head3.yRot = (float)Math.toRadians(f3);
      this.neck3.yRot = this.head7.yRot = this.head3.yRot;
      this.jaw1.yRot = this.jaw5.yRot = this.head3.yRot;
      this.tooth1.yRot = this.tooth2.yRot = this.tooth3.yRot = this.tooth4.yRot = this.tooth5.yRot = this.head3.yRot;
      this.tooth6.yRot = this.tooth7.yRot = this.tooth8.yRot = this.tooth9.yRot = this.tooth10.yRot = this.head3.yRot;
      this.tooth11.yRot = this.tooth12.yRot = this.tooth13.yRot = this.tooth14.yRot = this.tooth15.yRot = this.head3
         .yRot;
      this.tooth16.yRot = this.tooth17.yRot = this.tooth18.yRot = this.tooth19.yRot = this.tooth20.yRot = this.head3
         .yRot;
      this.tooth21.yRot = this.tooth22.yRot = this.tooth23.yRot = this.head3.yRot;
      if ((state.attacking ? 1 : 0) != 0) {
         newangle = cos(f2 * 0.85F * this.wingspeed) * (float) Math.PI * 0.16F;
         newangle += 0.5F;
      } else {
         newangle = f2 * 0.7F * this.wingspeed % (float) (Math.PI * 2);
         newangle = Math.abs(newangle);
         if (newangle < state.rf1) {
            state.ri1 = 0;
            if (state.random.nextInt(20) == 1) {
               state.ri1 |= 1;
            }
         }
         state.rf1 = newangle;
         if (state.ri1 != 0) {
            newangle = sin(f2 * 0.85F * this.wingspeed) * (float) Math.PI * 0.16F;
            newangle += 0.5F;
         } else {
            newangle = pi4 / 4.0F;
         }
      }
      this.jaw1.xRot = this.jaw5.xRot = newangle;
      this.tooth14.xRot = this.tooth15.xRot = newangle;
      this.tooth16.xRot = this.tooth17.xRot = this.tooth18.xRot = this.tooth19.xRot = this.tooth20.xRot = newangle;
      this.tooth21.xRot = this.tooth22.xRot = this.tooth23.xRot = newangle;
      float t1 = 0.0F;
      float t2 = 0.0F;
      if (f1 > 0.001) {
         newangle = cos(f2 * this.wingspeed / pscale);
         t1 = sin(f2 * this.wingspeed / pscale);
      } else {
         newangle = 0.0F;
         t1 = 0.0F;
         t2 = 0.0F;
      }
      if (t1 > 0.0F) {
         t2 = t1 * clawYamp * f1;
         this.lclaw1.y = clawY - t2;
      } else {
         this.lclaw1.y = clawY;
      }
      this.lclaw1.z = clawZ + clawZamp * newangle * f1;
      this.lclaw2.z = this.lclaw3.z = this.lclaw4.z = this.lclaw5.z = this.lclaw6.z = this.lclaw7.z = this.lclaw1
         .z;
      this.lclaw2.y = this.lclaw3.y = this.lclaw4.y = this.lclaw5.y = this.lclaw6.y = this.lclaw7.y = this.lclaw1
         .y;
      this.leftleg3.z = this.lclaw1.z;
      this.leftleg3.y = this.lclaw1.y;
      this.leftleg3.xRot = -0.523F + newangle * (float) Math.PI * 0.15F * f1;
      this.leftleg1.xRot = -0.576F + newangle * (float) Math.PI * 0.06F * f1;
      this.leftleg2.xRot = 0.977F + newangle * (float) Math.PI * 0.06F * f1;
      this.leftleg1.y = this.leftleg2.y = this.leftleg3.y - (float)Math.cos(this.leftleg3.xRot) * 17.0F;
      this.leftleg1.z = this.leftleg2.z = this.leftleg3.z - (float)Math.sin(this.leftleg3.xRot) * 17.0F;
      t1 = 0.0F;
      t2 = 0.0F;
      if (f1 > 0.001) {
         newangle = cos(f2 * this.wingspeed / pscale + pi4 * 4.0F);
         t1 = sin(f2 * this.wingspeed / pscale + pi4 * 4.0F);
      } else {
         newangle = 0.0F;
         t1 = 0.0F;
         t2 = 0.0F;
      }
      if (t1 > 0.0F) {
         t2 = t1 * clawYamp * f1;
         this.rclaw1.y = clawY - t2;
      } else {
         this.rclaw1.y = clawY;
      }
      this.rclaw1.z = clawZ + clawZamp * newangle * f1;
      this.rclaw2.z = this.rclaw3.z = this.rclaw4.z = this.rclaw5.z = this.rclaw6.z = this.rclaw7.z = this.rclaw1
         .z;
      this.rclaw2.y = this.rclaw3.y = this.rclaw4.y = this.rclaw5.y = this.rclaw6.y = this.rclaw7.y = this.rclaw1
         .y;
      this.rightleg3.z = this.rclaw1.z;
      this.rightleg3.y = this.rclaw1.y;
      this.rightleg3.xRot = -0.523F + newangle * (float) Math.PI * 0.15F * f1;
      this.rightleg1.xRot = -0.576F + newangle * (float) Math.PI * 0.06F * f1;
      this.rightleg2.xRot = 0.977F + newangle * (float) Math.PI * 0.06F * f1;
      this.rightleg1.y = this.rightleg2.y = this.rightleg3.y - (float)Math.cos(this.rightleg3.xRot) * 17.0F;
      this.rightleg1.z = this.rightleg2.z = this.rightleg3.z - (float)Math.sin(this.rightleg3.xRot) * 17.0F;
      this.lclaw2.xRot = this.lclaw3.xRot = this.lclaw4.xRot = this.lclaw5.xRot = this.lclaw6.xRot = this.lclaw7.xRot = this.lclaw1
         .xRot = 0.0F;
      this.rclaw2.xRot = this.rclaw3.xRot = this.rclaw4.xRot = this.rclaw5.xRot = this.rclaw6.xRot = this.rclaw7.xRot = this.rclaw1
         .xRot = 0.0F;
      if ((state.attacking ? 1 : 0) != 0) {
         tailspeed = 0.76F;
         tailamp = 0.25F;
      } else {
         tailspeed = 0.26F;
         tailamp = 0.08F;
      }
      this.tail3.yRot = cos(f2 * tailspeed * this.wingspeed) * (float) Math.PI * tailamp / 2.0F;
      this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 11.0F;
      this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 11.0F;
      this.tail4.yRot = cos(f2 * tailspeed * this.wingspeed) * (float) Math.PI * tailamp;
    }
}
