package com.min01.villageandvillagers.entity;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.misc.EntityHaybaleBarricade;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillageEntities 
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VillageandVillagers.MODID);
	
	public static final RegistryObject<EntityType<EntityHarvester>> HARVESTER = registerEntity("harvester", createBuilder(EntityHarvester::new, MobCategory.MISC).sized(0.6F, 1.95F));
	public static final RegistryObject<EntityType<EntityTimeKeeper>> TIME_KEEPER = registerEntity("time_keeper", createBuilder(EntityTimeKeeper::new, MobCategory.MISC).sized(0.6F, 2.4F));
	
	public static final RegistryObject<EntityType<EntityHaybaleBarricade>> HAYBALE_BARRICADE = registerEntity("haybale_barricade", createBuilder(EntityHaybaleBarricade::new, MobCategory.MISC).sized(3.0F, 3.0F));

	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(VillageandVillagers.MODID, name).toString()));
	}
}
