package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear.GearType;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;

public class TimeKeeperFallingGearGoal extends BasicAnimationSkillGoal<EntityTimeKeeper>
{
	public TimeKeeperFallingGearGoal(EntityTimeKeeper mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
	}
	
	@Override
	public boolean additionalStartCondition() 
	{
		return this.mob.distanceTo(this.mob.getTarget()) >= 8.0F;
	}

	@Override
	protected void performSkill() 
	{
		EntityTimeGear gear = new EntityTimeGear(VillageEntities.TIME_GEAR.get(), this.mob.level);
		gear.setLifeTime(200);
		gear.setGearType(GearType.FALLING);
		gear.setOwner(this.mob);
		gear.setPos(this.mob.getTarget().position().add(0, 40, 0));
		gear.setDeltaMovement(gear.getDeltaMovement().subtract(0, 1.5F, 0));
		this.mob.level.addFreshEntity(gear);
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
		return 120;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 5;
	}
}
