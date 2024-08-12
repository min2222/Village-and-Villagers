package com.min01.villageandvillagers.entity.villager;

import com.min01.villageandvillagers.entity.AbstractOwnableEntity;
import com.min01.villageandvillagers.util.KinematicChain;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;

public class EntityOctollagerClaw extends AbstractOwnableEntity<EntityDrOctollager>
{
	public final KinematicChain chain = new KinematicChain(this);
	
	public EntityOctollagerClaw(EntityType<?> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
		this.setNoGravity(true);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		this.move(MoverType.SELF, this.getDeltaMovement());
		if(this.getOwner() != null)
		{
			if(!this.getOwner().claws.contains(this))
			{
				this.getOwner().claws.add(this);
			}
			
			this.chain.tick();
			this.chain.setTarget(this.getOwner());
			
			if(this.tickCount % 20 == 0)
			{
				this.setDeltaMovement(this.getDeltaMovement().add(this.level.random.nextGaussian() * 0.005, this.level.random.nextGaussian() * 0.005, this.level.random.nextGaussian() * 0.005));
			}
			
            double distance = this.distanceTo(this.getOwner());
            if(distance > 4)
            {
            	double d0 = (this.getOwner().getX() - this.getX()) / distance;
                double d1 = (this.getOwner().getY() - this.getY()) / distance;
                double d2 = (this.getOwner().getZ() - this.getZ()) / distance;
                this.setDeltaMovement(this.getDeltaMovement().add(d0 * Math.abs(d0) * 0.01D, d1 * Math.abs(d1) * 0.01D, d2 * Math.abs(d2) * 0.01D));
            }
		}
	}
}
