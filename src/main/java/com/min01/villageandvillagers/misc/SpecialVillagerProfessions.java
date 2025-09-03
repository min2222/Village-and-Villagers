package com.min01.villageandvillagers.misc;

import com.google.common.collect.ImmutableSet;
import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.compat.jer.SpecialVillagerEntry;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import jeresources.registry.VillagerRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpecialVillagerProfessions 
{
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, VillageandVillagers.MODID);

	public static final RegistryObject<VillagerProfession> HARVESTER = registerSpecialProfession("harvester");
	public static final RegistryObject<VillagerProfession> TIME_KEEPER = registerSpecialProfession("time_keeper");
	public static final RegistryObject<VillagerProfession> ROBIN_HOOD = registerSpecialProfession("robin_hood");
	public static final RegistryObject<VillagerProfession> DAYDREAMER = registerSpecialProfession("daydreamer");
	public static final RegistryObject<VillagerProfession> MARTIAL_ARTIST = registerSpecialProfession("martial_artist");
	
	public static Int2ObjectMap<VillagerTrades.ItemListing[]> getTrades(VillagerProfession profession) 
    {
        return SpecialVillagerTrades.TRADES.getOrDefault(profession, Int2ObjectMaps.emptyMap());
    }
	
	public static <T extends AbstractCombatVillager> void registerVillagerEntry(VillagerRegistry registry, RegistryObject<EntityType<T>> entityType, RegistryObject<VillagerProfession> profession)
	{
		registry.addVillagerEntry(new SpecialVillagerEntry(() -> entityType.get(), profession.get(), getTrades(profession.get())));
	}
	
	public static RegistryObject<VillagerProfession> registerSpecialProfession(String name)
	{
		return VILLAGER_PROFESSION.register(name, () -> new VillagerProfession(name, PoiType.NONE, PoiType.NONE, ImmutableSet.of(), ImmutableSet.of(), null));
	}
}
