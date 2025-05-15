package com.min01.villageandvillagers.entity.model;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.villager.EntityRobinHood;
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

public class ModelRobinHood extends HierarchicalModel<EntityRobinHood>
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VillageandVillagers.MODID, "robin_hood"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public ModelRobinHood(ModelPart root)
	{
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.right_leg = this.body.getChild("right_leg");
		this.left_leg = this.body.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(82, 22).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(32, -15).addBox(-3.3F, -3.0F, -4.5F, 0.0F, 16.0F, 16.0F, new CubeDeformation(-3.0F)), PartPose.offsetAndRotation(4.0F, -18.0F, 4.0F, 2.7053F, 0.0F, 3.1416F));

		PartDefinition beard = head.addOrReplaceChild("beard", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.3612F, -4.1F, -0.0873F, 0.0F, 0.0F));

		beard.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(80, 76).mirror().addBox(-7.2F, -3.5F, 0.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 0.1612F, 0.0F, 0.0F, 0.0F, -0.3927F));

		beard.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(80, 76).addBox(-0.8F, -3.5F, 0.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.1612F, 0.0F, 0.0F, 0.0F, 0.3927F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -4.0F));

		PartDefinition headwear = head.addOrReplaceChild("headwear", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		headwear.addOrReplaceChild("headwear_r1", CubeListBuilder.create().texOffs(60, 9).addBox(-4.0F, -1.1F, -4.8F, 8.0F, 2.0F, 11.0F, new CubeDeformation(0.51F)), PartPose.offsetAndRotation(0.0F, -7.6F, -1.5F, 0.0436F, 0.0F, 0.0F));

		PartDefinition bodywear = body.addOrReplaceChild("bodywear", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F))
		.texOffs(32, 52).addBox(-4.5F, -0.6F, -3.5F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.2F))
		.texOffs(33, 86).addBox(-4.5F, -0.6F, -3.5F, 9.0F, 14.0F, 7.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		bodywear.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 80).addBox(-2.5F, -4.2F, -1.5F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 72).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4096F, 4.2868F, 4.5F, 0.0F, 0.0F, -0.6109F));

		bodywear.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(5, 88).addBox(-7.4F, -4.5F, 0.3F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3074F, 0.6984F, 4.5F, 0.0F, 0.0F, -2.6616F));

		bodywear.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(5, 88).addBox(-14.8F, -8.8F, -1.0F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.2074F, -2.1016F, 5.5F, 0.0F, 0.0F, -3.1416F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.05F, -1.05F, -0.7505F, 0.0F, 0.0F));

		arms.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(64, 64).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(6.0F, -0.95F, 0.05F, 0.0F, 0.0F, -0.0873F));

		arms.addOrReplaceChild("left_arm_r2", CubeListBuilder.create().texOffs(64, 72).mirror().addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-6.0F, -0.95F, 1.05F, 0.0F, 0.0F, 0.0873F));

		arms.addOrReplaceChild("mirrored", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -23.05F, -3.05F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 21.05F, 1.05F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 64).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -10.0F, 0.0F));

		right_arm.addOrReplaceChild("left_arm_r3", CubeListBuilder.create().texOffs(64, 72).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 64).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, -10.0F, 0.0F));

		left_arm.addOrReplaceChild("left_arm_r4", CubeListBuilder.create().texOffs(64, 64).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 80).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 80).mirror().addBox(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityRobinHood entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
		VillageClientUtil.animateHead(this.head, netHeadYaw, headPitch);
		this.arms.visible = !entity.isCombatMode();
		this.right_arm.visible = entity.isCombatMode();
		this.left_arm.visible = entity.isCombatMode();

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