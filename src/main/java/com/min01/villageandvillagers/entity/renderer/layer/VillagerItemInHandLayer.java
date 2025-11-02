package com.min01.villageandvillagers.entity.renderer.layer;

import com.min01.villageandvillagers.entity.model.ModelHarvester;
import com.min01.villageandvillagers.entity.villager.AbstractCombatVillager;
import com.min01.villageandvillagers.item.VillageItems;
import com.min01.villageandvillagers.util.VillageClientUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class VillagerItemInHandLayer<T extends AbstractCombatVillager, M extends HierarchicalModel<T>> extends RenderLayer<T, M>
{
	public final ItemInHandRenderer itemRenderer = VillageClientUtil.MC.getEntityRenderDispatcher().getItemInHandRenderer();
	
	public VillagerItemInHandLayer(RenderLayerParent<T, M> p_117346_) 
	{
		super(p_117346_);
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) 
	{
		if(p_117352_.isCombatMode())
		{
			if(this.getParentModel() instanceof ModelHarvester model)
			{
				ItemStack stack = p_117352_.getOffhandItem();
				if(stack.is(VillageItems.COWBELL.get()))
				{
					p_117349_.pushPose();
					p_117349_.translate(0, 1.5F, 0);
					model.body.translateAndRotate(p_117349_);
					model.upperbody.translateAndRotate(p_117349_);
					model.arms_out.translateAndRotate(p_117349_);
					model.left_arm.translateAndRotate(p_117349_);
					p_117349_.mulPose(Axis.XP.rotationDegrees(-90.0F));
					p_117349_.translate(2.0F / 16.0F, 2.0F / 16.0F, 8.0F / 16.0F);
					this.itemRenderer.renderItem(p_117352_, stack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, false, p_117349_, p_117350_, p_117351_);
					p_117349_.popPose();
				}
			}
		}
	}
}
