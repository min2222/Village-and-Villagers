package com.min01.villageandvillagers.entity.misc;

import com.min01.villageandvillagers.entity.AbstractOwnableEntity;
import com.min01.villageandvillagers.entity.IClipPos;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityHaybaleBarricade extends AbstractOwnableEntity<EntityHarvester> implements IClipPos
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(EntityHaybaleBarricade.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(EntityHaybaleBarricade.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DURABILITY = SynchedEntityData.defineId(EntityHaybaleBarricade.class, EntityDataSerializers.INT);
	
	public final AnimationState appearAnimationState = new AnimationState();
	public final AnimationState disappearAnimationState = new AnimationState();
	
	public EntityHaybaleBarricade(EntityType<?> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(ANIMATION_STATE, 0);
		this.entityData.define(ANIMATION_TICK, 0);
		this.entityData.define(DURABILITY, 100);
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
        			this.appearAnimationState.start(this.tickCount);
        			break;
        		}
        		case 2: 
        		{
        			this.stopAllAnimationStates();
        			this.disappearAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	public void stopAllAnimationStates() 
	{
		this.appearAnimationState.stop();
		this.disappearAnimationState.stop();
	}
	
	@Override
	public boolean isPickable() 
	{
		return true;
	}
	
	@Override
	public boolean canBeCollidedWith() 
	{
		return true;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		if(this.getDurability() <= 0 || this.tickCount >= 200)
		{
			if(this.getAnimationState() == 0)
			{
				this.setAnimationState(2);
				this.setAnimationTick(8);
			}
		}
		
		if(this.getAnimationTick() > 0)
		{
			this.setAnimationTick(this.getAnimationTick() - 1);
		}
		else
		{
			if(this.getAnimationState() == 1)
			{
				this.setAnimationState(0);
			}
			if(this.getAnimationState() == 2)
			{
				this.discard();
			}
		}
	}
	
	@Override
	public void setDeltaMovement(double p_20335_, double p_20336_, double p_20337_)
	{
		
	}
	
	@Override
	public Vec3 getDeltaMovement() 
	{
		return Vec3.ZERO;
	}

	@Override
	public boolean hurt(DamageSource p_19946_, float p_19947_) 
	{
		if(this.getDurability() > 0)
		{
			int damage = p_19946_.is(DamageTypeTags.IS_FIRE) ? 2 : 1;
			this.playSound(SoundEvents.GRASS_BREAK);
			this.setDurability(this.getDurability() - damage);
		}
		return p_19946_.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_)
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putInt("Durability", this.getDurability());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.contains("Durability"))
		{
			this.setDurability(p_37262_.getInt("Durability"));
		}
	}
	
	@Override
	public Vec3 getClipPos() 
	{
		return this.getEyePosition();
	}
	
	public void setDurability(int value)
	{
		this.entityData.set(DURABILITY, value);
	}
	
	public int getDurability()
	{
		return this.entityData.get(DURABILITY);
	}
	
    public void setAnimationTick(int value)
    {
        this.entityData.set(ANIMATION_TICK, value);
    }
    
    public int getAnimationTick()
    {
        return this.entityData.get(ANIMATION_TICK);
    }
    
    public void setAnimationState(int value)
    {
        this.entityData.set(ANIMATION_STATE, value);
    }
    
    public int getAnimationState()
    {
        return this.entityData.get(ANIMATION_STATE);
    }
}
