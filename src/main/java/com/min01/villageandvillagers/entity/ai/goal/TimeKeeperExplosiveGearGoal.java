package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear.GearType;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;

public class TimeKeeperExplosiveGearGoal extends BasicAnimationSkillGoal<EntityTimeKeeper>
{
	public TimeKeeperExplosiveGearGoal(EntityTimeKeeper mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F;
	}

	@Override
	protected void performSkill() 
	{
		for(int i = 0; i < 10; i++)
		{
			EntityTimeGear gear = new EntityTimeGear(VillageEntities.TIME_GEAR.get(), this.mob.level);
			gear.setLifeTime(100);
			gear.setGearType(GearType.EXPLOSIVE);
			gear.setOwner(this.mob);
			gear.setPos(this.mob.getEyePosition());
			gear.setDeltaMovement(gear.getDeltaMovement().add(this.mob.level.random.nextGaussian() * 0.2, this.mob.level.random.nextGaussian() * 0.35, this.mob.level.random.nextGaussian() * 0.2));
			this.mob.level.addFreshEntity(gear);
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 10;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 60;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 5;
	}
}
