package com.min01.minsvillageandvillagers.entity.projectile;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.phys.HitResult;

public class EntityThrownSapling extends ThrowableItemProjectile
{
	private OakTreeGrower grower;
	
	public EntityThrownSapling(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_)
	{
		super(p_37442_, p_37443_);
	}
	
	public EntityThrownSapling(EntityType<? extends ThrowableItemProjectile> p_37438_, LivingEntity p_37439_, Level p_37440_) 
	{
		super(p_37438_, p_37439_, p_37440_);
		this.grower = new OakTreeGrower();
	}
	
	@Override
	protected void onHit(HitResult p_37260_) 
	{
		super.onHit(p_37260_);
		List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10));
		list.removeIf(t -> this.getOwner() != null && t == this.getOwner());
		list.removeIf(t -> t.isAlliedTo(this) || this.isAlliedTo(this));
		list.forEach(t -> 
		{
			t.setDeltaMovement(t.getDeltaMovement().add(0, 0.25, 0));
		});
		if(this.level instanceof ServerLevel serverLevel)
		{
			this.grower.growTree(serverLevel, serverLevel.getChunkSource().getGenerator(), new BlockPos(p_37260_.getLocation()), Blocks.OAK_SAPLING.defaultBlockState(), this.random);
		}
		this.playSound(SoundEvents.GRASS_BREAK);
		this.discard();
	}

	@Override
	protected Item getDefaultItem() 
	{
		return Items.OAK_SAPLING;
	}
}
