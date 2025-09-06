package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.projectile.EntitySpecialArrow;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class SpecialArrowRenderer extends ArrowRenderer<EntitySpecialArrow>
{
	public SpecialArrowRenderer(Context p_173917_)
	{
		super(p_173917_);
	}

	@Override
	public ResourceLocation getTextureLocation(EntitySpecialArrow p_114482_)
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/" + p_114482_.getArrowType().getName() + ".png");
	}
}
