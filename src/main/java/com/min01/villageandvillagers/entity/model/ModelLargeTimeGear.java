package com.min01.villageandvillagers.entity.model;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear;
import com.min01.villageandvillagers.util.VillageClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelLargeTimeGear extends HierarchicalModel<EntityTimeGear>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VillageandVillagers.MODID, "large_time_gear"), "main");
	private final ModelPart root;

	public ModelLargeTimeGear(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition gear = root.addOrReplaceChild("gear", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 12.0F, -4.0F, 16.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 60).addBox(-5.0F, 20.0F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-20.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 60).addBox(12.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 74).addBox(20.0F, -5.0F, -3.0F, 8.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-8.0F, -20.0F, -4.0F, 16.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(64, 60).addBox(-5.0F, -28.0F, -3.0F, 10.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(60, 74).addBox(-28.0F, -5.0F, -3.0F, 8.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

		gear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(88, 24).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, -18.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		gear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 84).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		gear.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(80, 12).addBox(-5.0F, -2.0F, -2.0F, 10.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -18.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		gear.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 46).addBox(-8.0F, -2.0F, -3.0F, 16.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -13.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		gear.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 46).addBox(-8.0F, -2.0F, -3.0F, 16.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, -13.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		gear.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(44, 32).addBox(-8.0F, -6.0F, -3.0F, 16.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 13.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		gear.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(80, 0).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, 18.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		gear.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -6.0F, -3.0F, 16.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 13.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityTimeGear entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		VillageClientUtil.animateHead(this.root.getChild("gear"), netHeadYaw, headPitch);
	}
	
	@Override
	public ModelPart root() 
	{
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}