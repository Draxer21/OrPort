package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class AlosaurusPose extends CreaturePose {
    private final ModelPart Shape18, Shape19, Shape20, Shape21, Shape1, Shape2, Shape3, Shape4, Shape5, Shape6, jaw, leftleg, leftleg2, leftleg3, Shape11, rightleg, rightleg2, rightleg3, leftleg4, rightleg4, Shape17;
    AlosaurusPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Shape18=root.getChild("Shape18");
        this.Shape19=root.getChild("Shape19");
        this.Shape20=root.getChild("Shape20");
        this.Shape21=root.getChild("Shape21");
        this.Shape1=root.getChild("Shape1");
        this.Shape2=root.getChild("Shape2");
        this.Shape3=root.getChild("Shape3");
        this.Shape4=root.getChild("Shape4");
        this.Shape5=root.getChild("Shape5");
        this.Shape6=root.getChild("Shape6");
        this.jaw=root.getChild("jaw");
        this.leftleg=root.getChild("leftleg");
        this.leftleg2=root.getChild("leftleg2");
        this.leftleg3=root.getChild("leftleg3");
        this.Shape11=root.getChild("Shape11");
        this.rightleg=root.getChild("rightleg");
        this.rightleg2=root.getChild("rightleg2");
        this.rightleg3=root.getChild("rightleg3");
        this.leftleg4=root.getChild("leftleg4");
        this.rightleg4=root.getChild("rightleg4");
        this.Shape17=root.getChild("Shape17");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.25F * f1;
      } else {
         newangle = 0.0F;
      }
      this.rightleg.xRot = -0.174F + newangle;
      this.rightleg2.xRot = 0.506F + newangle;
      this.rightleg3.xRot = -0.401F + newangle;
      this.rightleg4.xRot = newangle;
      this.leftleg.xRot = -0.174F - newangle;
      this.leftleg2.xRot = 0.506F - newangle;
      this.leftleg3.xRot = -0.401F - newangle;
      this.leftleg4.xRot = -newangle;
      if ((state.attacking ? 1 : 0) != 0) {
         this.jaw.xRot = 0.52F + cos(f2 * 0.45F) * (float) Math.PI * 0.18F;
      } else {
         this.jaw.xRot = 0.1F;
      }
      this.Shape17.xRot = -0.523F + cos(f2 * 0.1F) * (float) Math.PI * 0.05F;
      this.Shape11.xRot = -0.523F + cos(f2 * 0.1F) * (float) Math.PI * 0.05F;
    }
}
