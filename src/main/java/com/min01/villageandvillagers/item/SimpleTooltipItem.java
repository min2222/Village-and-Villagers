package com.min01.villageandvillagers.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class SimpleTooltipItem extends Item
{
	private final String key;
	public SimpleTooltipItem(String key, Item.Properties properties) 
	{
		super(properties);
		this.key = key;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) 
	{
		p_41423_.add(Component.translatable(this.key).withStyle(ChatFormatting.DARK_PURPLE));
	}
}
