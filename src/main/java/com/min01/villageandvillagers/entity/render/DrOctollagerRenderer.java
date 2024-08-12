package com.min01.villageandvillagers.entity.render;

import com.min01.villageandvillagers.entity.model.ModelDrOctollager;
import com.min01.villageandvillagers.entity.villager.EntityDrOctollager;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class DrOctollagerRenderer extends AbstractCombatVillagerRenderer<EntityDrOctollager, ModelDrOctollager>
{
	public DrOctollagerRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelDrOctollager(p_174304_.bakeLayer(ModelDrOctollager.LAYER_LOCATION)), 0.5F);
	}
	
	@Override
	protected RenderType getRenderType(EntityDrOctollager p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) 
	{
		return RenderType.entityTranslucent(this.getTextureLocation(p_115322_));
	}

	@Override
	public String getTextureName(EntityDrOctollager entity)
	{
		return "dr_octollager";
	}
}
