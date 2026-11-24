package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class CryolophosaurusPose extends CreaturePose {
    private final ModelPart Shape1, Shape2, Shape3, jaw, Shape5, Shape6, Shape7, Shape8, Shape9, rightleg, Shape11, rightleg2, rightleg3, rightleg4, leftleg, Shape16, Shape17, leftleg2, leftleg3, leftleg4;
    CryolophosaurusPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Shape1=root.getChild("Shape1");
        this.Shape2=root.getChild("Shape2");
        this.Shape3=root.getChild("Shape3");
        this.jaw=root.getChild("jaw");
        this.Shape5=root.getChild("Shape5");
        this.Shape6=root.getChild("Shape6");
        this.Shape7=root.getChild("Shape7");
        this.Shape8=root.getChild("Shape8");
        this.Shape9=root.getChild("Shape9");
        this.rightleg=root.getChild("rightleg");
        this.Shape11=root.getChild("Shape11");
        this.rightleg2=root.getChild("rightleg2");
        this.rightleg3=root.getChild("rightleg3");
        this.rightleg4=root.getChild("rightleg4");
        this.leftleg=root.getChild("leftleg");
        this.Shape16=root.getChild("Shape16");
        this.Shape17=root.getChild("Shape17");
        this.leftleg2=root.getChild("leftleg2");
        this.leftleg3=root.getChild("leftleg3");
        this.leftleg4=root.getChild("leftleg4");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.25F * f1;
      } else {
         newangle = 0.0F;
      }
      this.rightleg.xRot = -0.2792527F + newangle;
      this.rightleg2.xRot = 0.384F + newangle;
      this.rightleg3.xRot = -0.68F + newangle;
      this.rightleg4.xRot = newangle;
      this.leftleg.xRot = -0.2792527F - newangle;
      this.leftleg2.xRot = 0.384F - newangle;
      this.leftleg3.xRot = -0.68F - newangle;
      this.leftleg4.xRot = -newangle;
      this.jaw.xRot = -1.15F + cos(f2 * 0.28F) * (float) Math.PI * 0.1F;
    }
}
