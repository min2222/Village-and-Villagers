package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityHaybaleBarricade;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

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
		this.mob.lookAt(Anchor.EYES, this.mob.getTarget().getEyePosition());
		this.mob.setAnimationState(3);
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) <= 10.0F && this.mob.onGround();
	}

	@Override
	protected void performSkill() 
	{
		Vec3 lookPos = VillageUtil.getLookPos(new Vec2(0.0F, this.mob.yBodyRot), this.mob.position(), 0.0F, 0.0F, 1.5F);
		EntityHaybaleBarricade barricade = new EntityHaybaleBarricade(VillageEntities.HAYBALE_BARRICADE.get(), this.mob.level);
		barricade.setOwner(this.mob);
		barricade.setPos(VillageUtil.getGroundPos(this.mob.level, lookPos.x, this.mob.getY() + 5, lookPos.z).add(0.0F, 0.5F, 0.0F));
		barricade.setAnimationState(1);
		barricade.setAnimationTick(8);
		barricade.setYRot(this.mob.yBodyRot);
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
	protected int getSkillWarmupTime() 
	{
		return 17;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 300;
	}
}
