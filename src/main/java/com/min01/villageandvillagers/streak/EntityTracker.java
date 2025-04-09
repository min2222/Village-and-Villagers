package com.min01.villageandvillagers.streak;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class EntityTracker extends Entity
{
    public HashSet<Tag> tags = new HashSet<>();

    @Nonnull
    public Entity parent;

    public int maxPersistAfterDeath = 0;
    public int timeAfterDeath = 0;

    public int maxTrack = 0;
    
    public ArrayList<EntityInfo> trackedInfo = new ArrayList<>();

    public int lastUpdate = -1;

    public EntityTracker(EntityType<?> entityTypeIn, Level worldIn)
    {
        super(entityTypeIn, worldIn);
        this.setInvisible(true);
        this.setInvulnerable(true);
    }

    public EntityTracker setParent(@Nonnull Entity tracked)
    {
        this.parent = tracked;
        return this;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <G extends Tag> G getTag(Class<G> clz)
    {
        for(Tag tag : this.tags)
        {
            if(clz.isInstance(tag))
            {
                return (G) tag;
            }
        }
        return null;
    }

    public void addTag(Tag tag)
    {
        this.tags.add(tag);
        tag.init(this);
        this.updateBounds();
    }

    public void removeTag(Class<? extends Tag> clz)
    {
       	this.tags.removeIf(clz::isInstance);
        this.updateBounds();
    }

    public void updateBounds()
    {
        int tracks = 0;
        int deathTicks = 0;
        float w = 0.1F;
        float h = 0.1F;
        boolean ignoreFrustum = false;
        for(Tag tag : this.tags)
        {
            if(tag.maxTracks() > tracks)
            {
                tracks = tag.maxTracks();
            }
            if(tag.maxDeathPersist() > deathTicks)
            {
                deathTicks = tag.maxDeathPersist();
            }
            float tagW = tag.width(this);
            if(w > tagW)
            {
                w = tagW;
            }
            float tagH = tag.height(this);
            if(h > tagH)
            {
                h = tagH;
            }
            ignoreFrustum = tag.ignoreFrustumCheck() | ignoreFrustum;
        }

        this.maxTrack = tracks;
        this.maxPersistAfterDeath = deathTicks;
        this.noCulling = ignoreFrustum;

        this.setPos(this.getX(), this.getY(), this.getZ());
    }

	@Override
    public void tick()
    {
        super.tick();

        if(!this.parent.isAlive() || !this.parent.level.dimension().equals(this.level.dimension()))
        {
            if(this.maxPersistAfterDeath > 0)
            {
                if(this.timeAfterDeath >= this.maxPersistAfterDeath)
                {
                	this.discard();
                }
                this.timeAfterDeath++;
            }
            else if(!this.parent.isAlive())
            {
            	this.discard();
            }
        }
        else
        {
            this.setPos(this.parent.getX(), this.parent.getY(), this.parent.getZ());
            this.setRot(this.parent.getYRot(), this.parent.getXRot());

            if(this.maxTrack > 0)
            {
                EntityInfo info = new EntityInfo(this.parent.getX(), this.parent.getY(), this.parent.getZ(), this.parent.getBbWidth(), this.parent.getBbHeight(), this.parent.getYRot(), this.parent.getXRot(), this.parent.isInvisible());
                this.trackedInfo.add(0, info);
                this.tags.forEach(tag -> tag.addInfo(this, info));

                while(this.trackedInfo.size() > this.maxTrack)
                {
                    EntityInfo removed = this.trackedInfo.get(this.trackedInfo.size() - 1);
                    this.tags.forEach(tag -> tag.removeInfo(this, removed));
                    this.trackedInfo.remove(this.trackedInfo.size() - 1);
                }
            }
        }

        this.tags.forEach(tag -> tag.tick(this));
    }
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> T getParent()
	{
		return (T) this.parent;
	}

    @Override
    protected void defineSynchedData()
    {
    	
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound)
    {
    	
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound)
    {
    	
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() 
    {
    	return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static class EntityInfo
    {
        public final double posX;
        public final double posY;
        public final double posZ;
        public final float width;
        public final float height;
        public final float yaw;
        public final float pitch;
        public final boolean invisible;

        public EntityInfo(double posX, double posY, double posZ, float width, float height, float yaw, float pitch, boolean invisible) 
        {
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.width = width;
            this.height = height;
            this.yaw = yaw;
            this.pitch = pitch;
            this.invisible = invisible;
        }
    }
}