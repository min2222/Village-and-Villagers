package com.min01.minsvillageandvillagers.entity;

import com.min01.minsvillageandvillagers.MinsVillageAndVillagers;
import com.min01.minsvillageandvillagers.entity.villager.EntityHarvester;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagerEntityTypes 
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinsVillageAndVillagers.MODID);
	
	public static final RegistryObject<EntityType<EntityHarvester>> HARVESTER = registerEntity("harvester", createBuilder(EntityHarvester::new, MobCategory.MISC).sized(0.6F, 1.95F));
	
	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(MinsVillageAndVillagers.MODID, name).toString()));
	}
}
