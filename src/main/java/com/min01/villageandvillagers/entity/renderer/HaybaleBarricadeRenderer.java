package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.misc.EntityHaybaleBarricade;
import com.min01.villageandvillagers.entity.model.ModelHaybaleBarricade;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HaybaleBarricadeRenderer extends EntityRenderer<EntityHaybaleBarricade>
{
	public final ModelHaybaleBarricade model;
	public HaybaleBarricadeRenderer(Context p_174008_) 
	{
		super(p_174008_);
		this.model = new ModelHaybaleBarricade(p_174008_.bakeLayer(ModelHaybaleBarricade.LAYER_LOCATION));
	}
	
	@Override
	public void render(EntityHaybaleBarricade p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) 
	{
		p_114488_.pushPose();
		p_114488_.scale(-1.0F, -1.0F, 1.0F);
		p_114488_.translate(0.0F, -1.5F, 0.0F);
		p_114488_.mulPose(Axis.YP.rotationDegrees(p_114485_.getYRot() + 180.0F));
		this.model.setupAnim(p_114485_, 0, 0, p_114485_.tickCount + p_114487_, 0, 0);
		this.model.renderToBuffer(p_114488_, p_114489_.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(p_114485_))), p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		p_114488_.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHaybaleBarricade p_114482_)
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/haybale_barricade.png");
	}

}
