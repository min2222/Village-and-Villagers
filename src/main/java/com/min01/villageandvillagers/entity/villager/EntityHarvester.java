package com.min01.villageandvillagers.entity.villager;

import com.min01.villageandvillagers.entity.ai.goal.HarvesterStabGoal;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityHarvester extends AbstractCombatVillager
{
	public final AnimationState stabAnimationState = new AnimationState();
	public final AnimationState twoHandStabAnimationState = new AnimationState();
	
	public EntityHarvester(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_)
	{
		super(p_35267_, p_35268_);
		this.posArray = new Vec3[1];
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 60.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.5F)
        		.add(Attributes.ATTACK_DAMAGE, 6.5F)
        		.add(Attributes.FOLLOW_RANGE, 48.0F)
        		.add(Attributes.ARMOR, 3.0F)
        		.add(Attributes.ARMOR_TOUGHNESS, 3.0F);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(4, new HarvesterStabGoal(this));
    }
    
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) 
	{
        if(ANIMATION_STATE.equals(p_219422_) && this.level.isClientSide) 
        {
            switch(this.getAnimationState()) 
            {
        		case 0: 
        		{
        			this.stopAllAnimationStates();
        			break;
        		}
        		case 1: 
        		{
        			this.stopAllAnimationStates();
        			this.stabAnimationState.start(this.tickCount);
        			break;
        		}
        		case 2: 
        		{
        			this.stopAllAnimationStates();
        			this.twoHandStabAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates() 
	{
		this.stabAnimationState.stop();
		this.twoHandStabAnimationState.stop();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.getTarget() != null)
		{
			this.getLookControl().setLookAt(this.getTarget(), 30.0F, 30.0F);
			if(this.canMove())
			{
				this.getNavigation().moveTo(this.getTarget(), this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
			}
		}
	}

	@Override
	public ItemListing[] getVillagerTrades()
	{
		return VillagerTrades.TRADES.get(VillagerProfession.FARMER).get(1);
	}
}
