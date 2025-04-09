package com.min01.villageandvillagers.mixin;

import javax.annotation.Nullable;

import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.min01.villageandvillagers.shader.ShaderEffectHandler;
import com.min01.villageandvillagers.shader.ExtendedPostChain;
import com.min01.villageandvillagers.shader.VillageShaders;
import com.min01.villageandvillagers.util.VillageClientUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.phys.Vec3;

@Mixin(LevelRenderer.class)
public abstract class MixinLevelRenderer
{
    private static final Matrix4f INVERSE_MAT = new Matrix4f();

    @Nullable
    @Shadow
    private ClientLevel level;
    
	@Inject(at = @At(value = "TAIL"), method = "renderLevel")
	private void renderLevel(PoseStack mtx, float frameTime, long nanoTime, boolean renderOutline, Camera camera, GameRenderer gameRenderer, LightTexture light, Matrix4f projMat, CallbackInfo ci)
	{
		ShaderEffectHandler.EFFECTS.forEach(t ->
		{
			if(t.enabled)
			{
				Vec3 camPos = camera.getPosition();
				Vec3 pos = t.pos.subtract(camPos);
				mtx.pushPose();
				mtx.translate(pos.x, pos.y, pos.z);
				if(t.effectName.equals("distortion"))
				{
					this.applyDistortion(mtx, frameTime);
				}
				if(t.effectName.equals("shockwave"))
				{
					this.applyShockwave(mtx, frameTime);
				}
				mtx.popPose();
			}
		});
		ShaderEffectHandler.EFFECTS.removeIf(t -> !t.enabled);
	}
	
	@Unique
	private void applyShockwave(PoseStack mtx, float frameTime)
	{
		Minecraft mc = VillageClientUtil.MC;

		ExtendedPostChain shaderChain = VillageShaders.getShockwave();
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.safeGetUniform("InverseTransformMatrix").set(this.getInverseTransformMatrix(INVERSE_MAT, mtx.last().pose()));
			shader.safeGetUniform("iTime").set((((float) (mc.level.getGameTime() % 2400000)) + frameTime) / 20.0F);
			shaderChain.process(frameTime);
			mc.getMainRenderTarget().bindWrite(false);
		}
	}
	
	@Unique
	private void applyDistortion(PoseStack mtx, float frameTime)
	{
		Minecraft mc = VillageClientUtil.MC;

		ExtendedPostChain shaderChain = VillageShaders.getDistortion();
		EffectInstance shader = shaderChain.getMainShader();

		if(shader != null)
		{
			shader.safeGetUniform("InverseTransformMatrix").set(this.getInverseTransformMatrix(INVERSE_MAT, mtx.last().pose()));
			shader.safeGetUniform("ViewMatrix").set(mtx.last().pose());
			shader.safeGetUniform("ProjectionMatrix").set(RenderSystem.getProjectionMatrix());
			shaderChain.process(frameTime);
			mc.getMainRenderTarget().bindWrite(false);
		}
	}
	
	@Unique
	private Matrix4f getInverseTransformMatrix(Matrix4f outMat, Matrix4f modelView)
    {
		return outMat.identity().mul(RenderSystem.getProjectionMatrix()).mul(modelView).invert();
    }
}
