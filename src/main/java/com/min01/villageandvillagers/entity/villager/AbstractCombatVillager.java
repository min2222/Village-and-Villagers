package com.min01.villageandvillagers.entity.villager;

import javax.annotation.Nullable;

import com.min01.villageandvillagers.entity.IAnimatable;
import com.min01.villageandvillagers.entity.IPosArray;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractCombatVillager extends AbstractVillager implements IAnimatable, IPosArray
{
	public static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> CAN_MOVE = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_USING_SKILL = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> IS_COMBAT_MODE = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.BOOLEAN);
	
	public Vec3[] posArray;
	
	public AbstractCombatVillager(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_) 
	{
		super(p_35267_, p_35268_);
		((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
		this.getNavigation().setCanFloat(true);
		this.noCulling = true;
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(ANIMATION_STATE, 0);
		this.entityData.define(ANIMATION_TICK, 0);
		this.entityData.define(CAN_MOVE, true);
		this.entityData.define(IS_USING_SKILL, false);
		this.entityData.define(IS_COMBAT_MODE, false);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35D)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractCombatVillager.this.getTarget() == null;
			}
		});
		this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F)
		{
			@Override
			public boolean canUse()
			{
				return super.canUse() && AbstractCombatVillager.this.getTarget() == null;
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if(this.getAnimationTick() > 0)
		{
			this.setAnimationTick(this.getAnimationTick() - 1);
		}
		
		if(!this.level.isClientSide)
		{
			if(this.getAnimationState() == 0)
			{
				this.setCombatMode(this.getTarget() != null);
			}
		}
	}
	
	@Override
	public Vec3[] getPosArray() 
	{
		return this.posArray;
	}
	
	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_150046_, AgeableMob p_150047_) 
	{
		return null;
	}

	@Override
	public boolean showProgressBar()
	{
		return false;
	}
	
	@Override
	public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_) 
	{
		ItemStack itemstack = p_35856_.getItemInHand(p_35857_);
		if(!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby() && !this.isCombatMode())
		{
			if(p_35857_ == InteractionHand.MAIN_HAND) 
			{
				p_35856_.awardStat(Stats.TALKED_TO_VILLAGER);
			}

			if(this.getOffers().isEmpty()) 
			{
				return InteractionResult.sidedSuccess(this.level.isClientSide);
			} 
			else 
			{
				if(!this.level.isClientSide) 
				{
					this.setTradingPlayer(p_35856_);
					this.openTradingScreen(p_35856_, this.getDisplayName(), 1);
				}

				return InteractionResult.sidedSuccess(this.level.isClientSide);
			}
		}
		else
		{
			return super.mobInteract(p_35856_, p_35857_);
		}
	}
	
	@Override
	protected void rewardTradeXp(MerchantOffer p_35859_)
	{
		if(p_35859_.shouldRewardExp())
		{
			int i = 3 + this.random.nextInt(4);
			this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
		}
	}
	
	@Override
	protected void updateTrades() 
	{
		VillagerTrades.ItemListing[] avillagertrades$itemlisting = this.getVillagerTrades();
		VillagerTrades.ItemListing[] avillagertrades$itemlisting1 = this.getVillagerTrades();
		if(avillagertrades$itemlisting != null && avillagertrades$itemlisting1 != null) 
		{
			MerchantOffers merchantoffers = this.getOffers();
			this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 5);
			int i = this.random.nextInt(avillagertrades$itemlisting1.length);
			VillagerTrades.ItemListing villagertrades$itemlisting = avillagertrades$itemlisting1[i];
			MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
			if(merchantoffer != null)
			{
				merchantoffers.add(merchantoffer);
			}
		}
	}
	
	public abstract VillagerTrades.ItemListing[] getVillagerTrades();
	
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() 
	{
		if(this.isSleeping()) 
		{
			return null;
		} 
		else
		{
			return this.isTrading() ? SoundEvents.VILLAGER_TRADE : SoundEvents.VILLAGER_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_35498_) 
	{
		return SoundEvents.VILLAGER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.VILLAGER_DEATH;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_35301_) 
	{
		super.addAdditionalSaveData(p_35301_);
		p_35301_.putBoolean("isCombatMode", this.isCombatMode());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_35290_)
	{
		super.readAdditionalSaveData(p_35290_);
		if(p_35290_.contains("isCombatMode"))
		{
			this.setCombatMode(p_35290_.getBoolean("isCombatMode"));
		}
	}
	
	public void stopAllAnimationStates()
	{
		
	}
	
	public void setCombatMode(boolean value) 
	{
		this.entityData.set(IS_COMBAT_MODE, value);
	}
	
	@Override
	public boolean isCombatMode()
	{
		return this.entityData.get(IS_COMBAT_MODE);
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
}
