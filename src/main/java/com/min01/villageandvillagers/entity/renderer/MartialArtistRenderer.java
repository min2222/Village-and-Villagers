package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelMartialArtist;
import com.min01.villageandvillagers.entity.villager.EntityMartialArtist;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MartialArtistRenderer extends MobRenderer<EntityMartialArtist, ModelMartialArtist>
{
	public MartialArtistRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelMartialArtist(p_174304_.bakeLayer(ModelMartialArtist.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityMartialArtist p_115812_) 
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/martial_artist.png");
	}
}
