package com.min01.villageandvillagers.block;

import com.min01.villageandvillagers.VillageandVillagers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillageBlocks
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VillageandVillagers.MODID);
	
	public static final RegistryObject<Block> ASURA_STONE = BLOCKS.register("asura_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
	public static final RegistryObject<Block> ASURA_STONE_BRICKS = BLOCKS.register("asura_stone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
	public static final RegistryObject<Block> ASURA_STONE_PILLAR = BLOCKS.register("asura_stone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
	public static final RegistryObject<Block> CHISELED_ASURA_STONE = BLOCKS.register("chiseled_asura_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
	public static final RegistryObject<Block> SMOOTH_ASURA_STONE = BLOCKS.register("smooth_asura_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
    public static final RegistryObject<Block> ASURA_STONE_SLAB = BLOCKS.register("asura_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
    public static final RegistryObject<Block> ASURA_STONE_BRICK_SLAB = BLOCKS.register("asura_stone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
    public static final RegistryObject<Block> SMOOTH_ASURA_STONE_SLAB = BLOCKS.register("smooth_asura_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE)));
    public static final RegistryObject<Block> ASURA_STONE_STAIRS = BLOCKS.register("asura_stone_stairs", () -> new StairBlock(() -> ASURA_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(ASURA_STONE.get())));
    public static final RegistryObject<Block> ASURA_STONE_BRICK_STAIRS = BLOCKS.register("asura_stone_brick_stairs", () -> new StairBlock(() -> ASURA_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(ASURA_STONE_BRICKS.get())));
    public static final RegistryObject<Block> SMOOTH_ASURA_STONE_STAIRS = BLOCKS.register("smooth_asura_stone_stairs", () -> new StairBlock(() -> SMOOTH_ASURA_STONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SMOOTH_ASURA_STONE.get())));
}
