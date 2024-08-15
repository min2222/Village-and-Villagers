package com.min01.villageandvillagers.event;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.VillagerEntities;
import com.min01.villageandvillagers.entity.model.ModelDrOctollager;
import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.model.ModelOctollagerClaw;
import com.min01.villageandvillagers.entity.model.ModelOctollagerTentacle;
import com.min01.villageandvillagers.entity.renderer.BasicCombatVillagerRenderer;
import com.min01.villageandvillagers.entity.renderer.DrOctollagerRenderer;
import com.min01.villageandvillagers.entity.renderer.OctollagerClawRenderer;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(VillagerEntities.HARVESTER.get(), p_174010_ -> new BasicCombatVillagerRenderer<>(p_174010_, new ModelHarvester(p_174010_.bakeLayer(ModelHarvester.LAYER_LOCATION)), "harvester"));
		event.registerEntityRenderer(VillagerEntities.THROWN_SAPLING.get(), p_174010_ -> new ThrownItemRenderer<>(p_174010_));
		event.registerEntityRenderer(VillagerEntities.DR_OCTOLLAGER.get(), DrOctollagerRenderer::new);
		event.registerEntityRenderer(VillagerEntities.OCTOLLAGER_CLAW.get(), OctollagerClawRenderer::new);
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelHarvester.LAYER_LOCATION, ModelHarvester::createBodyLayer);
    	event.registerLayerDefinition(ModelOctollagerClaw.LAYER_LOCATION, ModelOctollagerClaw::createBodyLayer);
    	event.registerLayerDefinition(ModelOctollagerTentacle.LAYER_LOCATION, ModelOctollagerTentacle::createBodyLayer);
    	event.registerLayerDefinition(ModelDrOctollager.LAYER_LOCATION, ModelDrOctollager::createBodyLayer);
    }
}
