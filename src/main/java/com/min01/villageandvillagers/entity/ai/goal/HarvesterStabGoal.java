package com.min01.villageandvillagers.entity.ai.goal;

import java.util.List;

import com.min01.villageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

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
		return this.mob.distanceTo(this.mob.getTarget()) <= 5.0F;
	}

	@Override
	protected void performSkill() 
	{
		if(this.mob.posArray[0] != null)
		{
			Vec3 size = new Vec3(9.5F / 16.0F, 3.5F / 16.0F, 9.5F / 16.0F);
			AABB aabb = new AABB(size.reverse().scale(0.5F), size.scale(0.5F)).move(this.mob.posArray[0]);
			List<LivingEntity> list = this.mob.level.getEntitiesOfClass(LivingEntity.class, aabb);
			list.removeIf(t -> t == this.mob || t.isAlliedTo(this.mob));
			list.forEach(t -> 
			{
				t.hurt(DamageSource.mobAttack(this.mob), (float) this.mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE));
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
		return this.mob.getAnimationState() == 1 ? 16 : 11;
	}
}
