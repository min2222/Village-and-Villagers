package com.min01.villageandvillagers.entity.projectile;

import java.util.UUID;

import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class EntityTimeGear extends ThrowableProjectile
{
	public EntityTimeGear(EntityType<? extends ThrowableProjectile> p_37466_, Level p_37467_)
	{
		super(p_37466_, p_37467_);
	}

	@Override
	protected void defineSynchedData()
	{
		
	}
	
	@Override
	public Entity getOwner()
	{
		UUID ownerUUID = ObfuscationReflectionHelper.getPrivateValue(Projectile.class, this, "f_37244_");
		if(ownerUUID != null)
		{
			return VillageUtil.getEntityByUUID(this.level, ownerUUID);
		}
		return super.getOwner();
	}
	
	public static enum GearType
	{
		GIANT, //giant gear that fall from sky
		TRACKING, //small gear that track nearby enemies
		SHIELD, //medium gear work like tf shield
		VEHICLE //large gear that time keeper use for flying
	}
}
