package com.min01.villageandvillagers.streak;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public interface IStreakRenderer<T extends Entity>
{
	default boolean canRenderStreak(T entity)
	{
		return true;
	}
	
	default Vec3 getStreakColor(T entity)
	{
		return new Vec3(1.0F, 1.0F, 1.0F);
	}
	
	default int getMaxStreakCount(T entity)
	{
		return 6;
	}
	
	default RenderType getStreakRenderType(ResourceLocation texture, T entity)
	{
		return RenderType.entityTranslucent(texture);
	}
	
	public HierarchicalModel<T> getStreakModel(T entity);

	public ResourceLocation getStreakTexture(T entity);
}
