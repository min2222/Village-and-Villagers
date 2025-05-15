package com.min01.villageandvillagers.misc;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.item.VillageItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class VillageCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VillageandVillagers.MODID);
    
    public static final RegistryObject<CreativeModeTab> VILLAGE_AND_VILLAGERS = CREATIVE_MODE_TAB.register("villageandvillagers", () -> CreativeModeTab.builder()
    		.title(Component.translatable("itemGroup.villageandvillagers.villageandvillagers"))
    		.icon(() -> new ItemStack(VillageItems.ASURA_STONE.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			output.accept(VillageItems.ASURA_STONE.get());
    			output.accept(VillageItems.ASURA_STONE_BRICKS.get());
    			output.accept(VillageItems.ASURA_STONE_PILLAR.get());
    			output.accept(VillageItems.CHISELED_ASURA_STONE.get());
    			output.accept(VillageItems.SMOOTH_ASURA_STONE.get());
    			output.accept(VillageItems.ASURA_STONE_SLAB.get());
    			output.accept(VillageItems.ASURA_STONE_BRICK_SLAB.get());
    			output.accept(VillageItems.SMOOTH_ASURA_STONE_SLAB.get());
    			output.accept(VillageItems.ASURA_STONE_STAIRS.get());
    			output.accept(VillageItems.ASURA_STONE_BRICK_STAIRS.get());
    			output.accept(VillageItems.SMOOTH_ASURA_STONE_STAIRS.get());
    			output.accept(VillageItems.HARVESTER_SPAWN_EGG.get());
    			output.accept(VillageItems.TIME_KEEPER_SPAWN_EGG.get());
    			output.accept(VillageItems.ROBIN_HOOD_SPAWN_EGG.get());
    			output.accept(VillageItems.DAYDREAMER_SPAWN_EGG.get());
    			output.accept(VillageItems.MARTIAL_ARTIST_SPAWN_EGG.get());
    			output.accept(VillageItems.TIME_JELLY.get());
    			output.accept(VillageItems.TIME_CRYSTAL.get());
    			output.accept(VillageItems.GEAR.get());
    			output.accept(VillageItems.MONOCLE.get());
    			output.accept(VillageItems.POCKET_WATCH.get());
    		}).build());
}
