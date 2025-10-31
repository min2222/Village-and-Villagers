package com.min01.villageandvillagers.entity.misc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.villageandvillagers.misc.VillageEntityDataSerializers;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityRushingCow extends Cow
{
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(EntityRushingCow.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Vec3> WANTED_POS = SynchedEntityData.defineId(EntityRushingCow.class, VillageEntityDataSerializers.VEC3.get());
	
	public EntityRushingCow(EntityType<? extends Cow> p_28285_, Level p_28286_) 
	{
		super(p_28285_, p_28286_);
		this.setInvulnerable(true);
	}
	
	@Override
	protected void registerGoals() 
	{
		
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(OWNER_UUID, Optional.empty());
		this.entityData.define(WANTED_POS, Vec3.ZERO);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		if(!this.getWantedPos().equals(Vec3.ZERO))
		{
			if(this.getWantedPos().subtract(this.position()).length() <= 2.5F || this.tickCount >= 200)
			{
				this.discard();
			}
			else
			{
				Vec3 wantedPos = this.getWantedPos();
				this.getNavigation().moveTo(wantedPos.x, wantedPos.y, wantedPos.z, 3.0F);
				this.getLookControl().setLookAt(wantedPos);
				
	    		for(int i = 0; i < 10; i++)
	    		{
	    			this.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX() + this.random.nextGaussian() * 0.1F, this.getY(), this.getZ() + this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F);
	        		this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, this.getBlockStateOn()), this.getX() + this.random.nextGaussian() * 0.1F, this.getY(), this.getZ() + this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F);
	    		}
	    		
	    		if(this.getOwner() != null)
	    		{
	    			List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.5F), t -> t != this.getOwner() && !t.isAlliedTo(this.getOwner()) && !(t instanceof EntityRushingCow));
	    			list.forEach(t ->
	    			{
	    				if(t.hurt(this.damageSources().mobAttack(this), 6.0F))
	    				{
	    					
	    				}
	    			});
	    		}
			}
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		super.addAdditionalSaveData(p_37265_);
		if(this.entityData.get(OWNER_UUID).isPresent())
		{
			p_37265_.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_37262_.getUUID("Owner")));
		}
	}
	
	public void setWantedPos(Vec3 pos)
	{
		this.entityData.set(WANTED_POS, pos);
	}
	
	public Vec3 getWantedPos()
	{
		return this.entityData.get(WANTED_POS);
	}
	
	public void setOwner(Entity owner)
	{
		this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
	}
	
	@Nullable
	public Entity getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return VillageUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
}
