package com.min01.villageandvillagers.entity.misc;

import java.util.List;

import com.min01.villageandvillagers.entity.AbstractOwnableCreature;
import com.min01.villageandvillagers.item.VillageItems;
import com.min01.villageandvillagers.misc.SmoothAnimationState;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class EntityScarecrow extends AbstractOwnableCreature<LivingEntity>
{
	public final SmoothAnimationState idleAnimationState = new SmoothAnimationState();
	public final SmoothAnimationState hurtAnimationState = new SmoothAnimationState();
	
	public EntityScarecrow(EntityType<? extends PathfinderMob> p_19870_, Level p_19871_)
	{
		super(p_19870_, p_19871_);
		this.setNoAi(true);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 200.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.0F)
        		.add(Attributes.ATTACK_DAMAGE, 0.0F)
        		.add(Attributes.FOLLOW_RANGE, 0.0F);
    }
	
	@Override
	public void tick()
	{
		super.tick();
		
		if(this.level.isClientSide)
		{
			this.idleAnimationState.updateWhen(this.getAnimationState() == 0, this.tickCount);
			this.hurtAnimationState.updateWhen(this.isUsingSkill(1), this.tickCount);
		}
		
		if(this.tickCount % 20 == 0 && this.getOwner() != null)
		{
			List<Mob> list = this.level.getEntitiesOfClass(Mob.class, this.getBoundingBox().inflate(5.0F), t -> t.getTarget() == this.getOwner() && !t.getType().is(Tags.EntityTypes.BOSSES));
			list.forEach(t -> 
			{
				t.setTarget(this);
			});
		}
		
		if(this.level.getBlockState(this.blockPosition().below()).isAir())
		{
			this.spawnAtLocation(VillageItems.SCARECROW.get());
			this.discard();
		}
	}
	
	@Override
	public boolean isAlliedTo(Entity p_20355_) 
	{
		if(p_20355_ == this.getOwner())
		{
			return true;
		}
		return super.isAlliedTo(p_20355_);
	}
	
	@Override
	protected void doPush(Entity p_20971_)
	{
		
	}
	
	@Override
	public void push(double p_20286_, double p_20287_, double p_20288_) 
	{
		
	}
	
	@Override
	public void knockback(double p_147241_, double p_147242_, double p_147243_) 
	{
		
	}
	
	@Override
	protected void tickDeath() 
	{
		if(!this.level.isClientSide && !this.isRemoved()) 
		{
			this.level.broadcastEntityEvent(this, (byte)60);
			this.remove(Entity.RemovalReason.KILLED);
		}
	}
	
	@Override
	public ItemStack getPickResult()
	{
		return VillageItems.SCARECROW.get().getDefaultInstance();
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_)
	{
		return SoundEvents.GRASS_BREAK;
	}
	
	@Override
	public boolean hurt(DamageSource p_19946_, float p_19947_) 
	{
		if(p_19946_.is(DamageTypeTags.IS_FIRE))
		{
			p_19947_ *= 2;
		}
		boolean flag = super.hurt(p_19946_, p_19947_);
		if(flag)
		{
			this.playSound(SoundEvents.GRASS_BREAK);
			if(this.getAnimationState() == 0)
			{
				this.setAnimationState(1);
				this.setAnimationTick(10);
				this.setUsingSkill(true);
			}
			this.level.broadcastEntityEvent(this, (byte) 99);
		}
		return flag;
	}
	
	@Override
	protected InteractionResult mobInteract(Player p_21472_, InteractionHand p_21473_) 
	{
		if(p_21472_ == this.getOwner() && p_21472_.isShiftKeyDown())
		{
			this.spawnAtLocation(VillageItems.SCARECROW.get());
			this.discard();
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(p_21472_, p_21473_);
	}
	
	@Override
	public void handleEntityEvent(byte p_21375_) 
	{
		super.handleEntityEvent(p_21375_);
		if(p_21375_ == 99)
		{
			for(int i = 0; i < 10; i++)
			{
	    		this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.HAY_BLOCK.defaultBlockState()), this.getX() + this.random.nextGaussian() * 0.1F, this.getEyeY(), this.getZ() + this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F, this.random.nextGaussian() * 0.1F);
			}
		}
	}
}
