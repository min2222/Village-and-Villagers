package com.min01.minsvillageandvillagers.entity.villager;

import javax.annotation.Nullable;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.LookAtTradingPlayerGoal;
import net.minecraft.world.entity.ai.goal.TradeWithPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

public abstract class AbstractCombatVillager extends AbstractVillager
{
	private static final EntityDataAccessor<Boolean> IS_COMBAT = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.BOOLEAN);
	   
	public AbstractCombatVillager(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_) 
	{
		super(p_35267_, p_35268_);
		((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
		this.getNavigation().setCanFloat(true);
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(IS_COMBAT, false);
	}
	
	public void setCombatMode(boolean value)
	{
		this.entityData.set(IS_COMBAT, value);
	}
	
	public boolean isCombatMode()
	{
		return this.entityData.get(IS_COMBAT);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35D));
		this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
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
		if (!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby() && !this.isCombatMode())
		{
			if (p_35857_ == InteractionHand.MAIN_HAND) 
			{
				p_35856_.awardStat(Stats.TALKED_TO_VILLAGER);
			}

			if (this.getOffers().isEmpty()) 
			{
				return InteractionResult.sidedSuccess(this.level.isClientSide);
			} 
			else 
			{
				if (!this.level.isClientSide) 
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
		if (p_35859_.shouldRewardExp())
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
		if (avillagertrades$itemlisting != null && avillagertrades$itemlisting1 != null) 
		{
			MerchantOffers merchantoffers = this.getOffers();
			this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 5);
			int i = this.random.nextInt(avillagertrades$itemlisting1.length);
			VillagerTrades.ItemListing villagertrades$itemlisting = avillagertrades$itemlisting1[i];
			MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
			if (merchantoffer != null)
			{
				merchantoffers.add(merchantoffer);
			}
		}
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if(this.getTarget() != null && this.moveToTarget())
		{
			this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
			this.getNavigation().moveTo(this.getTarget(), this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
		}
	}
	
	public boolean moveToTarget()
	{
		return true;
	}
	
	public abstract VillagerTrades.ItemListing[] getVillagerTrades();
	
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() 
	{
		if (this.isSleeping()) 
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
}
