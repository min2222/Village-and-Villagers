package com.min01.villageandvillagers.compat.jei;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.misc.SpecialVillagerProfessions;
import jeresources.registry.VillagerRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

@JeiPlugin
public class VillageJEIPlugin implements IModPlugin
{
	@Override
	public ResourceLocation getPluginUid() 
	{
		return new ResourceLocation(VillageandVillagers.MODID);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		if(ModList.get().isLoaded("jeresources"))
		{
			VillagerRegistry registry = VillagerRegistry.getInstance();
			SpecialVillagerProfessions.registerVillagerEntry(registry, VillageEntities.HARVESTER, SpecialVillagerProfessions.HARVESTER);
			SpecialVillagerProfessions.registerVillagerEntry(registry, VillageEntities.TIME_KEEPER, SpecialVillagerProfessions.TIME_KEEPER);
			SpecialVillagerProfessions.registerVillagerEntry(registry, VillageEntities.ROBIN_HOOD, SpecialVillagerProfessions.ROBIN_HOOD);
			SpecialVillagerProfessions.registerVillagerEntry(registry, VillageEntities.DAYDREAMER, SpecialVillagerProfessions.DAYDREAMER);
			SpecialVillagerProfessions.registerVillagerEntry(registry, VillageEntities.MARTIAL_ARTIST, SpecialVillagerProfessions.MARTIAL_ARTIST);
		}
	}
}
