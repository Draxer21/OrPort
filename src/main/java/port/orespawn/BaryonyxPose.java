package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;

final class BaryonyxPose extends CreaturePose {
    private final ModelPart Shape27, Shape28, Shape29, Shape30, Shape31, Shape32, Shape33, Shape34, Shape35, Shape36, Shape37, Shape38, Shape39, Shape40, Shape41, Shape42, Shape43, Shape44, Shape45, Shape46, Shape47, Shape48, Shape49, Shape50, Shape51, Shape1, Shape2, Shape3, Shape4, Shape5, Shape6, Shape7, Shape8, Shape9, Shape10, Shape11, Shape12, Shape13, Shape14, Shape15, Shape16, Shape17, Shape18, Shape19, Shape20, Shape21, Shape22, Shape23, Shape24, Shape25, Shape26, Shape52;
    BaryonyxPose(ModelPart root,float speed,float scale) {
        super(root,speed,scale);
        this.Shape27=root.getChild("Shape27");
        this.Shape28=root.getChild("Shape28");
        this.Shape29=root.getChild("Shape29");
        this.Shape30=root.getChild("Shape30");
        this.Shape31=root.getChild("Shape31");
        this.Shape32=root.getChild("Shape32");
        this.Shape33=root.getChild("Shape33");
        this.Shape34=root.getChild("Shape34");
        this.Shape35=root.getChild("Shape35");
        this.Shape36=root.getChild("Shape36");
        this.Shape37=root.getChild("Shape37");
        this.Shape38=root.getChild("Shape38");
        this.Shape39=root.getChild("Shape39");
        this.Shape40=root.getChild("Shape40");
        this.Shape41=root.getChild("Shape41");
        this.Shape42=root.getChild("Shape42");
        this.Shape43=root.getChild("Shape43");
        this.Shape44=root.getChild("Shape44");
        this.Shape45=root.getChild("Shape45");
        this.Shape46=root.getChild("Shape46");
        this.Shape47=root.getChild("Shape47");
        this.Shape48=root.getChild("Shape48");
        this.Shape49=root.getChild("Shape49");
        this.Shape50=root.getChild("Shape50");
        this.Shape51=root.getChild("Shape51");
        this.Shape1=root.getChild("Shape1");
        this.Shape2=root.getChild("Shape2");
        this.Shape3=root.getChild("Shape3");
        this.Shape4=root.getChild("Shape4");
        this.Shape5=root.getChild("Shape5");
        this.Shape6=root.getChild("Shape6");
        this.Shape7=root.getChild("Shape7");
        this.Shape8=root.getChild("Shape8");
        this.Shape9=root.getChild("Shape9");
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
        this.Shape24=root.getChild("Shape24");
        this.Shape25=root.getChild("Shape25");
        this.Shape26=root.getChild("Shape26");
        this.Shape52=root.getChild("Shape52");
    }
    @Override void animate(CreatureFrame state,float f,float f1,float f2,float f3,float f4) {
      float newangle = 0.0F;
      if (f1 > 0.1) {
         newangle = cos(f2 * 0.4F * this.wingspeed) * (float) Math.PI * 0.15F * f1;
      } else {
         newangle = 0.0F;
      }
      this.Shape24.xRot = newangle;
      this.Shape25.xRot = -0.17F + newangle;
      this.Shape26.xRot = newangle;
      this.Shape13.xRot = -newangle;
      this.Shape15.xRot = -0.17F - newangle;
      this.Shape17.xRot = -newangle;
      newangle = cos(f2 * 0.7F * this.wingspeed) * (float) Math.PI * 0.25F;
      this.Shape21.zRot = newangle;
      this.Shape16.zRot = -newangle;
    }
}
