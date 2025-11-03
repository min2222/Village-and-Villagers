package com.min01.villageandvillagers.entity.ai.goal;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityRushingCow;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.item.VillageItems;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class HarvesterCowRushGoal extends BasicAnimationSkillGoal<EntityHarvester>
{
	public ItemStack stack = ItemStack.EMPTY;
	
	public HarvesterCowRushGoal(EntityHarvester mob) 
	{
		super(mob);
	}
	
	@Override
	public void start() 
	{
		super.start();
		this.mob.setAnimationState(4);
		this.mob.lookAt(Anchor.EYES, this.mob.getTarget().getEyePosition());
		this.stack = this.mob.getItemInHand(InteractionHand.OFF_HAND).copy();
		this.mob.setItemInHand(InteractionHand.OFF_HAND, VillageItems.COWBELL.get().getDefaultInstance());
	}
	
	@Override
	public boolean canUse() 
	{
		return super.canUse() && this.mob.distanceTo(this.mob.getTarget()) <= 8.0F && this.mob.onGround();
	}

	@Override
	protected void performSkill() 
	{
		this.mob.playSound(SoundEvents.BELL_BLOCK);
		
		for(int i = 0; i < 10; i++)
		{
			int yRot = this.mob.level.random.nextInt(35);
			Vec3 lookPos = VillageUtil.getLookPos(new Vec2(0.0F, this.mob.getYRot() + yRot), this.mob.position(), 0, 0, -40);
			Vec3 spreadPos = VillageUtil.getSpreadPosition(this.mob.level, lookPos, 4);
			Vec3 groundPos = VillageUtil.getGroundPos(this.mob.level, spreadPos.x, this.mob.getY() + 10, spreadPos.z).add(0.0F, 1.0F, 0.0F);
			Vec3 wantedPos = VillageUtil.getLookPos(new Vec2(0.0F, this.mob.getYRot() + yRot), this.mob.position(), 0, 0, 20);
			EntityRushingCow cow = new EntityRushingCow(VillageEntities.RUSHING_COW.get(), this.mob.level);
			cow.setOwner(this.mob);
			cow.setPos(groundPos);
			cow.setWantedPos(VillageUtil.getGroundPos(this.mob.level, wantedPos.x, this.mob.getY() + 10, wantedPos.z).add(0.0F, 1.0F, 0.0F));
			cow.lookAt(Anchor.FEET, cow.getWantedPos());
			this.mob.level.addFreshEntity(cow);
		}
	}
	
	@Override
	public void stop() 
	{
		super.stop();
		this.mob.setAnimationState(0);
		this.mob.setItemInHand(InteractionHand.OFF_HAND, this.stack.copy());
		this.stack = ItemStack.EMPTY;
	}

	@Override
	protected int getSkillUsingTime() 
	{
		return 20;
	}

	@Override
	protected int getSkillUsingInterval() 
	{
		return 500;
	}
}
