package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;

public class HarvesterMeleeAttackGoal extends BasicAnimationSkillGoal<EntityHarvester>
{
	public HarvesterMeleeAttackGoal(EntityHarvester mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.lookAt(Anchor.EYES, this.mob.getTarget().getEyePosition());
		if(Math.random() <= 0.5F)
		{
			this.mob.setAnimationState(2);
	    	this.skillWarmupDelay = this.adjustedTickDelay(14);
		}
		else
		{
			this.mob.setAnimationState(1);
		}
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && VillageUtil.isWithinMeleeAttackRange(this.mob, this.mob.getTarget(), 3.5F);
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.getTarget() != null)
		{
			if(VillageUtil.isWithinMeleeAttackRange(this.mob, this.mob.getTarget(), 3.5F))
			{
				this.mob.doHurtTarget(this.mob.getTarget());
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
		return 30;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 16;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 40;
	}
}
