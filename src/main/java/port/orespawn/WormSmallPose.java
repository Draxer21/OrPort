package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class WormSmallPose extends CreaturePose {
    private final ModelPart head, body, tail;
    WormSmallPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.head=root.getChild("head");
        this.body=root.getChild("body");
        this.tail=root.getChild("tail");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = cos(f2 * 0.55F) * (float) Math.PI * 0.15F;
      this.tail.xRot = newangle;
      float d1 = (float)(Math.sin(newangle) * 5.0);
      float d2 = (float)(Math.cos(newangle) * 5.0);
      this.body.z = this.tail.z - d1;
      newangle = cos(f2 * 0.35F) * (float) Math.PI * 0.1F;
      this.tail.zRot = newangle;
      float d3 = (float)(Math.cos(newangle) * d2);
      float d4 = (float)(Math.sin(newangle) * d2);
      this.body.x = this.tail.x + d4;
      this.body.y = (float)(this.tail.y - 5.0 + (5.0 - d3));
      newangle = cos(f2 * 0.45F) * (float) Math.PI * 0.15F;
      this.body.xRot = newangle;
      d1 = (float)(Math.sin(newangle) * 5.0);
      d2 = (float)(Math.cos(newangle) * 5.0);
      this.head.z = this.body.z - d1;
      newangle = cos(f2 * 0.25F) * (float) Math.PI * 0.1F;
      this.body.zRot = newangle;
      d3 = (float)(Math.cos(newangle) * d2);
      d4 = (float)(Math.sin(newangle) * d2);
      this.head.x = this.body.x + d4;
      this.head.y = (float)(this.body.y - 5.0 + (5.0 - d3));
      this.head.xRot = 0.62F + cos(f2 * 0.65F) * (float) Math.PI * 0.15F;
      this.head.zRot = cos(f2 * 0.3F) * (float) Math.PI * 0.05F;
    }
}
