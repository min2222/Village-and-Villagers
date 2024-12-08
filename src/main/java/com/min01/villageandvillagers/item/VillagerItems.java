package com.min01.villageandvillagers.item;

import java.util.function.Supplier;

import com.min01.villageandvillagers.VillageandVillagers;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagerItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VillageandVillagers.MODID);

	public static RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<? extends Mob>> type, int color1, int color2)
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(type, color1, color2, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	}
}
