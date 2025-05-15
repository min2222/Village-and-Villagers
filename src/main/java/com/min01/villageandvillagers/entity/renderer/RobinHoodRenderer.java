package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelRobinHood;
import com.min01.villageandvillagers.entity.villager.EntityRobinHood;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RobinHoodRenderer extends MobRenderer<EntityRobinHood, ModelRobinHood>
{
	public RobinHoodRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelRobinHood(p_174304_.bakeLayer(ModelRobinHood.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityRobinHood p_115812_) 
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/robin_hood.png");
	}
}
