package com.min01.villageandvillagers.entity;

import com.min01.villageandvillagers.entity.ai.navigation.FixedPathNavigation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAnimatableVillager extends Villager implements IAnimatable, IPosArray
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractAnimatableVillager.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractAnimatableVillager.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> CAN_LOOK = SynchedEntityData.defineId(AbstractAnimatableVillager.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> CAN_MOVE = SynchedEntityData.defineId(AbstractAnimatableVillager.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(AbstractAnimatableVillager.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_USING_SKILL = SynchedEntityData.defineId(AbstractAnimatableVillager.class, EntityDataSerializers.BOOLEAN);

	public Vec3[] posArray;
	
	public AbstractAnimatableVillager(EntityType<? extends Villager> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
		this.noCulling = true;
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ANIMATION_STATE, 0);
		this.entityData.define(ANIMATION_TICK, 0);
		this.entityData.define(CAN_LOOK, true);
		this.entityData.define(CAN_MOVE, true);
		this.entityData.define(HAS_TARGET, false);
		this.entityData.define(IS_USING_SKILL, false);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableVillager.this.canLookAround();
			}
		});
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableVillager.this.canRandomStroll();
			}
		});
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableVillager.this.canLookAround();
			}
		});
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractAnimatableVillager.this.canLookAround();
			}
		});
	}
    
    @Override
    public void tick()
    {
    	super.tick();

		if(!this.level.isClientSide)
		{
			this.setHasTarget(this.getTarget() != null);
		}
		
		if(this.getAnimationTick() > 0)
		{
			this.setAnimationTick(this.getAnimationTick() - 1);
		}
		
		if(this.entityData.get(IS_USING_SKILL) && this.getAnimationTick() <= 0)
		{
			this.setAnimationState(0);
			this.setUsingSkill(false);
		}
    }
    
    @Override
    protected PathNavigation createNavigation(Level p_21480_)
    {
    	return new FixedPathNavigation(this, p_21480_);
    }
	
	public boolean canLookAround()
	{
		return !this.hasTarget();
	}
	
	public boolean canRandomStroll()
	{
		return !this.hasTarget();
	}
	
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	this.setUsingSkill(p_21450_.getBoolean("isUsingSkill"));
    	this.setCanLook(p_21450_.getBoolean("CanLook"));
    	this.setCanMove(p_21450_.getBoolean("CanMove"));
    	this.setAnimationTick(p_21450_.getInt("AnimationTick"));
    	this.setAnimationState(p_21450_.getInt("AnimationState"));
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) 
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putBoolean("isUsingSkill", this.isUsingSkill());
    	p_21484_.putBoolean("CanLook", this.canLook());
    	p_21484_.putBoolean("CanMove", this.canMove());
    	p_21484_.putInt("AnimationTick", this.getAnimationTick());
    	p_21484_.putInt("AnimationState", this.getAnimationState());
    }
    
    @Override
    public Vec3[] getPosArray()
    {
    	return this.posArray;
    }
	
	public void setHasTarget(boolean value)
	{
		this.entityData.set(HAS_TARGET, value);
	}
	
	public boolean hasTarget()
	{
		return this.entityData.get(HAS_TARGET);
	}
	
	@Override
	public void setUsingSkill(boolean value) 
	{
		this.entityData.set(IS_USING_SKILL, value);
	}
	
	@Override
	public boolean isUsingSkill() 
	{
		return this.getAnimationTick() > 0 || this.entityData.get(IS_USING_SKILL);
	}
	
    public void setCanLook(boolean value)
    {
    	this.entityData.set(CAN_LOOK, value);
    }
    
    public boolean canLook()
    {
    	return this.entityData.get(CAN_LOOK);
    }
    
    @Override
    public void setCanMove(boolean value)
    {
    	this.entityData.set(CAN_MOVE, value);
    }
    
    @Override
    public boolean canMove()
    {
    	return this.entityData.get(CAN_MOVE);
    }
    
    @Override
    public void setAnimationTick(int value)
    {
        this.entityData.set(ANIMATION_TICK, value);
    }
    
    @Override
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
    
    public boolean isUsingSkill(int state)
    {
    	return this.getAnimationState() == state && this.isUsingSkill();
    }
}
