package com.min01.villageandvillagers.event;

import java.util.List;

import com.min01.tickrateapi.util.TickrateUtil;
import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.item.VillageItems;
import com.min01.villageandvillagers.util.VillageUtil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge 
{
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		
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
}
