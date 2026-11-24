package port.orespawn;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
final class CreatureFrame extends LivingEntityRenderState {
    boolean attacking,sitting;
    float healthFraction, rf1;
    int ri1,ri2,ri3;
    long individual;
    float[][] trail;
    final java.util.Random random=new java.util.Random(0);
}
