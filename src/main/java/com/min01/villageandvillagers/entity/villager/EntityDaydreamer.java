package com.min01.villageandvillagers.entity.villager;

import com.min01.villageandvillagers.misc.VillageProfessions;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.Level;

public class EntityDaydreamer extends AbstractCombatVillager
{
	public EntityDaydreamer(EntityType<? extends Villager> p_35267_, Level p_35268_)
	{
		super(p_35267_, p_35268_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 30.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.75F)
        		.add(Attributes.ATTACK_DAMAGE, 2.0F)
        		.add(Attributes.FOLLOW_RANGE, 48.0F);
    }
    
    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
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
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates() 
	{
		
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
	public VillagerProfession getProfession()
	{
		return VillageProfessions.DAYDREAMER.get();
	}
	
	@Override
	public boolean isSpecial() 
	{
		return true;
	}
}
