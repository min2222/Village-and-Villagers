package com.min01.villageandvillagers.event;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.entity.model.ModelDaydreamer;
import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.model.ModelHaybaleBarricade;
import com.min01.villageandvillagers.entity.model.ModelLargeTimeGear;
import com.min01.villageandvillagers.entity.model.ModelMartialArtist;
import com.min01.villageandvillagers.entity.model.ModelRobinHood;
import com.min01.villageandvillagers.entity.model.ModelScarecrow;
import com.min01.villageandvillagers.entity.model.ModelTimeGear;
import com.min01.villageandvillagers.entity.model.ModelTimeKeeper;
import com.min01.villageandvillagers.entity.renderer.DaydreamerRenderer;
import com.min01.villageandvillagers.entity.renderer.HarvesterRenderer;
import com.min01.villageandvillagers.entity.renderer.HaybaleBarricadeRenderer;
import com.min01.villageandvillagers.entity.renderer.MartialArtistRenderer;
import com.min01.villageandvillagers.entity.renderer.NoneRenderer;
import com.min01.villageandvillagers.entity.renderer.RobinHoodRenderer;
import com.min01.villageandvillagers.entity.renderer.ScarecrowRenderer;
import com.min01.villageandvillagers.entity.renderer.SpecialArrowRenderer;
import com.min01.villageandvillagers.entity.renderer.TimeGearRenderer;
import com.min01.villageandvillagers.entity.renderer.TimeKeeperRenderer;
import com.min01.villageandvillagers.misc.VillageArmPoses;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
		VillageArmPoses.registerArmPoses();
		/*try 
		{
			AESUtil.encryptFiles(".png");
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}*/
	}
	
	@SubscribeEvent
	public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(VillageEntities.HARVESTER.get(), HarvesterRenderer::new);
		event.registerEntityRenderer(VillageEntities.HAYBALE_BARRICADE.get(), HaybaleBarricadeRenderer::new);
		event.registerEntityRenderer(VillageEntities.TIME_KEEPER.get(), TimeKeeperRenderer::new);
		event.registerEntityRenderer(VillageEntities.TIME_GEAR.get(), TimeGearRenderer::new);
    	event.registerEntityRenderer(VillageEntities.CAMERA_SHAKE.get(), NoneRenderer::new);
		event.registerEntityRenderer(VillageEntities.ROBIN_HOOD.get(), RobinHoodRenderer::new);
		event.registerEntityRenderer(VillageEntities.DAYDREAMER.get(), DaydreamerRenderer::new);
		event.registerEntityRenderer(VillageEntities.MARTIAL_ARTIST.get(), MartialArtistRenderer::new);
		event.registerEntityRenderer(VillageEntities.SPECIAL_ARROW.get(), SpecialArrowRenderer::new);
		event.registerEntityRenderer(VillageEntities.SCARECROW.get(), ScarecrowRenderer::new);
		event.registerEntityRenderer(VillageEntities.RUSHING_COW.get(), CowRenderer::new);
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelHarvester.LAYER_LOCATION, ModelHarvester::createBodyLayer);
    	event.registerLayerDefinition(ModelHaybaleBarricade.LAYER_LOCATION, ModelHaybaleBarricade::createBodyLayer);
    	event.registerLayerDefinition(ModelTimeKeeper.LAYER_LOCATION, ModelTimeKeeper::createBodyLayer);
    	event.registerLayerDefinition(ModelTimeGear.LAYER_LOCATION, ModelTimeGear::createBodyLayer);
    	event.registerLayerDefinition(ModelLargeTimeGear.LAYER_LOCATION, ModelLargeTimeGear::createBodyLayer);
    	event.registerLayerDefinition(ModelRobinHood.LAYER_LOCATION, ModelRobinHood::createBodyLayer);
    	event.registerLayerDefinition(ModelDaydreamer.LAYER_LOCATION, ModelDaydreamer::createBodyLayer);
    	event.registerLayerDefinition(ModelMartialArtist.LAYER_LOCATION, ModelMartialArtist::createBodyLayer);
    	event.registerLayerDefinition(ModelScarecrow.LAYER_LOCATION, ModelScarecrow::createBodyLayer);
    }
}
