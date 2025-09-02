package com.min01.villageandvillagers.entity.villager;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.min01.villageandvillagers.entity.AbstractAnimatableVillager;
import com.min01.villageandvillagers.misc.VillageTrades;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

public abstract class AbstractCombatVillager extends AbstractAnimatableVillager
{
	public static final EntityDataAccessor<Boolean> IS_COMBAT_MODE = SynchedEntityData.defineId(AbstractCombatVillager.class, EntityDataSerializers.BOOLEAN);
	
	public AbstractCombatVillager(EntityType<? extends Villager> p_35267_, Level p_35268_) 
	{
		super(p_35267_, p_35268_);
		this.setVillagerData(this.getVillagerData().setProfession(this.getProfession()));
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(IS_COMBAT_MODE, false);
	}
	
	@Override
	protected Brain<?> makeBrain(Dynamic<?> p_35445_)
	{
		Brain<Villager> brain = this.brainProvider().makeBrain(p_35445_);
		this.registerBrainGoals(brain);
		return brain;
	}

	@Override
	public void refreshBrain(ServerLevel p_35484_) 
	{
		Brain<Villager> brain = this.getBrain();
		brain.stopAll(p_35484_, this);
		this.brain = brain.copyWithoutBehaviors();
		this.registerBrainGoals(this.getBrain());
	}
	
	private void registerBrainGoals(Brain<Villager> p_35425_) 
	{
		VillagerProfession villagerprofession = this.getVillagerData().getProfession();
		p_35425_.setSchedule(Schedule.VILLAGER_DEFAULT);
		p_35425_.addActivityWithConditions(Activity.WORK, VillagerGoalPackages.getWorkPackage(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT)));

		p_35425_.addActivity(Activity.CORE, VillagerGoalPackages.getCorePackage(villagerprofession, 0.5F));
		p_35425_.addActivityWithConditions(Activity.MEET, VillagerGoalPackages.getMeetPackage(villagerprofession, 0.5F), ImmutableSet.of(Pair.of(MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT)));
		p_35425_.addActivity(Activity.REST, VillagerGoalPackages.getRestPackage(villagerprofession, 0.5F));
		p_35425_.addActivity(Activity.IDLE, VillagerGoalPackages.getIdlePackage(villagerprofession, 0.5F));
		p_35425_.setCoreActivities(ImmutableSet.of(Activity.CORE));
		p_35425_.setDefaultActivity(Activity.IDLE);
		p_35425_.setActiveActivityIfPossible(Activity.IDLE);
		p_35425_.updateActivityFromSchedule(this.level().getDayTime(), this.level().getGameTime());
	}
	
	@Override
	public void tick()
	{
		super.tick();
		
		if(!this.level.isClientSide)
		{
			if(this.getAnimationState() == 0)
			{
				this.setCombatMode(this.getTarget() != null);
			}
			if(this.isCombatMode())
			{
				this.getBrain().stopAll((ServerLevel) this.level, this);
			}
		}
    }
	
	@Override
	protected void updateTrades()
	{
		VillagerData villagerdata = this.getVillagerData();
		Int2ObjectMap<VillagerTrades.ItemListing[]> int2objectmap = VillageTrades.TRADES.get(villagerdata.getProfession());
		if(int2objectmap != null && !int2objectmap.isEmpty()) 
		{
			VillagerTrades.ItemListing[] avillagertrades$itemlisting = int2objectmap.get(villagerdata.getLevel());
			if(avillagertrades$itemlisting != null) 
			{
				MerchantOffers merchantoffers = this.getOffers();
				this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 2);
			}
		}
	}
	
	@Nullable
	@Override
	public Villager getBreedOffspring(ServerLevel p_150046_, AgeableMob p_150047_) 
	{
		return null;
	}
	
	@Override
	public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_) 
	{
		if(!this.isCombatMode())
		{
			return super.mobInteract(p_35856_, p_35857_);
		}
		return InteractionResult.PASS;
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
		this.setCombatMode(p_35290_.getBoolean("isCombatMode"));
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
    protected Component getTypeName()
    {
    	return this.getType().getDescription();
    }
    
	@Override
	public void setVillagerData(VillagerData p_35437_) 
	{
		boolean flag = this.isSpecial() ? !p_35437_.getProfession().equals(VillagerProfession.NONE) : true;
		if(flag)
		{
			super.setVillagerData(p_35437_);
		}
	}
	
	public abstract VillagerProfession getProfession();
	
	public boolean isSpecial()
	{
		return false;
	}
}
