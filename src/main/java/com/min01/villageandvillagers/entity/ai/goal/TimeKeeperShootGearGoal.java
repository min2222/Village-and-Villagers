package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear.GearType;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class TimeKeeperShootGearGoal extends BasicAnimationSkillGoal<EntityTimeKeeper>
{
	public TimeKeeperShootGearGoal(EntityTimeKeeper mob) 
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
		return this.mob.distanceTo(this.mob.getTarget()) >= 6.0F;
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.getTarget() != null)
		{
			for(int i = 90; i > -90; i -= 36)
			{
				Vec2 rot = VillageUtil.lookAt(this.mob.getEyePosition(), this.mob.getTarget().getEyePosition());
				Vec3 lookPos = VillageUtil.getLookPos(new Vec2(this.mob.getXRot(), rot.y + i), this.mob.getEyePosition(), 0, 0, 1.5F);
				EntityTimeGear gear = new EntityTimeGear(VillageEntities.TIME_GEAR.get(), this.mob.level);
				gear.setLifeTime(200);
				gear.setGearType(GearType.TRACKING);
				gear.setNoGravity(true);
				gear.setOwner(this.mob);
				gear.setPos(lookPos);
				gear.shootFromRotation(this.mob, this.mob.getXRot(), rot.y + i, 0.0F, 0.5F, 1.0F);
				this.mob.level.addFreshEntity(gear);
			}
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
