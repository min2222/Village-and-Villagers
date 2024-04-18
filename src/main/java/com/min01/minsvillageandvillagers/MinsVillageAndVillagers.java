package com.min01.minsvillageandvillagers;

import com.min01.minsvillageandvillagers.entity.VillagerEntities;
import com.min01.minsvillageandvillagers.item.VillagerItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MinsVillageAndVillagers.MODID)
public class MinsVillageAndVillagers 
{
	public static final String MODID = "minsvillageandvillagers";
	
	public MinsVillageAndVillagers()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		VillagerEntities.ENTITY_TYPES.register(bus);
		VillagerItems.ITEMS.register(bus);
	}
}
