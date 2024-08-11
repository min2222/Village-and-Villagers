package com.min01.villageandvillagers.entity.render;

import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class HarvesterRenderer extends AbstractCombatVillagerRenderer<EntityHarvester, ModelHarvester>
{
	public HarvesterRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelHarvester(p_174304_.bakeLayer(ModelHarvester.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public String getTextureName(EntityHarvester entity) 
	{
		return "harvester";
	}
}
