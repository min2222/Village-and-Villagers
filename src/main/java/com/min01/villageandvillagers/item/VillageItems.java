package com.min01.villageandvillagers.item;

import java.util.function.Supplier;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.block.VillageBlocks;
import com.min01.villageandvillagers.entity.VillageEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillageItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VillageandVillagers.MODID);
	
	public static final RegistryObject<Item> HARVESTER_SPAWN_EGG = registerSpawnEgg("harvester_spawn_egg", () -> VillageEntities.HARVESTER.get(), 1787273, 15002863);
	public static final RegistryObject<Item> TIME_KEEPER_SPAWN_EGG = registerSpawnEgg("time_keeper_spawn_egg", () -> VillageEntities.TIME_KEEPER.get(), 8932675, 14725725);
	public static final RegistryObject<Item> ROBIN_HOOD_SPAWN_EGG = registerSpawnEgg("robin_hood_spawn_egg", () -> VillageEntities.ROBIN_HOOD.get(), 2047016, 4928814);
	public static final RegistryObject<Item> DAYDREAMER_SPAWN_EGG = registerSpawnEgg("daydreamer_spawn_egg", () -> VillageEntities.DAYDREAMER.get(), 15067369, 5153005);
	public static final RegistryObject<Item> MARTIAL_ARTIST_SPAWN_EGG = registerSpawnEgg("martial_artist_spawn_egg", () -> VillageEntities.MARTIAL_ARTIST.get(), 15198162, 2433827);
	
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
	
	public static final RegistryObject<Item> TIME_JELLY = ITEMS.register("time_jelly", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> TIME_CRYSTAL = ITEMS.register("time_crystal", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> GEAR = ITEMS.register("gear", () -> new Item(new Item.Properties()));
	
	public static final RegistryObject<Item> MONOCLE = ITEMS.register("monocle", () -> new SimpleTooltipItem("item.villageandvillagers.monocle.tooltip", new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> POCKET_WATCH = ITEMS.register("pocket_watch", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

	public static RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<? extends Mob>> type, int color1, int color2)
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(type, color1, color2, new Item.Properties()));
	}
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
	}
}
