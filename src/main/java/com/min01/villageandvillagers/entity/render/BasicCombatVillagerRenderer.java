package com.min01.villageandvillagers.entity.render;

import com.min01.villageandvillagers.entity.model.AbstractCombatVillagerModel;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class BasicCombatVillagerRenderer<T extends AbstractCombatVillager, M extends AbstractCombatVillagerModel<T>> extends AbstractCombatVillagerRenderer<T, M>
{
	private final String textureName;
	
	public BasicCombatVillagerRenderer(Context p_174304_, M p_174305_, String textureName)
	{
		super(p_174304_, p_174305_, 0.5F);
		this.textureName = textureName;
	}
	
	@Override
	public String getTextureName(T entity)
	{
		return this.textureName;
	}
}
