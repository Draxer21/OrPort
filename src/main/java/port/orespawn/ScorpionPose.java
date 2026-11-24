package port.orespawn;

import net.minecraft.client.model.geom.ModelPart;

final class ScorpionPose extends CreaturePose {
    private final ModelPart[] legs = new ModelPart[8];
    private final ModelPart tail, leftClaw, rightClaw;

    ScorpionPose(ModelPart root, float speed, boolean emperor) {
        super(root, speed, 1);
        for (int i = 0; i < legs.length; i++) {
            String name = emperor ? "Leg" + (i + 1) : (i < 4 ? "lleg" : "rleg") + (1 + i % 4);
            legs[i] = root.getChild(name + "Pivot");
        }
        tail = root.getChild("tailPivot");
        leftClaw = root.getChild(emperor ? "LeftclawPivot" : "lclawPivot");
        rightClaw = root.getChild(emperor ? "RightclawPivot" : "rclawPivot");
    }

    @Override void animate(CreatureFrame state, float walk, float amplitude, float age, float yaw, float pitch) {
        for (int i = 0; i < legs.length; i++) {
            int side = i < 4 ? 1 : -1;
            float phase = age * 2 * wingspeed - (i % 4) * 1.5708f;
            legs[i].yRot = side * cos(phase) * .377f * amplitude;
            legs[i].zRot = side * Math.max(0, sin(phase)) * .22f * amplitude;
        }
        float strike = state.attacking ? (1 + sin(age * .7f)) * .5f : 0;
        tail.xRot = .045f * sin(age * .2f) - strike * .55f;
        leftClaw.yRot = .22f * strike;
        rightClaw.yRot = -leftClaw.yRot;
    }
}
