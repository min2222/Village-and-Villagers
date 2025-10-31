package com.min01.villageandvillagers.entity.projectile;

import java.util.List;
import java.util.UUID;

import com.min01.villageandvillagers.entity.EntityCameraShake;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class EntityTimeGear extends ThrowableProjectile
{
	public static final EntityDataAccessor<Integer> GEAR_TYPE = SynchedEntityData.defineId(EntityTimeGear.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> LIFE_TIME = SynchedEntityData.defineId(EntityTimeGear.class, EntityDataSerializers.INT);
	
	public EntityTimeGear(EntityType<? extends ThrowableProjectile> p_37466_, Level p_37467_)
	{
		super(p_37466_, p_37467_);
	}

	@Override
	protected void defineSynchedData()
	{
		this.entityData.define(GEAR_TYPE, 0);
		this.entityData.define(LIFE_TIME, 0);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.refreshDimensions();
		if(this.getOwner() == null)
		{
			this.discard();
		}
		else
		{
			Mob owner = (Mob) this.getOwner();
			if(this.getGearType() == GearType.TRACKING)
			{
				if(owner.getTarget() != null && this.tickCount >= 40 && this.tickCount <= 120)
				{
					this.setDeltaMovement(VillageUtil.fromToVector(this.position(), owner.getTarget().getEyePosition(), 0.5F));
				}
			}
		}

		if(this.tickCount >= this.getLifeTime())
		{
			this.discard();
		}
	}
	
	@Override
	public EntityDimensions getDimensions(Pose p_19975_) 
	{
		return this.getGearType().isLarge() ? EntityDimensions.scalable(3.0F, 3.0F) : super.getDimensions(p_19975_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_37258_)
	{
		super.onHitBlock(p_37258_);
		if(this.getGearType() == GearType.FALLING && !this.onGround())
		{
			EntityCameraShake.cameraShake(this.level, this.position(), 20, 0.15F, 0, 10);
			for(int i = 0; i < 360; i++)
			{
				Vec3 lookPos = VillageUtil.getLookPos(new Vec2(0, i), p_37258_.getLocation(), 0, 0.5F, 3.5F);
				Vec3 lookPos2 = VillageUtil.getLookPos(new Vec2(0, i), p_37258_.getLocation(), 0, 0.5F, 10.0F);
				Vec3 motion = VillageUtil.fromToVector(lookPos, lookPos2, 1.5F);
				this.level.addAlwaysVisibleParticle(ParticleTypes.CLOUD, lookPos.x, lookPos.y, lookPos.z, motion.x, motion.y, motion.z);
			}
			if(this.getOwner() != null)
			{
				List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.5F), t -> t != this.getOwner() && !t.isAlliedTo(this.getOwner()));
				list.forEach(t -> 
				{
					t.hurt(this.damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), 15.0F);
				});
			}
			this.setDeltaMovement(Vec3.ZERO);
			this.setLifeTime(this.tickCount + 60);
			this.setOnGround(true);
		}
		if(this.getGearType() == GearType.TRACKING)
		{
			this.discard();
		}
		if(this.getGearType() == GearType.EXPLOSIVE)
		{
			this.playSound(SoundEvents.GENERIC_EXPLODE);
			this.discard();
		}
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_37259_) 
	{
		if(this.getGearType() == GearType.TRACKING)
		{
			if(this.getOwner() != null)
			{
				Entity entity = p_37259_.getEntity();
				if(entity != this.getOwner() && !entity.isAlliedTo(this.getOwner()))
				{
					entity.hurt(this.damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), 5.0F);
					this.discard();
				}
			}
		}
	}
	
	@Override
	public Entity getOwner()
	{
		UUID ownerUUID = ObfuscationReflectionHelper.getPrivateValue(Projectile.class, this, "f_37244_");
		if(ownerUUID != null)
		{
			return VillageUtil.getEntityByUUID(this.level, ownerUUID);
		}
		return super.getOwner();
	}
	
	@Override
	protected void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putInt("GearType", this.entityData.get(GEAR_TYPE));
		p_37265_.putInt("LifeTime", this.getLifeTime());
	}
	
	@Override
	protected void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.contains("GearType"))
		{
			this.setGearType(GearType.values()[p_37262_.getInt("GearType")]);
		}
		if(p_37262_.contains("LifeTime"))
		{
			this.setLifeTime(p_37262_.getInt("LifeTime"));
		}
	}
	
	public void setLifeTime(int value)
	{
		this.entityData.set(LIFE_TIME, value);
	}
	
	public int getLifeTime()
	{
		return this.entityData.get(LIFE_TIME);
	}
	
	public void setGearType(GearType type)
	{
		this.entityData.set(GEAR_TYPE, type.ordinal());
	}
	
	public GearType getGearType()
	{
		return GearType.values()[this.entityData.get(GEAR_TYPE)];
	}
	
	public static enum GearType
	{
		TRACKING(false),
		FALLING(true),
		EXPLOSIVE(false);
		
		private boolean isLarge;
		
		private GearType(boolean isLarge)
		{
			this.isLarge = isLarge;
		}
		
		public boolean isLarge()
		{
			return this.isLarge;
		}
	}
}
