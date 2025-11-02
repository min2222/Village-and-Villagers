package com.min01.villageandvillagers.mixin;

import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.min01.villageandvillagers.util.VillageClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer 
{
	//private final Matrix4f inverseMat = new Matrix4f();

	@Inject(at = @At(value = "TAIL"), method = "renderLevel")
	private void renderLevel(PoseStack mtx, float frameTime, long nanoTime, boolean renderOutline, Camera camera, GameRenderer gameRenderer, LightTexture light, Matrix4f projMat, CallbackInfo ci)
	{
		Entity camEntity = VillageClientUtil.MC.cameraEntity;
		if(camEntity != null)
		{
			double x = Mth.lerp((double)frameTime, camEntity.xOld, camEntity.getX());
			double y = Mth.lerp((double)frameTime, camEntity.yOld, camEntity.getY());
			double z = Mth.lerp((double)frameTime, camEntity.zOld, camEntity.getZ());
			
			Vec3 camPos = camera.getPosition();
			Vec3 playerPos = new Vec3(x, y, z);
			Vec3 pos = playerPos.subtract(camPos);
			mtx.pushPose();
			mtx.translate(pos.x, pos.y, pos.z);
			//this.apply(mtx, frameTime);
			mtx.popPose();
		}
	}
	
	/*public void apply(PoseStack mtx, float frameTime)
	{
		Minecraft minecraft = VillageClientUtil.MC;

		ExtendedPostChain shaderChain = null;
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.setSampler("FireNoise", () -> minecraft.getTextureManager().getTexture(new ResourceLocation(VillageandVillagers.MODID, "textures/misc/rgba_noise_medium.png")).getId());
			shader.safeGetUniform("ViewMatrix").set(mtx.last().pose());
			shader.safeGetUniform("ProjectionMatrix").set(RenderSystem.getProjectionMatrix());
			shader.safeGetUniform("InverseTransformMatrix").set(getInverseTransformMatrix(this.inverseMat, mtx.last().pose()));
			shader.safeGetUniform("Time").set((((float) (minecraft.level.getGameTime() % 2400000)) + frameTime) / 20.0F);
			shaderChain.process(frameTime);
			minecraft.getMainRenderTarget().bindWrite(false);
		}
	}
	
	private static Matrix4f getInverseTransformMatrix(Matrix4f outMat, Matrix4f modelView)
    {
		return outMat.identity().mul(RenderSystem.getProjectionMatrix()).mul(modelView).invert();
    }*/
}
