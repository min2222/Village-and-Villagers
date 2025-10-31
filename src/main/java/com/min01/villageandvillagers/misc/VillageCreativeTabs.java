package com.min01.villageandvillagers.misc;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.item.VillageItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class VillageCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VillageandVillagers.MODID);
    
    public static final RegistryObject<CreativeModeTab> VILLAGE_AND_VILLAGERS = CREATIVE_MODE_TAB.register("villageandvillagers", () -> CreativeModeTab.builder()
    		.title(Component.translatable("itemGroup.villageandvillagers"))
    		.icon(() -> new ItemStack(VillageItems.PITCHFORK.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			for(RegistryObject<Item> item : VillageItems.ITEMS.getEntries())
    			{
    				output.accept(item.get());
    			}
    		}).build());
}
