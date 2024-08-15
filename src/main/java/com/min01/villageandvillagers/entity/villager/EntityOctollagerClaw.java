package com.min01.villageandvillagers.entity.villager;

import java.util.List;

import com.min01.villageandvillagers.entity.AbstractOwnableEntity;
import com.min01.villageandvillagers.util.KinematicChain;
import com.min01.villageandvillagers.util.VillagerUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EntityOctollagerClaw extends AbstractOwnableEntity<EntityDrOctollager>
{
	public static final EntityDataAccessor<Integer> INDEX = SynchedEntityData.defineId(EntityOctollagerClaw.class, EntityDataSerializers.INT);
	public final KinematicChain chain = new KinematicChain(this);
	
	public EntityOctollagerClaw(EntityType<?> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
		this.setNoGravity(true);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(INDEX, 0);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.move(MoverType.SELF, this.getDeltaMovement());
		if(this.getOwner() != null)
		{
			if(!this.getOwner().claws.contains(this))
			{
				this.getOwner().claws.add(this);
			}
			
			for(int i = 0; i < 40; i++)
			{
				this.chain.tick();
			}
			this.chain.setTarget(this.getOwner());
			
			if(this.getOwner().getTarget() == null)
			{
				this.setXRot(this.getOwner().getXRot());
				this.setYRot(this.getOwner().getYRot());
				
				Vec3 pos = this.getOwner().getClawPos(this.getIndex());
				if(this.distanceTo(this.getOwner()) > 5.0F)
				{
					this.setDeltaMovement(VillagerUtil.fromToVector(this.position(), pos, 0.05F));
				}
			}
			else
			{
				LivingEntity target = this.getOwner().getTarget();
				Vec3 pos = target.getEyePosition();
				Vec2 vec2 = VillagerUtil.lookAt(this.position(), target.getEyePosition());
				this.setXRot(vec2.x);
				this.setYRot(vec2.y);
				
				if(this.distanceTo(target) <= 15.0F)
				{
					this.setDeltaMovement(VillagerUtil.fromToVector(this.position(), pos, this.distanceTo(target) * 0.05F));
				}
				
				List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.15F));
				list.removeIf(t -> t == this.getOwner());
				list.forEach(t -> 
				{
					t.hurt(DamageSource.mobAttack(this.getOwner()), 1.5F);
				});
			}
		}
		else
		{
			this.discard();
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putInt("Index", this.getIndex());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_)
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.contains("Index"))
		{
			this.setIndex(p_37262_.getInt("Index"));
		}
	}
	
	public void setIndex(int value)
	{
		this.entityData.set(INDEX, value);
	}
	
	public int getIndex()
	{
		return this.entityData.get(INDEX);
	}
}
