package com.min01.villageandvillagers.item;

import java.util.function.Supplier;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.block.VillageBlocks;
import com.min01.villageandvillagers.entity.VillageEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillageItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VillageandVillagers.MODID);
	
	public static final RegistryObject<Item> HARVESTER_SPAWN_EGG = registerSpawnEgg("harvester_spawn_egg", () -> VillageEntities.HARVESTER.get(), 0, 0);
	
	public static final RegistryObject<Item> ASURA_STONE = registerBlockItem("asura_stone", () -> VillageBlocks.ASURA_STONE.get(), new Item.Properties());
	public static final RegistryObject<Item> ASURA_STONE_BRICKS = registerBlockItem("asura_stone_bricks", () -> VillageBlocks.ASURA_STONE_BRICKS.get(), new Item.Properties());
	public static final RegistryObject<Item> ASURA_STONE_PILLAR = registerBlockItem("asura_stone_pillar", () -> VillageBlocks.ASURA_STONE_PILLAR.get(), new Item.Properties());
	public static final RegistryObject<Item> CHISELED_ASURA_STONE = registerBlockItem("chiseled_asura_stone", () -> VillageBlocks.CHISELED_ASURA_STONE.get(), new Item.Properties());
	public static final RegistryObject<Item> SMOOTH_ASURA_STONE = registerBlockItem("smooth_asura_stone", () -> VillageBlocks.SMOOTH_ASURA_STONE.get(), new Item.Properties());
	public static final RegistryObject<Item> ASURA_STONE_SLAB = registerBlockItem("asura_stone_slab", () -> VillageBlocks.ASURA_STONE_SLAB.get(), new Item.Properties());
	public static final RegistryObject<Item> ASURA_STONE_BRICK_SLAB = registerBlockItem("asura_stone_brick_slab", () -> VillageBlocks.ASURA_STONE_BRICK_SLAB.get(), new Item.Properties());
	public static final RegistryObject<Item> SMOOTH_ASURA_STONE_SLAB = registerBlockItem("smooth_asura_stone_slab", () -> VillageBlocks.SMOOTH_ASURA_STONE_SLAB.get(), new Item.Properties());
	public static final RegistryObject<Item> ASURA_STONE_STAIRS = registerBlockItem("asura_stone_stairs", () -> VillageBlocks.ASURA_STONE_STAIRS.get(), new Item.Properties());
	public static final RegistryObject<Item> ASURA_STONE_BRICK_STAIRS = registerBlockItem("asura_stone_brick_stairs", () -> VillageBlocks.ASURA_STONE_BRICK_STAIRS.get(), new Item.Properties());
	public static final RegistryObject<Item> SMOOTH_ASURA_STONE_STAIRS = registerBlockItem("smooth_asura_stone_stairs", () -> VillageBlocks.SMOOTH_ASURA_STONE_STAIRS.get(), new Item.Properties());

	public static RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<? extends Mob>> type, int color1, int color2)
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(type, color1, color2, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	}
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties propertie)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), propertie.tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
	}
}
