package com.min01.villageandvillagers.misc;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.min01.villageandvillagers.VillageandVillagers;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagePoiTypes 
{
	public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, VillageandVillagers.MODID);
	
	public static final RegistryObject<PoiType> HARVESTER = registerPoiType("harvester", Blocks.HAY_BLOCK);
	public static final RegistryObject<PoiType> TIME_KEEPER = registerPoiType("time_keeper", Blocks.REPEATER);
	public static final RegistryObject<PoiType> ROBIN_HOOD = registerPoiType("robin_hood", Blocks.TARGET);
	public static final RegistryObject<PoiType> DAYDREAMER = registerPoiType("daydreamer", Blocks.WHITE_WOOL);
	public static final RegistryObject<PoiType> MARTIAL_ARTIST = registerPoiType("martial_artist", Blocks.QUARTZ_BLOCK);
	
	public static RegistryObject<PoiType> registerPoiType(String name, Block block)
	{
		return registerPoiType(name, getBlockStates(block));
	}
	
	public static RegistryObject<PoiType> registerPoiType(String name, Set<BlockState> set)
	{
		return POI_TYPES.register(name, () -> new PoiType(set, 1, 1));
	}
	
	public static Set<BlockState> getBlockStates(Block block) 
	{
		return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
	}
}
