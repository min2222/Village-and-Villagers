package com.min01.villageandvillagers.entity.model;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.animation.HaybaleBarricadeAnimation;
import com.min01.villageandvillagers.entity.misc.EntityHaybaleBarricade;
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

public class ModelHaybaleBarricade extends HierarchicalModel<EntityHaybaleBarricade>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VillageandVillagers.MODID, "haybale_barricade"), "main");
	private final ModelPart root;

	public ModelHaybaleBarricade(ModelPart root) 
	{
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("left_bale", CubeListBuilder.create().texOffs(0, 56).addBox(-6.5979F, -23.6336F, -1.7212F, 12.0F, 24.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 48).addBox(-8.5979F, -27.6336F, 0.2788F, 16.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.1394F, 0.0F, 2.3483F, 0.0F, -0.4363F, 0.0873F));

		body.addOrReplaceChild("right_bale", CubeListBuilder.create().texOffs(32, 56).addBox(-6.3049F, -23.7208F, -2.1422F, 12.0F, 24.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 52).addBox(-8.3049F, -27.7208F, -0.1422F, 16.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.1394F, 0.0F, 2.3483F, 0.0F, 0.4363F, -0.0873F));

		body.addOrReplaceChild("middle_bale", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -40.0F, -4.0F, 28.0F, 40.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-16.0F, -48.0F, 0.0F, 32.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityHaybaleBarricade entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		entity.appearAnimationState.animate(this, HaybaleBarricadeAnimation.BARRICADE_APPEAR, ageInTicks);
		entity.disappearAnimationState.animate(this, HaybaleBarricadeAnimation.BARRICADE_DISAPPEAR, ageInTicks);
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