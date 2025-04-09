package com.min01.villageandvillagers.streak;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class EntityTrackerRenderer extends EntityRenderer<EntityTracker>
{
	public EntityTrackerRenderer(Context p_174008_)
	{
		super(p_174008_);
	}
	
	@Override
	public void render(EntityTracker tracker, float entityYaw, float partialTick, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
	{
		matrixStackIn.popPose();
        Vec3 vec3d = this.entityRenderDispatcher.camera.getPosition();
        double camX = vec3d.x;
        double camY = vec3d.y;
        double camZ = vec3d.z;

        double d0 = Mth.lerp((double)partialTick, tracker.parent.xOld, tracker.parent.getX());
        double d1 = Mth.lerp((double)partialTick, tracker.parent.yOld, tracker.parent.getY());
        double d2 = Mth.lerp((double)partialTick, tracker.parent.zOld, tracker.parent.getZ());

        int parentPackedLight = this.entityRenderDispatcher.getPackedLightCoords(tracker.parent, partialTick);
        Vec3 renderOffset = this.getRenderOffset(tracker, partialTick);
        double pX = d0 - camX + renderOffset.x;
        double pY = d1 - camY + renderOffset.y;
        double pZ = d2 - camZ + renderOffset.z;
        
        matrixStackIn.pushPose();
        matrixStackIn.translate(pX, pY, pZ);
        tracker.tags.forEach(tag -> tag.render(tracker, entityYaw, partialTick, matrixStackIn, bufferIn, parentPackedLight));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityTracker p_114482_) 
	{
		return null;
	}
}
