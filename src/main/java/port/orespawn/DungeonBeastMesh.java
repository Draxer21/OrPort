package port.orespawn;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

final class DungeonBeastMesh {
    static ModelPart create() {
        var mesh = new MeshDefinition();
        var body = mesh.getRoot().addOrReplaceChild("beast", CubeListBuilder.create(),
            PartPose.offsetAndRotation(0, 0, 0, 0, (float)Math.PI / 2, 0));
        var leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create(), PartPose.offset(3, 15, 4));
        var rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create(), PartPose.offset(3, 15, -4));
        var leftJaw = body.addOrReplaceChild("leftJaw", CubeListBuilder.create(), PartPose.offset(10, 16, 2));
        var rightJaw = body.addOrReplaceChild("rightJaw", CubeListBuilder.create(), PartPose.offset(10, 16, -2));
        var segment1 = body.addOrReplaceChild("segment1", CubeListBuilder.create(), PartPose.offset(-1, 15, 0));
        var segment2 = segment1.addOrReplaceChild("segment2", CubeListBuilder.create(), PartPose.offset(-5, 2, 0));
        var segment3 = segment2.addOrReplaceChild("segment3", CubeListBuilder.create(), PartPose.offset(-4, 3, 0));
        var segment4 = segment3.addOrReplaceChild("segment4", CubeListBuilder.create(), PartPose.offset(-4, 2.8F, 0));
        var segment5 = segment4.addOrReplaceChild("segment5", CubeListBuilder.create(), PartPose.offset(-4, 0.4F, 0));
        var segment6 = segment5.addOrReplaceChild("segment6", CubeListBuilder.create(), PartPose.offset(-3, 0.3F, 0));
        var segment7 = segment6.addOrReplaceChild("segment7", CubeListBuilder.create(), PartPose.offset(-3, 0, 0));
        segment7.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -0.5F, -0.5333334F, 3, 1, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 3.141593F));
        body.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 0).addBox(2, -2.466667F, 4.3F, 4, 4, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0.8028515F, 0));
        body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 0).addBox(0, -1.5F, -2.533333F, 3, 3, 5),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -0.1745329F));
        body.addOrReplaceChild("lhornbase", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, -3, 0.5F, 3, 1, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, 0));
        body.addOrReplaceChild("leye", CubeListBuilder.create().texOffs(14, 15).addBox(3, -1.466667F, 3.3F, 2, 1, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0.4363323F, 0));
        leftJaw.addOrReplaceChild("ljaw3", CubeListBuilder.create().texOffs(10, 28).addBox(3.5F, 0, 1.5F, 1, 1, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0.5235988F, 0));
        leftJaw.addOrReplaceChild("ljaw1", CubeListBuilder.create().texOffs(10, 20).addBox(0, 0, -1.466667F, 3, 1, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, -0.3490659F, 0));
        leftJaw.addOrReplaceChild("ljaw2", CubeListBuilder.create().texOffs(10, 24).addBox(2, 0, 0.3F, 2, 1, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0.3490659F, 0));
        rightJaw.addOrReplaceChild("rjaw1", CubeListBuilder.create().texOffs(10, 20).addBox(0, 0, -0.4666667F, 3, 1, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0.3490659F, 0));
        rightJaw.addOrReplaceChild("rjaw2", CubeListBuilder.create().texOffs(10, 24).addBox(2, 0, -2.3F, 2, 1, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, -0.3490659F, 0));
        rightJaw.addOrReplaceChild("rjaw3", CubeListBuilder.create().texOffs(10, 28).addBox(3.5F, 0, -2.5F, 1, 1, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, -0.5235988F, 0));
        segment1.addOrReplaceChild("t1s3", CubeListBuilder.create().texOffs(75, 0).addBox(-3, -7, -0.5F, 1, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.8726646F));
        body.addOrReplaceChild("rshoulder", CubeListBuilder.create().texOffs(0, 0).addBox(2, -2.2F, -5, 4, 4, 2),
            PartPose.offsetAndRotation(-1, 15, 0, 0, 0, 0));
        rightLeg.addOrReplaceChild("rheel", CubeListBuilder.create().texOffs(0, 0).addBox(-2.3F, 0.3F, 6, 1, 1, 1),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, 0, 0));
        body.addOrReplaceChild("lshoulder", CubeListBuilder.create().texOffs(0, 0).addBox(2, -2.2F, 3, 4, 4, 2),
            PartPose.offsetAndRotation(-1, 15, 0, 0, 0, 0));
        rightLeg.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.466667F, -2, -5, 3, 3, 6),
            PartPose.offsetAndRotation(0, 0, 0, 0.6981317F, 0, 0));
        rightLeg.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -0.2F, 0, 2, 2, 6),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, 0, 0));
        leftLeg.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.466667F, -2, -1, 3, 3, 6),
            PartPose.offsetAndRotation(0, 0, 0, -0.6981317F, 0, 0));
        leftLeg.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -1.8F, 0, 2, 2, 6),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, 0, 0));
        rightLeg.addOrReplaceChild("rfoot", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -0.7F, 5, 3, 3, 2),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, 0, 0));
        leftLeg.addOrReplaceChild("ltoe3", CubeListBuilder.create().texOffs(32, 0).addBox(-3.7F, -1.5F, 4.5F, 1, 1, 2),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, 0.7853982F, -0.7853982F));
        leftLeg.addOrReplaceChild("ltoe2", CubeListBuilder.create().texOffs(32, 0).addBox(-3, -1.3F, 5.2F, 1, 1, 2),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, 0, -0.7853982F));
        leftLeg.addOrReplaceChild("ltoe1", CubeListBuilder.create().texOffs(32, 0).addBox(-3, -0.6F, 5.2F, 1, 1, 2),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, -0.7853982F, -0.7853982F));
        body.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 0).addBox(2, -2.466667F, -3, 4, 4, 6),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, 0));
        body.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(75, 6).addBox(-7, -4, -0.5F, 1, 2, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, 2.181662F));
        body.addOrReplaceChild("rhornbase", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, -3, -2.5F, 3, 1, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, 0));
        body.addOrReplaceChild("rh1", CubeListBuilder.create().texOffs(0, 28).addBox(4, -3, -2.5F, 2, 3, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -0.5235988F));
        body.addOrReplaceChild("lh1", CubeListBuilder.create().texOffs(0, 28).addBox(4, -3, 0.5F, 2, 3, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -0.5235988F));
        body.addOrReplaceChild("lh2", CubeListBuilder.create().texOffs(0, 23).addBox(5, -4, 1, 1, 3, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -0.8726646F));
        body.addOrReplaceChild("rh2", CubeListBuilder.create().texOffs(0, 23).addBox(5, -4, -2, 1, 3, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -0.8726646F));
        body.addOrReplaceChild("rh3", CubeListBuilder.create().texOffs(0, 19).addBox(6.1F, -2.4F, -2, 1, 2, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -1.396263F));
        body.addOrReplaceChild("lh3", CubeListBuilder.create().texOffs(0, 19).addBox(6.1F, -2.4F, 1, 1, 2, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -1.396263F));
        body.addOrReplaceChild("lh4", CubeListBuilder.create().texOffs(0, 15).addBox(6.5F, -1.8F, 1, 1, 2, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -1.745329F));
        body.addOrReplaceChild("rh4", CubeListBuilder.create().texOffs(0, 15).addBox(6.5F, -1.8F, -2, 1, 2, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, -1.745329F));
        body.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(75, 6).addBox(-8, -2.5F, -0.5F, 1, 2, 1),
            PartPose.offsetAndRotation(5, 15, 0, 0, 0, 2.617994F));
        segment2.addOrReplaceChild("t2s3", CubeListBuilder.create().texOffs(75, 0).addBox(3, 3.466667F, -0.5333334F, 1, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.007129F));
        segment3.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -1.5F, -2.5F, 6, 3, 5),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.530727F));
        segment4.addOrReplaceChild("t4s1", CubeListBuilder.create().texOffs(75, 0).addBox(0.5333334F, 1.533333F, -0.4666667F, 1, 2, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.356194F));
        segment6.addOrReplaceChild("t6s1", CubeListBuilder.create().texOffs(75, 0).addBox(0, 0.5F, -0.5F, 1, 1, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.356194F));
        segment6.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -0.5F, -1, 4, 1, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 3.141593F));
        body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0, -3, -4, 7, 6, 8),
            PartPose.offsetAndRotation(-1, 15, 0, 0, 0, 0));
        body.addOrReplaceChild("bodys1", CubeListBuilder.create().texOffs(75, 0).addBox(6, -3, -0.5F, 1, 4, 1),
            PartPose.offsetAndRotation(-1, 15, 0, 0, 0, -0.5235988F));
        body.addOrReplaceChild("bodys2", CubeListBuilder.create().texOffs(75, 0).addBox(4, -4, -0.5F, 1, 4, 1),
            PartPose.offsetAndRotation(-1, 15, 0, 0, 0, -0.5235988F));
        segment1.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -2.533333F, -3.5F, 7, 5, 7),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.792527F));
        body.addOrReplaceChild("bodys3", CubeListBuilder.create().texOffs(75, 0).addBox(2, -5, -0.5F, 1, 4, 1),
            PartPose.offsetAndRotation(-1, 15, 0, 0, 0, -0.5235988F));
        segment1.addOrReplaceChild("t1s1", CubeListBuilder.create().texOffs(75, 0).addBox(1, -5, -0.5F, 1, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.8726646F));
        segment1.addOrReplaceChild("t1s2", CubeListBuilder.create().texOffs(75, 0).addBox(-1, -6, -0.5F, 1, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.8726646F));
        segment2.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -2, -3, 7, 4, 6),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.530727F));
        segment3.addOrReplaceChild("t3s2", CubeListBuilder.create().texOffs(75, 0).addBox(2.5F, 2.466667F, -0.5333334F, 1, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.007129F));
        segment2.addOrReplaceChild("t2s2", CubeListBuilder.create().texOffs(75, 0).addBox(1, 2.466667F, -0.5333334F, 1, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.007129F));
        segment2.addOrReplaceChild("t2s1", CubeListBuilder.create().texOffs(75, 0).addBox(-1, 1.466667F, -0.5333334F, 1, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.007129F));
        segment3.addOrReplaceChild("t3s1", CubeListBuilder.create().texOffs(75, 0).addBox(0.5F, 1.466667F, -0.5333334F, 1, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.007129F));
        segment4.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -1, -2, 5, 2, 4),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 3.054326F));
        segment5.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(0, 0).addBox(-1, -0.5F, -1.5F, 4, 1, 3),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 3.054326F));
        segment5.addOrReplaceChild("t5s1", CubeListBuilder.create().texOffs(75, 0).addBox(0, 0.5F, -0.5F, 1, 2, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 2.356194F));
        body.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(2, -2.466667F, -6.3F, 4, 4, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, -0.8028515F, 0));
        body.addOrReplaceChild("reye", CubeListBuilder.create().texOffs(5, 15).addBox(3, -1.466667F, -5.3F, 2, 1, 2),
            PartPose.offsetAndRotation(5, 15, 0, 0, -0.4363323F, 0));
        leftLeg.addOrReplaceChild("lfoot", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.3F, 5, 3, 3, 2),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, 0, 0));
        rightLeg.addOrReplaceChild("rfoot2", CubeListBuilder.create().texOffs(0, 0).addBox(0.6F, -1.5F, 5, 2, 2, 2),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, 0.7853982F, 0));
        leftLeg.addOrReplaceChild("lfoot2", CubeListBuilder.create().texOffs(0, 0).addBox(0.7F, -0.5F, 5, 2, 2, 2),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, -0.7853982F, 0));
        leftLeg.addOrReplaceChild("lheel", CubeListBuilder.create().texOffs(0, 0).addBox(-2.3F, -1.3F, 6, 1, 1, 1),
            PartPose.offsetAndRotation(0, 2, 3, -1.570796F, 0, 0));
        rightLeg.addOrReplaceChild("rtoe3", CubeListBuilder.create().texOffs(32, 0).addBox(-3.7F, 0.6F, 4.5F, 1, 1, 2),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, -0.7853982F, -0.7853982F));
        rightLeg.addOrReplaceChild("rtoe2", CubeListBuilder.create().texOffs(32, 0).addBox(-3, 0.3F, 5.2F, 1, 1, 2),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, 0, -0.7853982F));
        rightLeg.addOrReplaceChild("rtoe1", CubeListBuilder.create().texOffs(32, 0).addBox(-3, -0.6F, 5.2F, 1, 1, 2),
            PartPose.offsetAndRotation(0, 2, -3, -1.570796F, 0.7853982F, -0.7853982F));
        return LayerDefinition.create(mesh, 128, 64).bakeRoot();
    }
}
