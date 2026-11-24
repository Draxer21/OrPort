package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class DragonflyPose extends CreaturePose {
    private final ModelPart Shape1, lfwing, Shape3, Shape4, Shape5, rjaw, ljaw, tail1, tail2, Shape10, Shape11, Shape12, Shape13, Shape14, Shape15, Shape16, Shape17, Shape18, Shape19, Shape20, Shape21, Shape22, Shape23, lrwing, rfwing, rrwing;
    DragonflyPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Shape1=root.getChild("Shape1");
        this.lfwing=root.getChild("lfwing");
        this.Shape3=root.getChild("Shape3");
        this.Shape4=root.getChild("Shape4");
        this.Shape5=root.getChild("Shape5");
        this.rjaw=root.getChild("rjaw");
        this.ljaw=root.getChild("ljaw");
        this.tail1=root.getChild("tail1");
        this.tail2=root.getChild("tail2");
        this.Shape10=root.getChild("Shape10");
        this.Shape11=root.getChild("Shape11");
        this.Shape12=root.getChild("Shape12");
        this.Shape13=root.getChild("Shape13");
        this.Shape14=root.getChild("Shape14");
        this.Shape15=root.getChild("Shape15");
        this.Shape16=root.getChild("Shape16");
        this.Shape17=root.getChild("Shape17");
        this.Shape18=root.getChild("Shape18");
        this.Shape19=root.getChild("Shape19");
        this.Shape20=root.getChild("Shape20");
        this.Shape21=root.getChild("Shape21");
        this.Shape22=root.getChild("Shape22");
        this.Shape23=root.getChild("Shape23");
        this.lrwing=root.getChild("lrwing");
        this.rfwing=root.getChild("rfwing");
        this.rrwing=root.getChild("rrwing");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      newangle = cos(f2 * 1.3F * this.wingspeed) * (float) Math.PI * 0.25F;
      this.lfwing.zRot = newangle;
      this.rfwing.zRot = -newangle;
      this.lrwing.zRot = newangle + 3.14F;
      this.rrwing.zRot = -newangle + 3.14F;
      newangle = cos(f2 * 0.3F * this.wingspeed) * (float) Math.PI * 0.1F;
      this.ljaw.xRot = newangle;
      this.rjaw.xRot = -newangle;
    }
}
