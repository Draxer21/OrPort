package port.orespawn;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.model.geom.builders.*;
final class ScorpionMesh {
    static ModelPart create() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var tailPivot = root.addOrReplaceChild("tailPivot", CubeListBuilder.create(), PartPose.offset(-2f, 17f, 3f));
        var lleg1Pivot = root.addOrReplaceChild("lleg1Pivot", CubeListBuilder.create(), PartPose.offset(2f, 18f, -3f));
        var rleg1Pivot = root.addOrReplaceChild("rleg1Pivot", CubeListBuilder.create(), PartPose.offset(-2f, 18f, -1f));
        var rleg2Pivot = root.addOrReplaceChild("rleg2Pivot", CubeListBuilder.create(), PartPose.offset(-2f, 18f, 1f));
        var lleg3Pivot = root.addOrReplaceChild("lleg3Pivot", CubeListBuilder.create(), PartPose.offset(2f, 18f, 1f));
        var rleg4Pivot = root.addOrReplaceChild("rleg4Pivot", CubeListBuilder.create(), PartPose.offset(-2f, 18f, 5f));
        var rleg3Pivot = root.addOrReplaceChild("rleg3Pivot", CubeListBuilder.create(), PartPose.offset(-2f, 18f, 3f));
        var lleg4Pivot = root.addOrReplaceChild("lleg4Pivot", CubeListBuilder.create(), PartPose.offset(2f, 18f, 3f));
        var lleg2Pivot = root.addOrReplaceChild("lleg2Pivot", CubeListBuilder.create(), PartPose.offset(2f, 18f, -1f));
        var lclawPivot = root.addOrReplaceChild("lclawPivot", CubeListBuilder.create(), PartPose.offset(1f, 18f, -6f));
        var rclawPivot = root.addOrReplaceChild("rclawPivot", CubeListBuilder.create(), PartPose.offset(-1f, 18f, -6f));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0f, 0f, 0f, 6f, 4f, 8f), PartPose.offsetAndRotation(-3f, 17f, -4f, 0f, 0f, 0f));
        tailPivot.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(28, 0).addBox(0f, 0f, 0f, 4f, 4f, 5f), PartPose.offsetAndRotation(0f, 0f, 0f, 0.261799f, 0f, 0f));
        tailPivot.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(46, 0).addBox(0f, 0f, 0f, 3f, 3f, 5f), PartPose.offsetAndRotation(0.5f, -0.2f, 3f, 1.02974f, 0f, 0f));
        tailPivot.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(62, 0).addBox(0f, 0f, 0f, 3f, 3f, 4f), PartPose.offsetAndRotation(0.5f, -2.5f, 5f, 1.72788f, 0f, 0f));
        tailPivot.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 17).addBox(0f, 0f, 0f, 2f, 2f, 5f), PartPose.offsetAndRotation(1f, -5f, 6f, 2.51327f, 0f, 0f));
        tailPivot.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(70, 7).addBox(0f, 0f, 0f, 2f, 2f, 4f), PartPose.offsetAndRotation(1f, -8f, 3f, 3.14159f, 0f, 0f));
        tailPivot.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(62, 7).addBox(0f, 0f, 0f, 1f, 1f, 3f), PartPose.offsetAndRotation(1.5f, -9f, -1f, 3.14159f, 0f, 0f));
        lleg1Pivot.addOrReplaceChild("lleg1", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, 0.488692f, 0.366519f));
        rleg1Pivot.addOrReplaceChild("rleg1", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, 2.6529f, -0.366519f));
        rleg2Pivot.addOrReplaceChild("rleg2", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, 2.89725f, -0.366519f));
        lleg3Pivot.addOrReplaceChild("lleg3", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, -0.244346f, 0.366519f));
        rleg4Pivot.addOrReplaceChild("rleg4", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, -2.6529f, -0.366519f));
        rleg3Pivot.addOrReplaceChild("rleg3", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, -2.89725f, -0.366519f));
        lleg4Pivot.addOrReplaceChild("lleg4", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, -0.488692f, 0.366519f));
        lleg2Pivot.addOrReplaceChild("lleg2", CubeListBuilder.create().texOffs(0, 12).addBox(0f, 0f, 0f, 11f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, 0.244346f, 0.366519f));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 9).addBox(0f, 0f, 0f, 5f, 3f, 4f), PartPose.offsetAndRotation(-2.5f, 17.5f, -8f, 0f, 0f, 0f));
        lclawPivot.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(46, 8).addBox(0f, 0f, 0f, 6f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, 0.523599f, 0.174533f));
        rclawPivot.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(46, 8).addBox(0f, 0f, -2f, 6f, 2f, 2f), PartPose.offsetAndRotation(0f, 0f, 0f, 0f, 2.61799f, -0.174533f));
        lclawPivot.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(70, 13).addBox(-2f, 0f, -3f, 2f, 2f, 3f), PartPose.offsetAndRotation(6f, 1f, -1.2f, 0.174533f, 0.174533f, 0f));
        rclawPivot.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(70, 13).addBox(0f, 0f, -3f, 2f, 2f, 3f), PartPose.offsetAndRotation(-6f, 1f, -1.2f, 0.174533f, -0.174533f, 0f));
        lclawPivot.addOrReplaceChild("lclaw", CubeListBuilder.create().texOffs(46, 12).addBox(-3f, 0f, -4f, 3f, 2f, 4f), PartPose.offsetAndRotation(6f, 1f, -4f, 0.0174533f, 0.383972f, 0.139626f));
        rclawPivot.addOrReplaceChild("rclaw", CubeListBuilder.create().texOffs(46, 12).addBox(0f, 0f, -4f, 3f, 2f, 4f), PartPose.offsetAndRotation(-6f, 1f, -4f, 0.0174533f, -0.383972f, 0.139626f));
        return LayerDefinition.create(mesh, 88, 24).bakeRoot();
    }
}
