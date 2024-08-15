package com.min01.villageandvillagers.event;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.villager.EntityDrOctollager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge 
{
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		Entity entity = event.getEntity();
		if(entity instanceof IronGolem ironGolem)
		{
			ironGolem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(ironGolem, EntityDrOctollager.class, false, false));
		}
	}
}
