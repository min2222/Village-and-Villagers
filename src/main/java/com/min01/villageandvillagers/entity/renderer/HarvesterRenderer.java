package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.renderer.layer.VillagerItemInHandLayer;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HarvesterRenderer extends MobRenderer<EntityHarvester, ModelHarvester>
{
	public HarvesterRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelHarvester(p_174304_.bakeLayer(ModelHarvester.LAYER_LOCATION)), 0.5F);
		this.addLayer(new VillagerItemInHandLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHarvester p_115812_) 
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/harvester.png");
	}
}
