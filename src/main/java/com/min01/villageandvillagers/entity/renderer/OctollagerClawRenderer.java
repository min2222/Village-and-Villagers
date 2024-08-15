package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelOctollagerClaw;
import com.min01.villageandvillagers.entity.model.ModelOctollagerTentacle;
import com.min01.villageandvillagers.entity.villager.EntityOctollagerClaw;
import com.min01.villageandvillagers.util.KinematicChain.ChainSegment;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class OctollagerClawRenderer extends EntityRenderer<EntityOctollagerClaw>
{
	public static final ResourceLocation CLAW_TEXTURE = new ResourceLocation(VillageandVillagers.MODID, "textures/entity/octollager_claw.png");
	public static final ResourceLocation TENTACLE_TEXTURE = new ResourceLocation(VillageandVillagers.MODID, "textures/entity/octollager_tentacle.png");
	public final ModelOctollagerClaw clawModel;
	public final ModelOctollagerTentacle tentacleModel;
	
	public OctollagerClawRenderer(Context p_174008_)
	{
		super(p_174008_);
		this.clawModel = new ModelOctollagerClaw(p_174008_.bakeLayer(ModelOctollagerClaw.LAYER_LOCATION));
		this.tentacleModel = new ModelOctollagerTentacle(p_174008_.bakeLayer(ModelOctollagerTentacle.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityOctollagerClaw p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		p_114488_.pushPose();
		p_114488_.scale(-1.0F, -1.0F, 1.0F);
		p_114488_.translate(0.0F, -1.5F, 0.0F);
		float xRot = Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot());
		float yRot = Mth.rotLerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot());
		this.clawModel.setupAnim(p_114485_, 0, 0, 0, yRot + 180.0F, xRot - 90.0F);
		this.clawModel.renderToBuffer(p_114488_, p_114489_.getBuffer(RenderType.entityCutoutNoCull(CLAW_TEXTURE)), p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		p_114488_.popPose();
		
		for(ChainSegment segment : p_114485_.chain.getSegments())
		{
			this.renderSegment(p_114485_, p_114488_, p_114489_, segment.getPos(), segment.getRot(), p_114490_, p_114487_);
		}
	}
	
	public void renderSegment(EntityOctollagerClaw claw, PoseStack stack, MultiBufferSource bufferSource, Vec3 pos, Vec2 rot, int packedLight, float partialTick)
	{
		stack.pushPose();
		stack.translate(0, -1.5F, 0);
		double d0 = Mth.lerp((double)partialTick, claw.xOld, claw.getX());
		double d1 = Mth.lerp((double)partialTick, claw.yOld, claw.getY());
		double d2 = Mth.lerp((double)partialTick, claw.zOld, claw.getZ());
		Vec3 lerpPos = new Vec3(d0, d1, d2);
		pos = pos.subtract(lerpPos);
		stack.translate(pos.x, pos.y, pos.z);
		this.tentacleModel.setupAnim(claw, 0, 0, 0, rot.y, rot.x);
		this.tentacleModel.renderToBuffer(stack, bufferSource.getBuffer(RenderType.entityCutoutNoCull(TENTACLE_TEXTURE)), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityOctollagerClaw p_114482_)
	{
		return CLAW_TEXTURE;
	}
}
