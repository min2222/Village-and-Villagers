package com.min01.minsvillageandvillagers.misc;

import com.min01.minsvillageandvillagers.MinsVillageAndVillagers;
import com.min01.minsvillageandvillagers.entity.VillagerEntityTypes;
import com.min01.minsvillageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinsVillageAndVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler 
{
    @SubscribeEvent
    public static void createAttributes(EntityAttributeCreationEvent event) 
    {
    	event.put(VillagerEntityTypes.HARVESTER.get(), EntityHarvester.createAttributes().build());
    }
    
    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event)
    {
    	event.register(VillagerEntityTypes.HARVESTER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, Operation.AND);
    }
}
