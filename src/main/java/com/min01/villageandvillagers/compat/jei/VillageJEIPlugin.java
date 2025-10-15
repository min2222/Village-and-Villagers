package com.min01.villageandvillagers.compat.jei;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.compat.jer.SpecialVillagerEntry;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;
import com.min01.villageandvillagers.misc.SpecialVillagerProfessions;

import jeresources.registry.VillagerRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;

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
			registerVillagerEntry(registry, VillageEntities.HARVESTER, SpecialVillagerProfessions.HARVESTER);
			registerVillagerEntry(registry, VillageEntities.TIME_KEEPER, SpecialVillagerProfessions.TIME_KEEPER);
			registerVillagerEntry(registry, VillageEntities.ROBIN_HOOD, SpecialVillagerProfessions.ROBIN_HOOD);
			registerVillagerEntry(registry, VillageEntities.DAYDREAMER, SpecialVillagerProfessions.DAYDREAMER);
			registerVillagerEntry(registry, VillageEntities.MARTIAL_ARTIST, SpecialVillagerProfessions.MARTIAL_ARTIST);
		}
	}
	
	public static <T extends AbstractCombatVillager> void registerVillagerEntry(VillagerRegistry registry, RegistryObject<EntityType<T>> entityType, RegistryObject<VillagerProfession> profession)
	{
		registry.addVillagerEntry(new SpecialVillagerEntry(() -> entityType.get(), profession.get(), SpecialVillagerProfessions.getTrades(profession.get())));
	}
}
