package com.min01.minsvillageandvillagers.misc;

import com.min01.minsvillageandvillagers.MinsVillageAndVillagers;
import com.min01.minsvillageandvillagers.entity.VillagerEntityTypes;
import com.min01.minsvillageandvillagers.entity.model.ModelHarvester;
import com.min01.minsvillageandvillagers.entity.render.HarvesterRenderer;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinsVillageAndVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(VillagerEntityTypes.HARVESTER.get(), HarvesterRenderer::new);
		event.registerEntityRenderer(VillagerEntityTypes.THROWN_SAPLING.get(), p_174010_ -> new ThrownItemRenderer<>(p_174010_));
	}
	
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelHarvester.LAYER_LOCATION, ModelHarvester::createBodyLayer);
    }
}
