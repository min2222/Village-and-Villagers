package com.min01.villageandvillagers.entity.model;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.animation.ScarecrowAnimation;
import com.min01.villageandvillagers.entity.misc.EntityScarecrow;
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

public class ModelScarecrow extends HierarchicalModel<EntityScarecrow>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VillageandVillagers.MODID, "scarecrow"), "main");
	private final ModelPart root;
	private final ModelPart body;

	public ModelScarecrow(ModelPart root)
	{
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 42).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upperbody = body.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(16, 42).addBox(-6.0F, -2.0F, 0.0F, 12.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

		upperbody.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		upperbody.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 46).addBox(-12.0F, 2.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 34).addBox(-12.0F, -2.0F, -2.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-16.0F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -12.0F, 0.0F));

		upperbody.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 42).addBox(8.0F, 2.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(0.0F, -2.0F, -2.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 48).addBox(12.0F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, 0.0F));

		PartDefinition head = upperbody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -5.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(32, 46).addBox(-1.0F, -28.0F, -4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(EntityScarecrow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		VillageClientUtil.animateHead(this.body, netHeadYaw, 0);
		
		entity.idleAnimationState.animate(this, ScarecrowAnimation.SCARECROW_IDLE, ageInTicks);
		entity.hurtAnimationState.animate(this, ScarecrowAnimation.SCARECROW_HURT, ageInTicks);
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