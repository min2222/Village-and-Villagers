package com.min01.villageandvillagers.entity.villager;

import java.util.ArrayList;
import java.util.List;

import com.min01.villageandvillagers.entity.VillagerEntities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class EntityDrOctollager extends AbstractCombatVillager
{
	public final List<EntityOctollagerClaw> claws = new ArrayList<>();
	
	public EntityDrOctollager(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_) 
	{
		super(p_35267_, p_35268_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 100)
    			.add(Attributes.MOVEMENT_SPEED, 0.5D)
        		.add(Attributes.ATTACK_DAMAGE, 7)
        		.add(Attributes.FOLLOW_RANGE, 30)
        		.add(Attributes.ARMOR, 10)
        		.add(Attributes.ARMOR_TOUGHNESS, 10);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
    	this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

	@Override
	public ItemListing[] getVillagerTrades() 
	{
		return null;
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_35282_, DifficultyInstance p_35283_, MobSpawnType p_35284_, SpawnGroupData p_35285_, CompoundTag p_35286_)
	{
		for(int i = 0; i < 4; i++)
		{
			EntityOctollagerClaw claw = new EntityOctollagerClaw(VillagerEntities.OCTOLLAGER_CLAW.get(), this.level);
			claw.setPos(this.getEyePosition().subtract(this.level.random.nextDouble() * 1.5, 0.5, this.level.random.nextDouble() * 1.5));
			claw.setOwner(this);
			this.level.addFreshEntity(claw);
		}
		return super.finalizeSpawn(p_35282_, p_35283_, p_35284_, p_35285_, p_35286_);
	}
}
