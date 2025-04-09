package com.min01.villageandvillagers.streak;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;

public interface Tag
{
    default int maxTracks()
    { 
    	return 0; 
    }
    
    default int maxDeathPersist() 
    { 
    	return 0; 
    }
    
    default float width(EntityTracker tracker) 
    { 
    	return 0; 
    }
    
    default float height(EntityTracker tracker) 
    { 
    	return 0; 
    }
    
    default void init(EntityTracker tracker)
    {
    	
    }
    
    default void tick(EntityTracker tracker){}
    
    default void addInfo(EntityTracker tracker, EntityTracker.EntityInfo info)
    {
    	
    }
    
    default void removeInfo(EntityTracker tracker, EntityTracker.EntityInfo info)
    {
    	
    }
    
    default boolean ignoreFrustumCheck() 
    {
    	return false;
    }
    
    default <T extends Entity> void render(EntityTracker entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
    {
    	
    }
}
