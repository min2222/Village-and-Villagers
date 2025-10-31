package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelLargeTimeGear;
import com.min01.villageandvillagers.entity.model.ModelTimeGear;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TimeGearRenderer extends EntityRenderer<EntityTimeGear>
{
	public final ModelTimeGear model;
	public final ModelLargeTimeGear modelLarge;
	public TimeGearRenderer(Context p_174008_) 
	{
		super(p_174008_);
		this.model = new ModelTimeGear(p_174008_.bakeLayer(ModelTimeGear.LAYER_LOCATION));
		this.modelLarge = new ModelLargeTimeGear(p_174008_.bakeLayer(ModelLargeTimeGear.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityTimeGear p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		p_114488_.pushPose();
		p_114488_.scale(-1.0F, -1.0F, 1.0F);
		p_114488_.translate(0.0F, -1.5F, 0.0F);
		float yRot = Mth.rotLerp(p_114487_, p_114485_.yRotO, p_114485_.getYRot());
		float xRot = Mth.lerp(p_114487_, p_114485_.xRotO, p_114485_.getXRot());
		if(p_114485_.getGearType().isLarge())
		{
			this.modelLarge.setupAnim(p_114485_, 0, 0, 0, yRot, xRot);
			this.modelLarge.renderToBuffer(p_114488_, p_114489_.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(p_114485_))), p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
		else
		{
			this.model.setupAnim(p_114485_, 0, 0, 0, yRot, xRot);
			this.model.renderToBuffer(p_114488_, p_114489_.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(p_114485_))), p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
		p_114488_.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityTimeGear p_114482_)
	{
		return p_114482_.getGearType().isLarge() ? new ResourceLocation(VillageandVillagers.MODID, "textures/entity/large_time_gear.png") : new ResourceLocation(VillageandVillagers.MODID, "textures/entity/time_gear.png");
	}

}
