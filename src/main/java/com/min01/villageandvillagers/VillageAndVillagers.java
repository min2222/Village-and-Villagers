package com.min01.villageandvillagers;

import com.min01.villageandvillagers.entity.VillagerEntities;
import com.min01.villageandvillagers.item.VillagerItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VillageAndVillagers.MODID)
public class VillageAndVillagers 
{
	public static final String MODID = "villageandvillagers";
	
	public VillageAndVillagers()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		VillagerEntities.ENTITY_TYPES.register(bus);
		VillagerItems.ITEMS.register(bus);
	}
}
