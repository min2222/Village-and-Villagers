package com.min01.villageandvillagers.entity;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class AbstractOwnableCreature<T extends Entity> extends AbstractAnimatableCreature
{
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(AbstractOwnableCreature.class, EntityDataSerializers.OPTIONAL_UUID);
	
	public AbstractOwnableCreature(EntityType<? extends PathfinderMob> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(OWNER_UUID, Optional.empty());
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
	
	public void setOwner(T owner)
	{
		this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
	}
	
	@Nullable
	public T getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return VillageUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
}
