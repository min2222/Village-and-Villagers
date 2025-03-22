package com.min01.villageandvillagers;

import com.min01.villageandvillagers.block.VillageBlocks;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.item.VillageItems;
import com.min01.villageandvillagers.misc.VillageCreativeTabs;
import com.min01.villageandvillagers.misc.VillageEntityDataSerializers;
import com.min01.villageandvillagers.network.VillageNetwork;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VillageandVillagers.MODID)
public class VillageandVillagers 
{
	public static final String MODID = "villageandvillagers";
	
	public VillageandVillagers()
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		VillageEntities.ENTITY_TYPES.register(bus);
		VillageItems.ITEMS.register(bus);
		VillageBlocks.BLOCKS.register(bus);
		VillageEntityDataSerializers.SERIALIZERS.register(bus);
		VillageCreativeTabs.CREATIVE_MODE_TAB.register(bus);
		
		VillageNetwork.registerMessages();
	}
}
