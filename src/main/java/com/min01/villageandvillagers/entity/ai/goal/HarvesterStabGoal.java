package com.min01.villageandvillagers.entity.ai.goal;

import java.util.List;

import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HarvesterStabGoal extends BasicAnimationSkillGoal<EntityHarvester>
{
	public HarvesterStabGoal(EntityHarvester mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		if(Math.random() <= 0.5F)
		{
			this.mob.setAnimationState(2);
		}
		else
		{
			this.mob.setAnimationState(1);
		}
	}
	
	@Override
	public boolean additionalStartCondition() 
	{
		return this.mob.distanceTo(this.mob.getTarget()) <= 4.0F;
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.posArray[0] != null)
		{
			List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(1.5F));
			list.removeIf(t -> t == this.mob || t.isAlliedTo(this.mob) || VillageUtil.distanceTo(t, this.mob.posArray[0]) > 2.5F);
			list.forEach(t -> 
			{
				t.hurt(this.mob.damageSources().mobAttack(this.mob), (float) this.mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE));
			});
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
	protected int getSkillUsingInterval() 
	{
		return 60;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return this.mob.getAnimationState() == 1 ? 16 : 14;
	}
}
