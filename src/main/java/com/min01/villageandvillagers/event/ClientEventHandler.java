package com.min01.villageandvillagers.event;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.model.ModelHaybaleBarricade;
import com.min01.villageandvillagers.entity.model.ModelTimeKeeper;
import com.min01.villageandvillagers.entity.renderer.HarvesterRenderer;
import com.min01.villageandvillagers.entity.renderer.HaybaleBarricadeRenderer;
import com.min01.villageandvillagers.entity.renderer.TimeKeeperRenderer;
import com.min01.villageandvillagers.shader.VillageShaders;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(VillageEntities.HARVESTER.get(), HarvesterRenderer::new);
		event.registerEntityRenderer(VillageEntities.HAYBALE_BARRICADE.get(), HaybaleBarricadeRenderer::new);
		event.registerEntityRenderer(VillageEntities.TIME_KEEPER.get(), TimeKeeperRenderer::new);
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelHarvester.LAYER_LOCATION, ModelHarvester::createBodyLayer);
    	event.registerLayerDefinition(ModelHaybaleBarricade.LAYER_LOCATION, ModelHaybaleBarricade::createBodyLayer);
    	event.registerLayerDefinition(ModelTimeKeeper.LAYER_LOCATION, ModelTimeKeeper::createBodyLayer);
    }
    
	@SubscribeEvent
	public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event)
	{
		event.registerReloadListener(new VillageShaders());
	}
}
