package com.min01.villageandvillagers.event;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.misc.EntityScarecrow;
import com.min01.villageandvillagers.entity.villager.EntityDaydreamer;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.entity.villager.EntityMartialArtist;
import com.min01.villageandvillagers.entity.villager.EntityRobinHood;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;

import net.minecraft.world.entity.animal.Cow;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler 
{
	@SubscribeEvent
	public static void onFMLCommonSetup(FMLCommonSetupEvent event)
	{
		
	}
	
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) 
    {
    	event.put(VillageEntities.HARVESTER.get(), EntityHarvester.createAttributes().build());
    	event.put(VillageEntities.TIME_KEEPER.get(), EntityTimeKeeper.createAttributes().build());
    	event.put(VillageEntities.ROBIN_HOOD.get(), EntityRobinHood.createAttributes().build());
    	event.put(VillageEntities.DAYDREAMER.get(), EntityDaydreamer.createAttributes().build());
    	event.put(VillageEntities.MARTIAL_ARTIST.get(), EntityMartialArtist.createAttributes().build());
    	event.put(VillageEntities.SCARECROW.get(), EntityScarecrow.createAttributes().build());
    	event.put(VillageEntities.RUSHING_COW.get(), Cow.createAttributes().build());
    }
    
    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event)
    {
    	
    }
}
