package port.orespawn;

import net.minecraft.client.model.geom.ModelPart;

final class DungeonBeastPose extends CreaturePose {
    private final ModelPart leftLeg, rightLeg, leftJaw, rightJaw;
    private final ModelPart[] tail = new ModelPart[7];
    private final ModelPart[] dorsalSpines = new ModelPart[3];

    DungeonBeastPose(ModelPart root) {
        super(root, 0.62f, 1);
        var body = root.getChild("beast");
        leftLeg = body.getChild("leftLeg");
        rightLeg = body.getChild("rightLeg");
        leftJaw = body.getChild("leftJaw");
        rightJaw = body.getChild("rightJaw");
        var parent = body;
        for (int i = 0; i < tail.length; i++) {
            tail[i] = parent.getChild("segment" + (i + 1));
            parent = tail[i];
        }
        for (int i = 0; i < dorsalSpines.length; i++)
            dorsalSpines[i] = body.getChild("bodys" + (i + 1));
    }

    @Override void animate(CreatureFrame state, float walk, float amplitude, float age, float yaw, float pitch) {
        float phase = age * wingspeed;
        float stride = cos(phase * 1.4f) * 0.69f * amplitude;
        rightLeg.zRot += stride;
        leftLeg.zRot -= stride;
        float sweep = cos(phase * 0.75f) * (state.attacking ? 1.25f : amplitude);
        for (var segment : tail) segment.yRot += sweep * 0.15f;
        float bite = (1 + cos(phase * 2)) * (state.attacking ? 0.24f : 0.035f);
        leftJaw.yRot -= bite;
        rightJaw.yRot += bite;
        for (int i = 0; i < dorsalSpines.length; i++)
            dorsalSpines[i].xRot += cos(phase * 0.5f - i * 0.4f) * 0.22f;
    }
}
