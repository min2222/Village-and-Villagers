package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelDaydreamer;
import com.min01.villageandvillagers.entity.villager.EntityDaydreamer;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DaydreamerRenderer extends MobRenderer<EntityDaydreamer, ModelDaydreamer>
{
	public DaydreamerRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelDaydreamer(p_174304_.bakeLayer(ModelDaydreamer.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDaydreamer p_115812_) 
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/daydreamer.png");
	}
}
