package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityScarecrow;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class HarvesterSummonScarecrowGoal extends BasicAnimationSkillGoal<EntityHarvester>
{
	public HarvesterSummonScarecrowGoal(EntityHarvester mob) 
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
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) <= 6.0F && this.mob.onGround();
	}

	@Override
	protected void performSkill() 
	{
		Vec3 lookPos = VillageUtil.getLookPos(new Vec2(0.0F, this.mob.yBodyRot), this.mob.position(), 0.0F, 0.0F, 1.5F);
		EntityScarecrow scarecrow = new EntityScarecrow(VillageEntities.SCARECROW.get(), this.mob.level);
		scarecrow.setPos(VillageUtil.getGroundPos(this.mob.level, lookPos.x, this.mob.getY() + 5, lookPos.z).add(0.0F, 0.5F, 0.0F));
		scarecrow.setOwner(this.mob);
		scarecrow.lookAt(Anchor.EYES, this.mob.getEyePosition());
		this.mob.level.addFreshEntity(scarecrow);
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
		return 400;
	}
}
