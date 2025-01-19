package com.min01.villageandvillagers.entity.model;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.animation.HarvesterAnimation;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
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
import net.minecraft.util.Mth;

public class ModelHarvester extends HierarchicalModel<EntityHarvester>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VillageandVillagers.MODID, "harvester"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart upperbody;
	private final ModelPart head;
	private final ModelPart arms_out;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart arms_crossed;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public ModelHarvester(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.upperbody = this.body.getChild("upperbody");
		this.head = this.upperbody.getChild("head");
		this.arms_out = this.upperbody.getChild("arms_out");
		this.left_arm = this.arms_out.getChild("left_arm");
		this.right_arm = this.arms_out.getChild("right_arm");
		this.arms_crossed = this.upperbody.getChild("arms_crossed");
		this.right_leg = this.body.getChild("right_leg");
		this.left_leg = this.body.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upperbody = body.addOrReplaceChild("upperbody", CubeListBuilder.create().texOffs(0, 45).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition head = upperbody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 40).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(28, 24).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.0F));

		head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(16, 0).addBox(-8.0F, 0.25F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-4.0F, -4.75F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.25F, 0.0F));

		head.addOrReplaceChild("wheat", CubeListBuilder.create().texOffs(14, 22).addBox(-1.0F, 0.0F, -6.0F, 2.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -4.0F, 0.3927F, 0.7854F, 0.0F));

		PartDefinition arms_out = upperbody.addOrReplaceChild("arms_out", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		arms_out.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -22.0F, 0.0F));

		PartDefinition right_arm = arms_out.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -22.0F, 0.0F));

		PartDefinition pitchfork = right_arm.addOrReplaceChild("pitchfork", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 8.5F, -3.0F, 1.5708F, 0.0F, 0.0F));

		pitchfork.addOrReplaceChild("fork_locator", CubeListBuilder.create().texOffs(67, 2).addBox(-0.5F, 3.5F, -0.5F, 1.0F, 20.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(71, 4).addBox(-0.5F, -3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(75, 4).addBox(-3.5F, -3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(71, 11).addBox(2.5F, -3.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(71, 2).addBox(-3.5F, 2.5F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

		upperbody.addOrReplaceChild("arms_crossed", CubeListBuilder.create().texOffs(48, 20).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(34, 28).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 16).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -1.0F, -0.7854F, 0.0F, 0.0F));

		upperbody.addOrReplaceChild("satchel", CubeListBuilder.create().texOffs(29, 55).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -1.0F, -2.0F));

		body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(52, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityHarvester entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		VillageClientUtil.animateHead(this.head, netHeadYaw, headPitch);
		this.animate(entity.stabAnimationState, HarvesterAnimation.HARVESTER_STAB, ageInTicks);
		this.animate(entity.twoHandStabAnimationState, HarvesterAnimation.HARVESTER_TWO_HAND_STAB, ageInTicks);
		this.arms_crossed.visible = !entity.isCombatMode();
		this.arms_out.visible = entity.isCombatMode();

		if(entity.getAnimationState() != 0)
		{
			limbSwing = 0.0F;
			limbSwingAmount = 0.0F;
		}
		
		this.right_leg.xRot += Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.left_leg.xRot += Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		this.right_arm.xRot += Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.left_arm.xRot += Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
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