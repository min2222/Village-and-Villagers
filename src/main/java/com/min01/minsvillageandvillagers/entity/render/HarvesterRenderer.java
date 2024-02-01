package com.min01.minsvillageandvillagers.entity.render;

import com.min01.minsvillageandvillagers.MinsVillageAndVillagers;
import com.min01.minsvillageandvillagers.entity.model.ModelHarvester;
import com.min01.minsvillageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HarvesterRenderer extends MobRenderer<EntityHarvester, ModelHarvester>
{
	public HarvesterRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelHarvester(p_174304_.bakeLayer(ModelHarvester.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHarvester p_115812_) 
	{
		return new ResourceLocation(MinsVillageAndVillagers.MODID, "textures/entity/harvester.png");
	}
}
