package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class WormMediumPose extends CreaturePose {
    private final ModelPart head, body, tail, tooth1, tooth2, tooth3, tooth4, head2;
    WormMediumPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.head=root.getChild("head");
        this.body=root.getChild("body");
        this.tail=root.getChild("tail");
        this.tooth1=root.getChild("tooth1");
        this.tooth2=root.getChild("tooth2");
        this.tooth3=root.getChild("tooth3");
        this.tooth4=root.getChild("tooth4");
        this.head2=root.getChild("head2");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = cos(f2 * 0.45F) * (float) Math.PI * 0.1F;
      this.tail.xRot = newangle;
      float d1 = (float)(Math.sin(newangle) * 12.0);
      float d2 = (float)(Math.cos(newangle) * 12.0);
      this.body.z = this.tail.z - d1;
      newangle = cos(f2 * 0.25F) * (float) Math.PI * 0.08F;
      this.tail.zRot = newangle;
      float d3 = (float)(Math.cos(newangle) * d2);
      float d4 = (float)(Math.sin(newangle) * d2);
      this.body.x = this.tail.x + d4;
      this.body.y = (float)(this.tail.y - 12.0 + (12.0 - d3));
      newangle = cos(f2 * 0.35F) * (float) Math.PI * 0.1F;
      this.body.xRot = newangle;
      d1 = (float)(Math.sin(newangle) * 12.0);
      d2 = (float)(Math.cos(newangle) * 12.0);
      this.head2.z = this.head.z = this.body.z - d1;
      newangle = cos(f2 * 0.15F) * (float) Math.PI * 0.07F;
      this.body.zRot = newangle;
      d3 = (float)(Math.cos(newangle) * d2);
      d4 = (float)(Math.sin(newangle) * d2);
      this.head2.x = this.head.x = this.body.x + d4;
      this.head2.y = this.head.y = (float)(this.body.y - 12.0 + (12.0 - d3));
      this.head2.xRot = this.head.xRot = 0.62F + cos(f2 * 0.55F) * (float) Math.PI * 0.15F;
      this.head2.zRot = this.head.zRot = cos(f2 * 0.25F) * (float) Math.PI * 0.05F;
      newangle = this.head.xRot;
      this.tooth1.xRot = this.tooth2.xRot = this.tooth3.xRot = this.tooth4.xRot = newangle;
      d1 = (float)(Math.sin(newangle) * 12.0);
      d2 = (float)(Math.cos(newangle) * 12.0);
      this.tooth1.z = this.tooth2.z = this.tooth3.z = this.tooth4.z = this.head.z - d1;
      newangle = this.head.zRot;
      this.tooth1.zRot = this.tooth2.zRot = this.tooth3.zRot = this.tooth4.zRot = newangle;
      d3 = (float)(Math.cos(newangle) * d2);
      d4 = (float)(Math.sin(newangle) * d2);
      this.tooth1.x = this.tooth2.x = this.tooth3.x = this.tooth4.x = this.head.x + d4;
      this.tooth1.y = this.tooth2.y = this.tooth3.y = this.tooth4.y = (float)(
         this.head.y - 12.0 + (12.0 - d3)
      );
      this.tooth1.z++;
      this.tooth2.z--;
      this.tooth1.xRot = this.tooth1.xRot - 0.4F - cos(f2 * 0.55F) * (float) Math.PI * 0.15F;
      this.tooth2.xRot = this.tooth2.xRot + 0.4F + cos(f2 * 0.55F) * (float) Math.PI * 0.15F;
      this.tooth3.x++;
      this.tooth4.x--;
      this.tooth3.zRot = this.tooth3.zRot + 0.4F + cos(f2 * 0.55F) * (float) Math.PI * 0.15F;
      this.tooth4.zRot = this.tooth4.zRot - 0.4F - cos(f2 * 0.55F) * (float) Math.PI * 0.15F;
    }
}
