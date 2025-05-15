package com.min01.villageandvillagers.misc;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.min01.villageandvillagers.VillageandVillagers;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillageProfessions 
{
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, VillageandVillagers.MODID);
	
	public static final RegistryObject<VillagerProfession> TIME_KEEPER = registerSpecialProfession("time_keeper", null);
	public static final RegistryObject<VillagerProfession> ROBIN_HOOD = registerSpecialProfession("robin_hood", null);
	public static final RegistryObject<VillagerProfession> DAYDREAMER = registerSpecialProfession("daydreamer", null);
	public static final RegistryObject<VillagerProfession> MARTIAL_ARTIST = registerSpecialProfession("martial_artist", null);
	
	public static RegistryObject<VillagerProfession> registerSpecialProfession(String p_219654_, @Nullable SoundEvent p_219657_)
	{
		//TODO jer compat (NONE type doesn't work)
		return VILLAGER_PROFESSION.register(p_219654_, () -> new VillagerProfession(p_219654_, PoiType.NONE, PoiType.NONE, ImmutableSet.of(), ImmutableSet.of(), p_219657_));
	}
}
