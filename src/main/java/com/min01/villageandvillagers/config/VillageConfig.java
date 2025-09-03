package com.min01.villageandvillagers.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class VillageConfig 
{
	public static final VillageConfig CONFIG;
	public static final ForgeConfigSpec CONFIG_SPEC;

	public static ForgeConfigSpec.ConfigValue<Float> specialVillagerChance;
	public static ForgeConfigSpec.BooleanValue disableDamageVillagers;
	
    static 
    {
    	Pair<VillageConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(VillageConfig::new);
    	CONFIG = pair.getLeft();
    	CONFIG_SPEC = pair.getRight();
    }
	
    public VillageConfig(ForgeConfigSpec.Builder config) 
    {
    	config.push("Settings");
    	VillageConfig.specialVillagerChance = config.comment("chance for special villagers (villagers with no job site block) appear in village").define("specialVillagerChance", 10.0F);
    	VillageConfig.disableDamageVillagers = config.comment("disable/enable if skills of special villagers can damage iron golem or villagers").define("disableDamageVillagers", true);
        config.pop();
    }
}
