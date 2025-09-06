package com.min01.villageandvillagers.item;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.projectile.EntitySpecialArrow;
import com.min01.villageandvillagers.entity.projectile.EntitySpecialArrow.SpecialArrowType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpecialArrowItem extends ArrowItem
{
	private final SpecialArrowType arrowType;
	
	public SpecialArrowItem(Properties p_40512_, SpecialArrowType arrowType)
	{
		super(p_40512_);
		this.arrowType = arrowType;
	}

	@Override
	public AbstractArrow createArrow(Level p_40513_, ItemStack p_40514_, LivingEntity p_40515_)
	{
		EntitySpecialArrow arrow = new EntitySpecialArrow(VillageEntities.SPECIAL_ARROW.get(), p_40515_, p_40513_);
		arrow.setArrowType(this.arrowType);
		return arrow;
	}
	
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) 
	{
		return super.isInfinite(stack, bow, player) && this.arrowType.isInfinity();
	}
}
