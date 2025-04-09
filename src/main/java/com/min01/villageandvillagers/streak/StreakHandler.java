package com.min01.villageandvillagers.streak;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.min01.villageandvillagers.VillageandVillagers;
import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.util.VillageClientUtil;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VillageandVillagers.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class StreakHandler 
{
	private static final AtomicInteger NEXT_ENTITY_ID = new AtomicInteger(-70000000);
	private static final IdentityHashMap<Entity, EntityTracker> TRACKERS = new IdentityHashMap<>();
	
    public static int getNextEntId()
    {
        return NEXT_ENTITY_ID.getAndDecrement();
    }

    public static EntityTracker getOrCreate(Entity entity)
    {
        EntityTracker tracker = null;
        
        for(Map.Entry<Entity, EntityTracker> entry : TRACKERS.entrySet())
        {
            if(entry.getKey() == entity)
            {
                tracker = entry.getValue();
                break;
            }
        }
        
        if(tracker == null)
        {
            tracker = new EntityTracker(VillageEntities.ENTITY_TRACKER.get(), entity.level);
            TRACKERS.put(entity, tracker);
        }

        if(!tracker.isAlive())
        {
            TRACKERS.put(entity, tracker = new EntityTracker(VillageEntities.ENTITY_TRACKER.get(), entity.level));
        }
        
        tracker.setParent(entity);
        
        if(!tracker.isAddedToWorld())
        {
            tracker.setId(getNextEntId());
            tracker.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
            ((ClientLevel)entity.level).putNonPlayerEntity(tracker.getId(), tracker);
        }
        return tracker;
    }
    
	@SubscribeEvent
	public static void onEntityJoinLevel(EntityJoinLevelEvent event)
	{
		Level level = event.getLevel();
		if(level.isClientSide)
		{
			if(event.getEntity() instanceof IStreak)
			{
				EntityTracker tracker = getOrCreate(event.getEntity());
				tracker.addTag(new StreakTag());
			}
		}
	}
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            if(VillageClientUtil.MC.player != null && !VillageClientUtil.MC.isPaused())
            {
                EntityTracker playerTracker = null;
                Iterator<Map.Entry<Entity, EntityTracker>> iter = TRACKERS.entrySet().iterator();
                while(iter.hasNext())
                {
                    Map.Entry<Entity, EntityTracker> entry = iter.next();
                    EntityTracker tracker = entry.getValue();
                    if(!tracker.isAlive() || VillageClientUtil.MC.player.tickCount - tracker.lastUpdate > 10)
                    {
                        if(entry.getKey() == VillageClientUtil.MC.player && tracker.lastUpdate == -1)
                        {
                            playerTracker = tracker;
                        }
                        iter.remove();
                    }
                }
                if(playerTracker != null)
                {
                    EntityTracker tracker = getOrCreate(playerTracker.parent);
                    tracker.tags = playerTracker.tags;
                    tracker.updateBounds();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event)
    {
        if(event.getLevel().isClientSide())
        {
            Iterator<Map.Entry<Entity, EntityTracker>> iter = TRACKERS.entrySet().iterator();
            while(iter.hasNext())
            {
                Map.Entry<Entity, EntityTracker> e = iter.next();
                EntityTracker tracker = e.getValue();
                if(tracker.parent.level == event.getLevel())
                {
                	iter.remove();
                }
            }
        }
    }
}
