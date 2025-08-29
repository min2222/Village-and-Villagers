package com.min01.villageandvillagers.entity.villager;

import com.min01.villageandvillagers.entity.ai.goal.HarvesterMeleeAttackGoal;
import com.min01.villageandvillagers.entity.ai.goal.HarvesterSummonBarricadeGoal;
import com.min01.villageandvillagers.misc.SmoothAnimationState;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityHarvester extends AbstractCombatVillager
{
	public final SmoothAnimationState stabAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState twoHandStabAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState stompAnimationState = new SmoothAnimationState();
	
	public EntityHarvester(EntityType<? extends Villager> p_35267_, Level p_35268_)
	{
		super(p_35267_, p_35268_);
		this.posArray = new Vec3[1];
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 40.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.5F)
        		.add(Attributes.ATTACK_DAMAGE, 5.0F)
        		.add(Attributes.FOLLOW_RANGE, 48.0F);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(4, new HarvesterMeleeAttackGoal(this));
    	this.goalSelector.addGoal(4, new HarvesterSummonBarricadeGoal(this));
    }
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.stabAnimationState.updateWhen(this.isUsingSkill(1), this.tickCount);
			this.twoHandStabAnimationState.updateWhen(this.isUsingSkill(2), this.tickCount);
			this.stompAnimationState.updateWhen(this.isUsingSkill(3), this.tickCount);
		}
		if(this.getTarget() != null)
		{
			if(this.canLook())
			{
				this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
			}
			if(this.canMove())
			{
				//TODO hide in barricade
				this.getNavigation().moveTo(this.getTarget(), this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
			}
		}
	}
	
	@Override
	public VillagerProfession getProfession()
	{
		return VillagerProfession.FARMER;
	}
	
	@Override
	public boolean isSpecial() 
	{
		return true;
	}
}
