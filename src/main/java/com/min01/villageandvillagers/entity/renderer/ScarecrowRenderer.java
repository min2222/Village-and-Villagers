package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.misc.EntityScarecrow;
import com.min01.villageandvillagers.entity.model.ModelScarecrow;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScarecrowRenderer extends MobRenderer<EntityScarecrow, ModelScarecrow>
{
	public ScarecrowRenderer(Context p_174008_) 
	{
		super(p_174008_, new ModelScarecrow(p_174008_.bakeLayer(ModelScarecrow.LAYER_LOCATION)), 0.5F);
	}
	
	@Override
	protected float getFlipDegrees(EntityScarecrow p_115337_)
	{
		return 0.0F;
	}

	@Override
	public ResourceLocation getTextureLocation(EntityScarecrow p_114482_)
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/scarecrow.png");
	}

}
