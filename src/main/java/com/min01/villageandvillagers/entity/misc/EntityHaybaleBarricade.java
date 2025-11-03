package com.min01.villageandvillagers.entity.misc;

import com.min01.villageandvillagers.entity.AbstractOwnableEntity;
import com.min01.villageandvillagers.entity.IClipPos;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.misc.SmoothAnimationState;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class EntityHaybaleBarricade extends AbstractOwnableEntity<EntityHarvester> implements IClipPos
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(EntityHaybaleBarricade.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(EntityHaybaleBarricade.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DURABILITY = SynchedEntityData.defineId(EntityHaybaleBarricade.class, EntityDataSerializers.INT);
	
	public final SmoothAnimationState appearAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState disappearAnimationState = new SmoothAnimationState();
	
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
		
		if(this.level.isClientSide)
		{
			this.appearAnimationState.updateWhen(this.getAnimationState() == 1, this.tickCount);
			this.disappearAnimationState.updateWhen(this.getAnimationState() == 2, this.tickCount);
		}
		
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
    		for(int i = 0; i < 40; i++)
    		{
        		this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.HAY_BLOCK.defaultBlockState()), this.getX() + this.random.nextGaussian() * 0.5F, this.getY() + 0.5F, this.getZ() + this.random.nextGaussian() * 0.5F, this.random.nextGaussian() * 0.5F, this.random.nextGaussian() * 0.5F, this.random.nextGaussian() * 0.5F);
    		}
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
		this.setDurability(p_37262_.getInt("Durability"));
	}
	
	@Override
	public Vec3 getClipPos(Vec3 from, Vec3 to, Entity entity) 
	{
    	Vec3 motion = VillageUtil.fromToVector(from, to, (float) from.distanceTo(this.getEyePosition()) - 0.8F);
    	Vec3 pos = from.add(motion);
		return pos;
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
