package com.min01.villageandvillagers.streak;

import com.min01.villageandvillagers.util.VillageClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class StreakTag implements Tag
{
    @Override
    public int maxTracks()
    {
        return Math.max(100, 6);
    }

    @Override
    public int maxDeathPersist()
    {
        return Math.max(100, 6);
    }
    
    @Override
    public boolean ignoreFrustumCheck()
    {
        return true;
    }
    
	@SuppressWarnings("unchecked")
	@Override
    public <T extends Entity> void render(EntityTracker tracker, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
    {
        if(tracker.trackedInfo.size() <= 1)
        {
            return;
        }

        matrixStackIn.pushPose();

        double d0 = Mth.lerp((double)partialTicks, tracker.parent.xOld, tracker.parent.getX());
        double d1 = Mth.lerp((double)partialTicks, tracker.parent.yOld, tracker.parent.getY());
        double d2 = Mth.lerp((double)partialTicks, tracker.parent.zOld, tracker.parent.getZ());

        EntityRenderer<?> entityRenderer = VillageClientUtil.MC.getEntityRenderDispatcher().getRenderer(tracker.parent);
        if(entityRenderer instanceof IStreakRenderer<?>)
        {
        	IStreakRenderer<T> renderer = (IStreakRenderer<T>) entityRenderer;
            if(!tracker.parent.isInvisible() && renderer.canRenderStreak(tracker.getParent()))
            {
                VertexConsumer consumer = bufferIn.getBuffer(renderer.getStreakRenderType(renderer.getStreakTexture(tracker.getParent()), tracker.getParent()));
                Vec3 color = renderer.getStreakColor(tracker.getParent());
                int maxTrailNumber = renderer.getMaxStreakCount(tracker.getParent());
                for(int i = Math.min(maxTrailNumber, tracker.trackedInfo.size()) - 1 - tracker.timeAfterDeath; i >= 1; i--)
                {
                    EntityTracker.EntityInfo info = tracker.trackedInfo.get(i);
                    float x = (float)(info.posX - d0);
                    float y = (float)(info.posY - d1);
                    float z = (float)(info.posZ - d2);
                    HierarchicalModel<T> model = renderer.getStreakModel(tracker.getParent());
                    float yRot = Mth.rotLerp(partialTicks, tracker.parent.yRotO, tracker.parent.getYRot());
                    float xRot = Mth.lerp(partialTicks, tracker.parent.xRotO, tracker.parent.getXRot());
                    matrixStackIn.pushPose();
                    matrixStackIn.translate(x, y, z);
                    matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
                    matrixStackIn.translate(0.0F, -1.5F, 0.0F);
                    model.setupAnim(tracker.getParent(), 0, 0, 0, yRot, xRot);
                    model.renderToBuffer(matrixStackIn, consumer, packedLightIn, OverlayTexture.NO_OVERLAY, (float) color.x, (float) color.y, (float) color.z, ((maxTrailNumber + 1) - (i + partialTicks)) / (float)(maxTrailNumber) * 0.5F);
                    matrixStackIn.popPose();
                }
            }
        }

        matrixStackIn.popPose();
    }
}