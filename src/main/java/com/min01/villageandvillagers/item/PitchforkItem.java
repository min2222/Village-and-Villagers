package com.min01.villageandvillagers.item;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public class PitchforkItem extends HoeItem
{
	public PitchforkItem() 
	{
		super(Tiers.IRON, 3, -2.4F, new Item.Properties().durability(250));
	}
}
