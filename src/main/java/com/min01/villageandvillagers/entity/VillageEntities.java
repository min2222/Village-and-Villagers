package com.min01.villageandvillagers.entity;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.misc.EntityHaybaleBarricade;
import com.min01.villageandvillagers.entity.projectile.EntitySpecialArrow;
import com.min01.villageandvillagers.entity.projectile.EntityTimeGear;
import com.min01.villageandvillagers.entity.villager.EntityDaydreamer;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.entity.villager.EntityMartialArtist;
import com.min01.villageandvillagers.entity.villager.EntityRobinHood;
import com.min01.villageandvillagers.entity.villager.EntityTimeKeeper;
import com.min01.villageandvillagers.streak.EntityTracker;

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
	public static final RegistryObject<EntityType<EntityRobinHood>> ROBIN_HOOD = registerEntity("robin_hood", createBuilder(EntityRobinHood::new, MobCategory.MISC).sized(0.6F, 1.95F));
	public static final RegistryObject<EntityType<EntityDaydreamer>> DAYDREAMER = registerEntity("daydreamer", createBuilder(EntityDaydreamer::new, MobCategory.MISC).sized(0.6F, 1.95F));
	public static final RegistryObject<EntityType<EntityMartialArtist>> MARTIAL_ARTIST = registerEntity("martial_artist", createBuilder(EntityMartialArtist::new, MobCategory.MISC).sized(0.6F, 1.95F));
	
	public static final RegistryObject<EntityType<EntityHaybaleBarricade>> HAYBALE_BARRICADE = registerEntity("haybale_barricade", createBuilder(EntityHaybaleBarricade::new, MobCategory.MISC).sized(3.0F, 3.0F));
	public static final RegistryObject<EntityType<EntityTimeGear>> TIME_GEAR = registerEntity("time_gear", createBuilder(EntityTimeGear::new, MobCategory.MISC).sized(0.25F, 0.25F));
	public static final RegistryObject<EntityType<Entity>> VILLAGE_CAMERA_SHAKE = registerEntity("village_camera_shake", createBuilder(EntityVillageCameraShake::new, MobCategory.MISC).sized(0.0F, 0.0F));
	public static final RegistryObject<EntityType<EntityTracker>> ENTITY_TRACKER = registerEntity("entity_tracker", createBuilder(EntityTracker::new, MobCategory.MISC).noSave().noSummon().fireImmune());
	public static final RegistryObject<EntityType<EntitySpecialArrow>> SPECIAL_ARROW = registerEntity("special_arrow", EntityType.Builder.<EntitySpecialArrow>of(EntitySpecialArrow::new, MobCategory.MISC).sized(0.5F, 0.5F));

	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(VillageandVillagers.MODID, name).toString()));
	}
}
