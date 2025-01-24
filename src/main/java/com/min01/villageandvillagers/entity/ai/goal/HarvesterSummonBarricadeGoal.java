package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityHaybaleBarricade;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.world.phys.Vec2;

public class HarvesterSummonBarricadeGoal extends BasicAnimationSkillGoal<EntityHarvester>
{
	public HarvesterSummonBarricadeGoal(EntityHarvester mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(3);
	}
	
	@Override
	public boolean additionalStartCondition() 
	{
		return this.mob.distanceTo(this.mob.getTarget()) >= 6.0F && this.mob.isOnGround();
	}

	@Override
	protected void performSkill() 
	{
		EntityHaybaleBarricade barricade = new EntityHaybaleBarricade(VillageEntities.HAYBALE_BARRICADE.get(), this.mob.level);
		barricade.setOwner(this.mob);
		barricade.setPos(VillageUtil.getLookPos(new Vec2(0.0F, this.mob.yBodyRot), this.mob.position(), 0.0F, 0.0F, 1.5F));
		barricade.setAnimationState(1);
		barricade.setAnimationTick(8);
		barricade.setYRot(this.mob.getYHeadRot());
		this.mob.level.addFreshEntity(barricade);
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
		return 25;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 100;
	}
	
	@Override
	protected int getSkillWarmupTime() 
	{
		return 17;
	}
}
