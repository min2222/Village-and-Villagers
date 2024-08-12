package com.min01.villageandvillagers.entity.villager;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.level.Level;

public class EntityGrandmaster extends AbstractCombatVillager
{
	public EntityGrandmaster(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_) 
	{
		super(p_35267_, p_35268_);
	}

	@Override
	public ItemListing[] getVillagerTrades() 
	{
		return null;
	}
}
