package com.min01.villageandvillagers.entity.render;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.AbstractCombatVillagerModel;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractCombatVillagerRenderer<T extends AbstractCombatVillager, M extends AbstractCombatVillagerModel<T>> extends MobRenderer<T, M>
{
	public AbstractCombatVillagerRenderer(Context p_174304_, M p_174305_, float p_174306_)
	{
		super(p_174304_, p_174305_, p_174306_);
		this.addLayer(new ItemInHandLayer<>(this, p_174304_.getItemInHandRenderer())
		{
			@Override
			public void render(PoseStack p_117204_, MultiBufferSource p_117205_, int p_117206_, T p_117207_, float p_117208_, float p_117209_, float p_117210_, float p_117211_, float p_117212_, float p_117213_) 
			{
				if(p_117207_.isCombatMode())
				{
					super.render(p_117204_, p_117205_, p_117206_, p_117207_, p_117208_, p_117209_, p_117210_, p_117211_, p_117212_, p_117213_);
				}
			}
		});
	}

	@Override
	public ResourceLocation getTextureLocation(T p_115812_)
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/" + this.getTextureName(p_115812_) + ".png");
	}
	
	public abstract String getTextureName(T entity);
}
