package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class MosquitoPose extends CreaturePose {
    private final ModelPart body, leftwing1, rightwing1, leftwing2, rightwing2;
    MosquitoPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.body=root.getChild("body");
        this.leftwing1=root.getChild("leftwing1");
        this.rightwing1=root.getChild("rightwing1");
        this.leftwing2=root.getChild("leftwing2");
        this.rightwing2=root.getChild("rightwing2");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      this.rightwing1.zRot = cos(f2 * 3.0F) * (float) Math.PI * 0.25F;
      this.rightwing2.zRot = this.rightwing1.zRot;
      this.leftwing1.zRot = -this.rightwing1.zRot;
      this.leftwing2.zRot = -this.rightwing1.zRot;
    }
}
