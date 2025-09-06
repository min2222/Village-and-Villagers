package com.min01.villageandvillagers.event;

import java.util.List;

import com.min01.tickrateapi.util.TickrateUtil;
import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.config.VillageConfig;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;
import com.min01.villageandvillagers.item.VillageItems;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import tallestegg.guardvillagers.entities.Guard;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge 
{	
	@SubscribeEvent
	public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event)
	{
		if(event.getEntity() instanceof Villager villager && event.getSpawnType() == MobSpawnType.STRUCTURE)
		{
			if(villager.level instanceof ServerLevel serverLevel)
			{
				if(serverLevel.structureManager().getStructureWithPieceAt(villager.blockPosition(), StructureTags.VILLAGE).isValid())
				{
					VillageUtil.convertVillagerToSpecial(VillageEntities.HARVESTER.get(), villager, event);
					VillageUtil.convertVillagerToSpecial(VillageEntities.ROBIN_HOOD.get(), villager, event);
				}
			}
		}
	}
	
	
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event)
	{
		if(VillageConfig.disableDamageVillagers.get())
		{
			LivingEntity entity = event.getEntity();
			Entity source = event.getSource().getDirectEntity();
			if(source != null)
			{
				if(source instanceof IronGolem || source instanceof AbstractCombatVillager || source instanceof Guard)
				{
					if(entity instanceof IronGolem || entity instanceof AbstractCombatVillager || entity instanceof Guard || entity instanceof AbstractVillager)
					{
						event.setCanceled(true);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingChangeTarget(LivingChangeTargetEvent event)
	{
		if(VillageConfig.disableDamageVillagers.get())
		{
			LivingEntity entity = event.getEntity();
			if(entity instanceof IronGolem || entity instanceof AbstractCombatVillager || entity instanceof Guard)
			{
				LivingEntity target = event.getNewTarget();
				if(target instanceof IronGolem || target instanceof AbstractCombatVillager || target instanceof Guard || target instanceof AbstractVillager)
				{
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onLevelTick(LevelTickEvent event)
	{
		Iterable<Entity> all = VillageUtil.getAllEntities(event.level);
		for(Entity entity : all)
		{
			if(!(entity instanceof LivingEntity) || !entity.getPersistentData().contains("ForceTickCount"))
			{
				continue;
			}
			int time = entity.getPersistentData().getInt("ForceTickCount");
			entity.getPersistentData().putInt("ForceTickCount", time - 1);
			TickrateUtil.setTickrate(entity, entity.getPersistentData().getInt("TickrateVV"));
			if(time <= 0)
			{
				entity.getPersistentData().remove("ForceTickCount");
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event)
	{
		LivingEntity living = event.getEntity();
		VillageUtil.getCurio(living, VillageItems.POCKET_WATCH.get(), t -> 
		{
			if(living instanceof Player player)
			{
				if(!player.getCooldowns().isOnCooldown(t.getItem()))
				{
					if(living.getHealth() <= 4.0F)
					{
						Entity entity = event.getSource().getEntity();
						if(entity != null)
						{
							VillageUtil.setTickrateWithTime(entity, 0, 60);
							player.getCooldowns().addCooldown(t.getItem(), 20 * 60);
						}
						else
						{
							List<LivingEntity> list = living.level.getEntitiesOfClass(LivingEntity.class, living.getBoundingBox().inflate(5.0F), target -> target != living && !living.isAlliedTo(target));
							list.forEach(target -> 
							{
								VillageUtil.setTickrateWithTime(target, 0, 100);
							});
							player.getCooldowns().addCooldown(t.getItem(), 20 * 90);
						}
					}
				}
			}
		});
	}
	
	public static class EmeraldForItems implements VillagerTrades.ItemListing 
	{
		private final Item item;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;
		private final int emeraldCount;
		private final float priceMultiplier;

		public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_, int emeraldCount) 
		{
			this.item = p_35657_.asItem();
			this.cost = p_35658_;
			this.maxUses = p_35659_;
			this.villagerXp = p_35660_;
			this.emeraldCount = emeraldCount;
			this.priceMultiplier = 0.05F;
		}

		@Override
		public MerchantOffer getOffer(Entity p_219682_, RandomSource p_219683_)
		{
			ItemStack itemstack = new ItemStack(this.item, this.cost);
			return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, this.emeraldCount), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}
}
