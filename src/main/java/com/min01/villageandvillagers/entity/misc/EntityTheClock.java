package com.min01.villageandvillagers.entity.misc;

import com.min01.villageandvillagers.entity.AbstractOwnableEntity;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityTheClock extends AbstractOwnableEntity<EntityTimeKeeper>
{
	public EntityTheClock(EntityType<?> p_19870_, Level p_19871_)
	{
		super(p_19870_, p_19871_);
	}
}
