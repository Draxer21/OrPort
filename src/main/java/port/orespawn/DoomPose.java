package port.orespawn;
import net.minecraft.client.model.geom.ModelPart;
final class DoomPose extends CreaturePose {
    private final ModelPart[] a=new ModelPart[100], b=new ModelPart[100];
    DoomPose(ModelPart root){super(root,1,2);
        for(int i=0;i<a.length;i++){a[i]=root.getChild(i==0?"body1":"ringA"+i);b[i]=root.getChild(i==0?"body2":"ringB"+i);}
    }
    @Override void animate(CreatureFrame state,float walk,float speed,float age,float yaw,float pitch){
        for(int i=0;i<a.length;i++){
            float wave=age*0.08f-i*0.18f;
            float x=state.trail==null?sin(wave)*Math.min(12,i):state.trail[i][0];
            float y=state.trail==null?cos(wave)*Math.min(8,i):state.trail[i][1];
            float z=12+(state.trail==null?i*2.4f:state.trail[i][2]);
            a[i].x+=x;b[i].x+=x;a[i].y+=y;b[i].y+=y;a[i].z+=z;b[i].z+=z;
            float radius=(2-cos((float)Math.toRadians(i*3)))*Math.min(1f,(100-i)/75f);
            a[i].xScale=b[i].xScale=a[i].yScale=b[i].yScale=radius;
        }
    }
}
