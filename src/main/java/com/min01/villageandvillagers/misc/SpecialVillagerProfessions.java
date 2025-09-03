package com.min01.villageandvillagers.misc;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.compat.jer.SpecialVillagerEntry;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import jeresources.registry.VillagerRegistry;
import net.minecraft.sounds.SoundEvent;
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

	public static final RegistryObject<VillagerProfession> HARVESTER = registerSpecialProfession("harvester", VillagePoiTypes.HARVESTER, null);
	public static final RegistryObject<VillagerProfession> TIME_KEEPER = registerSpecialProfession("time_keeper", VillagePoiTypes.TIME_KEEPER, null);
	public static final RegistryObject<VillagerProfession> ROBIN_HOOD = registerSpecialProfession("robin_hood", VillagePoiTypes.ROBIN_HOOD, null);
	public static final RegistryObject<VillagerProfession> DAYDREAMER = registerSpecialProfession("daydreamer", VillagePoiTypes.DAYDREAMER, null);
	public static final RegistryObject<VillagerProfession> MARTIAL_ARTIST = registerSpecialProfession("martial_artist", VillagePoiTypes.MARTIAL_ARTIST, null);
	
	public static Int2ObjectMap<VillagerTrades.ItemListing[]> getTrades(VillagerProfession profession) 
    {
        return SpecialVillagerTrades.TRADES.getOrDefault(profession, Int2ObjectMaps.emptyMap());
    }
	
	public static <T extends AbstractCombatVillager> void registerVillagerEntry(VillagerRegistry registry, RegistryObject<EntityType<T>> entityType, RegistryObject<VillagerProfession> profession)
	{
		registry.addVillagerEntry(new SpecialVillagerEntry(() -> entityType.get(), profession.get(), getTrades(profession.get())));
	}
	
	public static RegistryObject<VillagerProfession> registerSpecialProfession(String p_219654_, RegistryObject<PoiType> type, @Nullable SoundEvent p_219657_)
	{
		return VILLAGER_PROFESSION.register(p_219654_, () -> new VillagerProfession(p_219654_, t -> t.get() == type.get(), t -> t.get() == type.get(), ImmutableSet.of(), ImmutableSet.of(), p_219657_));
	}
}
