package com.min01.villageandvillagers.entity.renderer;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.villager.EntityHarvester;
import com.min01.villageandvillagers.network.UpdatePosArrayPacket;
import com.min01.villageandvillagers.network.VillageNetwork;
import com.min01.villageandvillagers.util.VillageClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class HarvesterRenderer extends MobRenderer<EntityHarvester, ModelHarvester>
{
	public HarvesterRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelHarvester(p_174304_.bakeLayer(ModelHarvester.LAYER_LOCATION)), 0.5F);
	}
	
	@Override
	public void render(EntityHarvester p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) 
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
		Vec3 pos1 = VillageClientUtil.getWorldPosition(p_115455_, this.model.root(), new Vec3(0.0F, p_115455_.yBodyRot, 0.0F), "body", "upperbody", "arms_out", "right_arm", "pitchfork", "fork_locator");
		p_115455_.posArray[0] = pos1;
		VillageNetwork.sendToServer(new UpdatePosArrayPacket(p_115455_, pos1, 0));
	}

	@Override
	public ResourceLocation getTextureLocation(EntityHarvester p_115812_) 
	{
		return new ResourceLocation(VillageandVillagers.MODID, "textures/entity/harvester.png");
	}
}
