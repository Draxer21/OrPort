package port.orespawn;

import net.minecraft.client.model.geom.ModelPart;

/**
 * Animación de los dos escorpiones traducida de ModelScorpion y ModelEmperorScorpion (1.7.10).
 * El clásico coloca cada pieza en coordenadas absolutas del modelo y encadena la cola y las
 * pinzas: cada segmento se sitúa al final del anterior. Aquí las piezas cuelgan de pivotes, así que
 * {@link Seg} traduce esas coordenadas absolutas a coordenadas relativas al pivote.
 */
final class ScorpionPose extends CreaturePose {
    private static final float HALF_PI = 1.570795f, PI = 3.1415927f;

    /** Pieza hija de un pivote, manipulada con las coordenadas absolutas del modelo clásico. */
    private static final class Seg {
        final ModelPart part;
        final float px, py, pz, restX, restY, restZ;
        Seg(ModelPart pivot, String name) {
            part = pivot.getChild(name);
            px = pivot.x; py = pivot.y; pz = pivot.z;
            restX = px + part.x; restY = py + part.y; restZ = pz + part.z;
        }
        float x() { return px + part.x; }
        float y() { return py + part.y; }
        float z() { return pz + part.z; }
        void x(float v) { part.x = v - px; }
        void y(float v) { part.y = v - py; }
        void z(float v) { part.z = v - pz; }
    }

    private final boolean emperor;
    private final Seg[][] legs = new Seg[8][];
    private final Seg[] tail, leftClaw, rightClaw;
    private final ModelPart leftMandible, rightMandible;

    ScorpionPose(ModelPart root, float speed, boolean emperor) {
        super(root, speed, 1);
        this.emperor = emperor;
        if (emperor) {
            for (int i = 0; i < 8; i++) {
                var pivot = root.getChild("Leg" + (i + 1) + "Pivot");
                legs[i] = new Seg[5];
                for (int s = 0; s < 5; s++) legs[i][s] = new Seg(pivot, "Leg" + (i + 1) + "Seg" + (s + 1));
            }
            var t = root.getChild("tailPivot");
            tail = new Seg[11];
            for (int s = 0; s < 8; s++) tail[s] = new Seg(t, "Tailseg" + (s + 1));
            for (int s = 0; s < 3; s++) tail[8 + s] = new Seg(t, "Stinger" + (s + 1));
            var l = root.getChild("LeftclawPivot");
            var r = root.getChild("RightclawPivot");
            leftClaw = new Seg[]{new Seg(l, "LeftArmSeg1"), new Seg(l, "LeftArmSeg2"), new Seg(l, "LeftArmSeg3"), new Seg(l, "LeftArmSeg4"), new Seg(l, "LeftPincer")};
            rightClaw = new Seg[]{new Seg(r, "RightArmSeg1"), new Seg(r, "RightArmSeg2"), new Seg(r, "RightArmSeg3"), new Seg(r, "RightArmSeg4"), new Seg(r, "RightPincer")};
            leftMandible = root.getChild("LeftManPart2");
            rightMandible = root.getChild("RightManPart2");
        } else {
            for (int i = 0; i < 8; i++) {
                String name = (i < 4 ? "lleg" : "rleg") + (1 + i % 4);
                legs[i] = new Seg[]{new Seg(root.getChild(name + "Pivot"), name)};
            }
            var t = root.getChild("tailPivot");
            tail = new Seg[6];
            for (int s = 0; s < 6; s++) tail[s] = new Seg(t, "tail" + (s + 1));
            var l = root.getChild("lclawPivot");
            var r = root.getChild("rclawPivot");
            leftClaw = new Seg[]{new Seg(l, "larm2"), new Seg(l, "larm1"), new Seg(l, "lclaw")};
            rightClaw = new Seg[]{new Seg(r, "rarm2"), new Seg(r, "rarm1"), new Seg(r, "rclaw")};
            leftMandible = rightMandible = null;
        }
    }

    @Override void animate(CreatureFrame state, float walk, float amplitude, float age, float yaw, float pitch) {
        if (emperor) emperorLegs(age, amplitude);
        else commonLegs(age, amplitude);
        if (emperor) {
            float jaw = state.attacking ? cos(age * 2.5f * wingspeed) * PI * .15f : cos(age * .5f * wingspeed) * PI * .05f;
            leftMandible.zRot = jaw;
            rightMandible.zRot = -jaw;
        }
        // El clásico sortea qué pinza y si la cola se mueven cada vez que la oscilación pasa de
        // negativa a positiva. Aquí el sorteo depende del individuo y del número de ciclo, de modo
        // que el resultado es el mismo en todos los fotogramas del ciclo sin guardar estado.
        float phase = age * 3 * wingspeed;
        float swing = cos(phase) * PI * .15f;
        long cycle = (long) Math.floor((phase - 3 * HALF_PI) / (4 * HALF_PI));
        state.random.setSeed(state.individual * 0x9E3779B97F4A7C15L + cycle);
        int ri1 = state.random.nextInt(state.attacking ? 4 : 20);
        int ri2 = state.random.nextInt(state.attacking ? 3 : 25);
        if (emperor) {
            emperorLeftClaw(ri1 == 1 || ri1 == 3 ? swing : 0);
            emperorRightClaw(ri1 == 2 || ri1 == 3 ? swing : 0);
            emperorTail(ri2 == 1 ? swing : 0);
        } else {
            commonLeftClaw(ri1 == 1 || ri1 == 3 ? swing : 0);
            commonRightClaw(ri1 == 2 || ri1 == 3 ? swing : 0);
            commonTail(ri2 == 1 ? swing : 0);
        }
    }

    // ---- escorpión común (ModelScorpion) ----

    private static final float[][] COMMON_LEG_BASE = {{.49f, 2.65f}, {.24f, 2.9f}, {-.24f, -2.9f}, {-.49f, -2.65f}};

    private void commonLegs(float age, float amplitude) {
        for (int i = 0; i < 4; i++) {
            float a = cos(age * 2 * wingspeed - i * HALF_PI) * PI * .12f * amplitude;
            legs[i][0].part.yRot = a + COMMON_LEG_BASE[i][0];
            legs[4 + i][0].part.yRot = -a + COMMON_LEG_BASE[i][1];
        }
    }

    private void commonLeftClaw(float f) {
        Seg arm2 = leftClaw[0], arm1 = leftClaw[1], claw = leftClaw[2];
        arm2.part.yRot = .52f + f;
        arm1.z(arm2.z() - sin(arm2.part.yRot) * 4.5f);
        claw.z(arm1.z() - 3);
        claw.part.yRot = .381f - f;
    }

    private void commonRightClaw(float f) {
        Seg arm2 = rightClaw[0], arm1 = rightClaw[1], claw = rightClaw[2];
        arm2.part.yRot = 2.61f - f;
        arm1.z(arm2.z() - sin(arm2.part.yRot) * 4.5f);
        claw.z(arm1.z() - 3);
        claw.part.yRot = -.381f + f;
    }

    private void commonTail(float f) {
        tail[0].part.xRot = .26f + f;
        tail[1].part.xRot = tail[0].part.xRot + .769f + f;
        follow(tail[1], tail[0], 4);
        tail[2].part.xRot = tail[1].part.xRot + .701f + f;
        follow(tail[2], tail[1], 4);
        tail[3].part.xRot = tail[2].part.xRot - 5.501f - f * 3 / 2 - .4f;
        follow(tail[3], tail[2], 3);
        follow(tail[4], tail[3], 4);
        follow(tail[5], tail[4], 4);
    }

    // ---- escorpión emperador (ModelEmperorScorpion) ----

    private void emperorLegs(float age, float amplitude) {
        for (int i = 0; i < 4; i++) {
            float swing = cos(age * 2 * wingspeed - i * HALF_PI) * PI * .12f * amplitude;
            float next = cos((age + .1f) * 2 * wingspeed - i * HALF_PI) * PI * .12f * amplitude;
            // La pata se levanta mientras avanza (cuando la oscilación crece).
            float lift = next > swing ? .47f * amplitude - Math.abs(swing) : 0;
            emperorLeg(legs[i], swing, lift, 1);
            emperorLeg(legs[4 + i], -swing, lift, -1);
        }
    }

    /** doLeftLeg (side 1) y doRightLeg (side -1) del clásico. */
    private static void emperorLeg(Seg[] s, float f, float g, int side) {
        s[1].part.yRot = f;
        s[2].part.yRot = f;
        s[3].part.yRot = f;
        s[4].part.yRot = side * f;
        float sf = sin(f);
        s[2].z(s[1].z() - side * sf * 6);
        s[2].x(s[1].x() + side * (6 - Math.abs(sf * 6)));
        s[3].z(s[2].z() - side * sf * 9);
        s[3].x(s[2].x() + side * (9 - Math.abs(sf * 9)));
        s[4].z(s[3].z() - side * sf);
        s[4].x(s[3].x() + side * (1 - Math.abs(sf)));
        s[1].part.zRot = side * (-g - .929f);
        s[2].part.zRot = side * (-g + .632f);
        s[2].y(s[1].y() + side * 11.5f * sin(s[1].part.zRot));
        s[3].y(s[2].y() + side * 11.5f * sin(s[2].part.zRot));
        s[4].y(s[3].y() + 6.5f);
    }

    private void emperorLeftClaw(float f) {
        Seg seg1 = leftClaw[0], seg2 = leftClaw[1];
        seg1.part.yRot = -1.57f + f;
        seg2.z(-22 - cos(seg1.part.yRot) * 12);
        for (int i = 2; i < 5; i++) leftClaw[i].z(seg2.z() - 11);
        leftClaw[2].part.yRot = .074f + f;
        leftClaw[4].part.yRot = .371f - f;
    }

    private void emperorRightClaw(float f) {
        Seg seg1 = rightClaw[0], seg2 = rightClaw[1];
        seg1.part.yRot = 1.57f - f;
        seg2.z(-22 - cos(seg1.part.yRot) * 12);
        for (int i = 2; i < 5; i++) rightClaw[i].z(seg2.z() - 11);
        rightClaw[2].part.yRot = -.074f - f;
        rightClaw[4].part.yRot = -.371f + f;
    }

    private void emperorTail(float f) {
        Seg[] t = tail;
        t[0].part.xRot = .594f + f;
        t[1].part.xRot = t[0].part.xRot + .484f + f;
        follow(t[1], t[0], 9);
        t[2].part.xRot = t[1].part.xRot + .632f + f;
        follow(t[2], t[1], 10);
        t[3].part.xRot = t[2].part.xRot + .557f - f;
        follow(t[3], t[2], 10);
        t[4].part.xRot = t[3].part.xRot + .632f - f;
        follow(t[4], t[3], 10);
        t[5].part.xRot = t[4].part.xRot - 5.501f - f * 3 / 2 - .4f;
        follow(t[5], t[4], 10);
        t[6].part.xRot = t[5].part.xRot - 2.822f - f * 2.5f - 2.2f;
        follow(t[6], t[5], 10);
        t[7].part.xRot = t[6].part.xRot;
        t[7].y(t[6].y());
        t[7].z(t[6].z());
        t[8].part.xRot = t[6].part.xRot + f * .66f;
        follow(t[8], t[6], 10);
        t[9].part.xRot = t[8].part.xRot - .48f + f;
        follow(t[9], t[8], 3);
        t[10].part.xRot = t[9].part.xRot - 1.01f + f * 1.7f;
        follow(t[10], t[9], 3);
    }

    /** Sitúa {@code seg} al final de {@code prev}, que mide {@code length} y gira en X. */
    private static void follow(Seg seg, Seg prev, float length) {
        seg.y(prev.y() - sin(prev.part.xRot) * length);
        seg.z(prev.z() + cos(prev.part.xRot) * length);
    }
}
