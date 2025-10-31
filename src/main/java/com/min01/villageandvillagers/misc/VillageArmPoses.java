package com.min01.villageandvillagers.misc;

import com.min01.villageandvillagers.item.VillageItems;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.IArmPoseTransformer;

public class VillageArmPoses 
{
	public static HumanoidModel.ArmPose COWBELL;
	
	public static class CowbellArmPose implements IArmPoseTransformer 
	{
		@Override
		public void applyTransform(HumanoidModel<?> model, LivingEntity entity, HumanoidArm arm)
		{
			if(entity.isHolding(VillageItems.COWBELL.get()))
			{
				if(entity.getMainHandItem().is(VillageItems.COWBELL.get()))
				{
					model.rightArm.xRot = 5.0F;
				}
				else
				{
					model.leftArm.xRot = 5.0F;
				}
			}
		}
	}
	
	public static void registerArmPoses()
	{
		COWBELL = HumanoidModel.ArmPose.create("COWBELL", false, new VillageArmPoses.CowbellArmPose());
	}
}
