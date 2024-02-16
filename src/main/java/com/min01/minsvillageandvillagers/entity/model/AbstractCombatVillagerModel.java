package com.min01.minsvillageandvillagers.entity.model;

import com.min01.minsvillageandvillagers.entity.villager.AbstractCombatVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractCombatVillagerModel<E extends AbstractCombatVillager> extends HierarchicalModel<E> implements ArmedModel
{
	public abstract ModelPart getArm(HumanoidArm p_102923_);
	
	public abstract Vec3 getHandPos(HumanoidArm p_102108_);
	
	@Override
	public void translateToHand(HumanoidArm p_102108_, PoseStack p_102109_)
	{
		ModelPart arm = this.getArm(p_102108_);
		Vec3 vec = this.getHandPos(p_102108_);
		p_102109_.translate((double)(vec.x / 16.0F), (double)(vec.y / 16.0F), (double)(vec.z / 16.0F));
		if (arm.zRot != 0.0F) 
		{
			p_102109_.mulPose(Vector3f.ZP.rotation(arm.zRot));
		}

		if (arm.yRot != 0.0F)
		{
			p_102109_.mulPose(Vector3f.YP.rotation(arm.yRot));
		}

		if (arm.xRot != 0.0F)
		{
			p_102109_.mulPose(Vector3f.XP.rotation(arm.xRot));
		}

		if (arm.xScale != 1.0F || arm.yScale != 1.0F || arm.zScale != 1.0F)
		{
			p_102109_.scale(arm.xScale, arm.yScale, arm.zScale);
		}
	}
}
