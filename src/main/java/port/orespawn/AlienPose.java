package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class AlienPose extends CreaturePose {
    private final ModelPart torso, stomach, rThigh, lThigh, lShin, rShin, lShin1, rShin1, lFoot, rFoot, neck, fan, tail2, tail3, tail4, tail5, tail1, fanl1, fanr1, fanl2, fanr2, fanl3, fanr3, fanl4, fanr4, fanl5, fanr5, fanl6, fanr6, spike4, spike5, spike3, fanl7, fanr7, head, head1, jaw1, head2, jaw2, fang1, fang2, fang3, fang4, spike2, spike1, arml1, armr1, arml2, armr2, clawr1, clawr2, clawr3, clawl2, clawl3, clawl1;
    AlienPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.torso=root.getChild("torso");
        this.stomach=root.getChild("stomach");
        this.rThigh=root.getChild("rThigh");
        this.lThigh=root.getChild("lThigh");
        this.lShin=root.getChild("lShin");
        this.rShin=root.getChild("rShin");
        this.lShin1=root.getChild("lShin1");
        this.rShin1=root.getChild("rShin1");
        this.lFoot=root.getChild("lFoot");
        this.rFoot=root.getChild("rFoot");
        this.neck=root.getChild("neck");
        this.fan=root.getChild("fan");
        this.tail2=root.getChild("tail2");
        this.tail3=root.getChild("tail3");
        this.tail4=root.getChild("tail4");
        this.tail5=root.getChild("tail5");
        this.tail1=root.getChild("tail1");
        this.fanl1=root.getChild("fanl1");
        this.fanr1=root.getChild("fanr1");
        this.fanl2=root.getChild("fanl2");
        this.fanr2=root.getChild("fanr2");
        this.fanl3=root.getChild("fanl3");
        this.fanr3=root.getChild("fanr3");
        this.fanl4=root.getChild("fanl4");
        this.fanr4=root.getChild("fanr4");
        this.fanl5=root.getChild("fanl5");
        this.fanr5=root.getChild("fanr5");
        this.fanl6=root.getChild("fanl6");
        this.fanr6=root.getChild("fanr6");
        this.spike4=root.getChild("spike4");
        this.spike5=root.getChild("spike5");
        this.spike3=root.getChild("spike3");
        this.fanl7=root.getChild("fanl7");
        this.fanr7=root.getChild("fanr7");
        this.head=root.getChild("head");
        this.head1=root.getChild("head1");
        this.jaw1=root.getChild("jaw1");
        this.head2=root.getChild("head2");
        this.jaw2=root.getChild("jaw2");
        this.fang1=root.getChild("fang1");
        this.fang2=root.getChild("fang2");
        this.fang3=root.getChild("fang3");
        this.fang4=root.getChild("fang4");
        this.spike2=root.getChild("spike2");
        this.spike1=root.getChild("spike1");
        this.arml1=root.getChild("arml1");
        this.armr1=root.getChild("armr1");
        this.arml2=root.getChild("arml2");
        this.armr2=root.getChild("armr2");
        this.clawr1=root.getChild("clawr1");
        this.clawr2=root.getChild("clawr2");
        this.clawr3=root.getChild("clawr3");
        this.clawl2=root.getChild("clawl2");
        this.clawl3=root.getChild("clawl3");
        this.clawl1=root.getChild("clawl1");
    }
    private void doLeftLeg(float angle) {
      this.lFoot.xRot = angle;
      this.lShin.xRot = angle - 0.4F;
      this.lShin1.xRot = angle - 0.8F;
      this.lThigh.xRot = angle - 0.8F;
    }
    private void doRightLeg(float angle) {
      this.rFoot.xRot = angle;
      this.rShin.xRot = angle - 0.4F;
      this.rShin1.xRot = angle - 0.8F;
      this.rThigh.xRot = angle - 0.8F;
    }
    private void doJaw(float angle) {
      this.jaw1.xRot = Math.abs(angle);
      this.jaw2.xRot = this.jaw1.xRot;
    }
    private void doTail(float angle) {
      this.tail1.yRot = angle * 0.25F;
      this.spike1.yRot = this.tail1.yRot;
      this.tail2.yRot = angle * 0.5F;
      this.tail2.z = this.tail1.z + (float)Math.cos(this.tail1.yRot) * 10.0F;
      this.tail2.x = this.tail1.x + (float)Math.sin(this.tail1.yRot) * 10.0F;
      this.spike2.yRot = this.tail2.yRot;
      this.spike2.z = this.tail2.z;
      this.spike2.x = this.tail2.x;
      this.tail3.yRot = angle * 0.8F;
      this.tail3.z = this.tail2.z + (float)Math.cos(this.tail2.yRot) * 10.0F;
      this.tail3.x = this.tail2.x + (float)Math.sin(this.tail2.yRot) * 10.0F;
      this.spike3.yRot = this.tail3.yRot;
      this.spike3.z = this.tail3.z;
      this.spike3.x = this.tail3.x;
      this.tail4.yRot = angle * 1.25F;
      this.tail4.z = this.tail3.z + (float)Math.cos(this.tail3.yRot) * 10.0F;
      this.tail4.x = this.tail3.x + (float)Math.sin(this.tail3.yRot) * 10.0F;
      this.spike4.yRot = this.tail4.yRot + 0.52F;
      this.spike4.z = this.tail4.z;
      this.spike4.x = this.tail4.x;
      this.spike5.yRot = this.tail4.yRot - 0.52F;
      this.spike5.z = this.tail4.z;
      this.spike5.x = this.tail4.x;
      this.tail5.yRot = angle * 1.5F;
      this.tail5.z = this.tail4.z + (float)Math.cos(this.tail4.yRot) * 10.0F;
      this.tail5.x = this.tail4.x + (float)Math.sin(this.tail4.yRot) * 10.0F;
    }
    private void doLeftClaw(float angle) {
      this.arml1.yRot = -0.52F + Math.abs(angle * 2.0F);
      this.arml2.z = this.arml1.z - (float)Math.sin(this.arml1.yRot) * 9.0F;
      this.arml2.x = this.arml1.x + (float)Math.cos(this.arml1.yRot) * 9.0F;
      this.arml2.yRot = 0.855F + Math.abs(angle);
      this.clawl1.z = this.arml2.z - (float)Math.sin(this.arml2.yRot) * 14.0F;
      this.clawl1.x = this.arml2.x + (float)Math.cos(this.arml2.yRot) * 14.0F;
      this.clawl1.yRot = 2.7F + Math.abs(angle * 4.0F);
      this.clawl2.z = this.clawl1.z;
      this.clawl2.x = this.clawl1.x;
      this.clawl2.yRot = 2.27F + Math.abs(angle * 4.0F);
      this.clawl3.z = this.clawl1.z;
      this.clawl3.x = this.clawl1.x;
      this.clawl3.yRot = 2.7F + Math.abs(angle * 4.0F);
    }
    private void doRightClaw(float angle) {
      this.armr1.yRot = -2.61F - Math.abs(angle * 2.0F);
      this.armr2.z = this.armr1.z - (float)Math.sin(this.armr1.yRot) * 9.0F;
      this.armr2.x = this.armr1.x + (float)Math.cos(this.armr1.yRot) * 9.0F;
      this.armr2.yRot = 2.27F - Math.abs(angle);
      this.clawr1.z = this.armr2.z - (float)Math.sin(this.armr2.yRot) * 14.0F;
      this.clawr1.x = this.armr2.x + (float)Math.cos(this.armr2.yRot) * 14.0F;
      this.clawr1.yRot = 0.436F - Math.abs(angle * 4.0F);
      this.clawr2.z = this.clawr1.z;
      this.clawr2.x = this.clawr1.x;
      this.clawr2.yRot = 0.87F - Math.abs(angle * 4.0F);
      this.clawr3.z = this.clawr1.z;
      this.clawr3.x = this.clawr1.x;
      this.clawr3.yRot = 0.436F - Math.abs(angle * 4.0F);
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      float nextangle = 0.0F;
      newangle = cos(f2 * 4.0F * this.wingspeed) * (float) Math.PI * 0.5F * f1;
      this.doLeftLeg(newangle);
      this.doRightLeg(-newangle);
      if ((state.attacking ? 1 : 0) == 0) {
         this.fan.zRot = 0.0F;
         this.fanl1.zRot = 0.0F;
         this.fanl2.zRot = 0.0F;
         this.fanl3.zRot = 0.0F;
         this.fanl4.zRot = 0.0F;
         this.fanl5.zRot = 0.0F;
         this.fanl6.zRot = 0.0F;
         this.fanl7.zRot = 0.0F;
         this.fanr1.zRot = 0.0F;
         this.fanr2.zRot = 0.0F;
         this.fanr3.zRot = 0.0F;
         this.fanr4.zRot = 0.0F;
         this.fanr5.zRot = 0.0F;
         this.fanr6.zRot = 0.0F;
         this.fanr7.zRot = 0.0F;
         this.fan.xRot = -1.85F;
         this.fanl1.xRot = -1.85F;
         this.fanl2.xRot = -1.85F;
         this.fanl3.xRot = -1.85F;
         this.fanl4.xRot = -1.85F;
         this.fanl5.xRot = -1.85F;
         this.fanl6.xRot = -1.85F;
         this.fanl7.xRot = -1.85F;
         this.fanr1.xRot = -1.85F;
         this.fanr2.xRot = -1.85F;
         this.fanr3.xRot = -1.85F;
         this.fanr4.xRot = -1.85F;
         this.fanr5.xRot = -1.85F;
         this.fanr6.xRot = -1.85F;
         this.fanr7.xRot = -1.85F;
      } else {
         float pi6 = (float) (Math.PI / 6);
         float fanspeed = 1.22F;
         float fanamp = 0.1F;
         this.fan.xRot = cos(f2 * fanspeed * this.wingspeed) * (float) Math.PI * fanamp;
         this.fanl1.xRot = cos(f2 * fanspeed * this.wingspeed - 1.0F * pi6) * (float) Math.PI * fanamp;
         this.fanl2.xRot = cos(f2 * fanspeed * this.wingspeed - 2.0F * pi6) * (float) Math.PI * fanamp;
         this.fanl3.xRot = cos(f2 * fanspeed * this.wingspeed - 3.0F * pi6) * (float) Math.PI * fanamp;
         this.fanl4.xRot = cos(f2 * fanspeed * this.wingspeed - 4.0F * pi6) * (float) Math.PI * fanamp;
         this.fanl5.xRot = cos(f2 * fanspeed * this.wingspeed - 5.0F * pi6) * (float) Math.PI * fanamp;
         this.fanl6.xRot = cos(f2 * fanspeed * this.wingspeed - 6.0F * pi6) * (float) Math.PI * fanamp;
         this.fanl7.xRot = cos(f2 * fanspeed * this.wingspeed - 7.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr1.xRot = cos(f2 * fanspeed * this.wingspeed - 1.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr2.xRot = cos(f2 * fanspeed * this.wingspeed - 2.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr3.xRot = cos(f2 * fanspeed * this.wingspeed - 3.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr4.xRot = cos(f2 * fanspeed * this.wingspeed - 4.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr5.xRot = cos(f2 * fanspeed * this.wingspeed - 5.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr6.xRot = cos(f2 * fanspeed * this.wingspeed - 6.0F * pi6) * (float) Math.PI * fanamp;
         this.fanr7.xRot = cos(f2 * fanspeed * this.wingspeed - 7.0F * pi6) * (float) Math.PI * fanamp;
         this.fan.zRot = 0.0F;
         this.fanl1.zRot = 0.261F;
         this.fanl2.zRot = 0.523F;
         this.fanl3.zRot = 0.785F;
         this.fanl4.zRot = 1.047F;
         this.fanl5.zRot = 1.309F;
         this.fanl6.zRot = 1.571F;
         this.fanl7.zRot = 1.832F;
         this.fanr1.zRot = -0.261F;
         this.fanr2.zRot = -0.523F;
         this.fanr3.zRot = -0.785F;
         this.fanr4.zRot = -1.047F;
         this.fanr5.zRot = -1.309F;
         this.fanr6.zRot = -1.571F;
         this.fanr7.zRot = -1.832F;
      }
      this.neck.yRot = (float)Math.toRadians(f3) * 0.35F;
      this.head.yRot = (float)Math.toRadians(f3) * 0.75F;
      this.head.z = this.neck.z - (float)Math.cos(this.neck.yRot) * 3.0F;
      this.head.x = this.neck.x + (float)Math.sin(this.neck.yRot) * 3.0F;
      this.head1.yRot = this.head.yRot;
      this.head1.z = this.head.z;
      this.head1.x = this.head.x;
      this.head2.yRot = this.head.yRot;
      this.head2.z = this.head.z;
      this.head2.x = this.head.x;
      this.fang1.yRot = this.head.yRot;
      this.fang1.z = this.head.z;
      this.fang1.x = this.head.x;
      this.fang2.yRot = this.head.yRot;
      this.fang2.z = this.head.z;
      this.fang2.x = this.head.x;
      this.fang3.yRot = this.head.yRot;
      this.fang3.z = this.head.z;
      this.fang3.x = this.head.x;
      this.fang4.yRot = this.head.yRot;
      this.fang4.z = this.head.z;
      this.fang4.x = this.head.x;
      this.jaw1.yRot = this.head.yRot;
      this.jaw1.z = this.head.z - (float)Math.cos(this.head.yRot) * 8.0F;
      this.jaw1.x = this.head.x - (float)Math.sin(this.head.yRot) * 8.0F;
      this.jaw2.yRot = this.jaw1.yRot;
      this.jaw2.z = this.jaw1.z;
      this.jaw2.x = this.jaw1.x;
      newangle = cos(f2 * 3.5F * this.wingspeed) * (float) Math.PI * 0.5F;
      nextangle = cos((f2 + 0.2F) * 3.5F * this.wingspeed) * (float) Math.PI * 0.5F;
      if (nextangle > 0.0F && newangle < 0.0F) {
         if ((state.attacking ? 1 : 0) == 0) {
            state.ri1 = state.random.nextInt(15);
            state.ri2 = state.random.nextInt(15);
            state.ri3 = state.random.nextInt(15);
         } else {
            state.ri1 = state.random.nextInt(4);
            state.ri2 = state.random.nextInt(2);
            state.ri3 = 1;
         }
      }
      if (state.ri2 == 1) {
         this.doTail(newangle);
      } else {
         newangle = cos(f2 * this.wingspeed) * (float) Math.PI * 0.05F;
         this.doTail(newangle);
      }
      if (state.ri3 == 1) {
         newangle = cos(f2 * 3.5F * this.wingspeed) * (float) Math.PI * 0.35F;
         this.doJaw(newangle);
      } else {
         newangle = cos(f2 * this.wingspeed) * (float) Math.PI * 0.02F;
         this.doJaw(newangle);
      }
      newangle = cos(f2 * this.wingspeed * 3.5F) * (float) Math.PI * 0.2F;
      if (state.ri1 != 1 && state.ri1 != 3) {
         newangle = cos(f2 * this.wingspeed) * (float) Math.PI * 0.03F;
         this.doLeftClaw(newangle);
      } else {
         this.doLeftClaw(newangle);
      }
      if (state.ri1 != 2 && state.ri1 != 3) {
         newangle = cos(f2 * this.wingspeed) * (float) Math.PI * 0.03F;
         this.doRightClaw(-newangle);
      } else {
         this.doRightClaw(-newangle);
      }
    }
}
