package com.min01.villageandvillagers.entity.model;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.villager.EntityOctollagerClaw;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ModelOctollagerClaw extends EntityModel<EntityOctollagerClaw> 
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VillageandVillagers.MODID, "claw"), "main");
	public final ModelPart root;

	public ModelOctollagerClaw(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.0F, -1.0F, -1.5708F, 0.0F, 0.0F));

		root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.0F));

		root.addOrReplaceChild("claw1", CubeListBuilder.create().texOffs(6, 7).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2385F, -1.9829F, -0.3491F, 0.0F, 0.0F));

		PartDefinition claw2 = root.addOrReplaceChild("claw2", CubeListBuilder.create(), PartPose.offset(1.9829F, 0.2385F, 0.0F));

		claw2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 7).addBox(2.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2988F, 0.6409F, -3.0F, 0.0F, -1.5708F, -0.3491F));

		PartDefinition claw3 = root.addOrReplaceChild("claw3", CubeListBuilder.create(), PartPose.offset(-1.9829F, 0.2385F, 0.0F));

		claw3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 3).addBox(-4.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2988F, 0.6409F, -3.0F, 0.0F, 1.5708F, 0.3491F));

		root.addOrReplaceChild("claw4", CubeListBuilder.create().texOffs(0, 3).addBox(-1.0F, -1.5F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2385F, 1.9829F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(EntityOctollagerClaw entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root.xRot = (float) Math.toRadians(headPitch);
		this.root.yRot = (float) Math.toRadians(netHeadYaw);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) 
	{
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}