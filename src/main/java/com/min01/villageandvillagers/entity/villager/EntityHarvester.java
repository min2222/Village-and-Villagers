package com.min01.villageandvillagers.entity.villager;

import com.min01.villageandvillagers.entity.VillagerEntities;
import com.min01.villageandvillagers.entity.projectile.EntityThrownSapling;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class EntityHarvester extends AbstractCombatVillager
{
	public EntityHarvester(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_)
	{
		super(p_35267_, p_35268_);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_HOE));
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 40)
    			.add(Attributes.MOVEMENT_SPEED, 0.5D)
        		.add(Attributes.ATTACK_DAMAGE, 4)
        		.add(Attributes.FOLLOW_RANGE, 30)
        		.add(Attributes.ARMOR, 3)
        		.add(Attributes.ARMOR_TOUGHNESS, 3);
    }

	@Override
	public ItemListing[] getVillagerTrades() 
	{
		return VillagerTrades.TRADES.get(VillagerProfession.FARMER).get(1);
	}
	
	@Override
	public void aiStep() 
	{
		super.aiStep();
		
		if(this.getUnhappyCounter() >= 3)
		{
			this.setUnhappyCounter(0);
			this.setCombatMode(true);
			if(this.getLastHurtByMob() != null)
			{
				this.setTarget(this.getLastHurtByMob());
			}
		}
		
		if(this.isCombatMode())
		{
			if(this.getTarget() != null)
			{
				if(this.tickCount % 100 == 0)
				{
					EntityThrownSapling sapling = new EntityThrownSapling(VillagerEntities.THROWN_SAPLING.get(), this, this.level);
					sapling.setPos(this.position().add(0, this.getEyeHeight(), 0));
					sapling.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 1.5F, 1.0F);
					this.level.addFreshEntity(sapling);
				}
			}
		}
	}
	
	@Override
	public boolean hurt(DamageSource p_21016_, float p_21017_)
	{
		Entity entity = p_21016_.getDirectEntity();
		if(!this.isCombatMode() && entity != null)
		{
			boolean flag = entity instanceof Player player ? !player.getAbilities().instabuild : !entity.isAlliedTo(this);
			if(flag)
			{
				this.setUnhappyCounter(this.getUnhappyCounter() + 1);
			}
		}
		return super.hurt(p_21016_, p_21017_);
	}
}
