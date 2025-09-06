package com.min01.villageandvillagers.entity.projectile;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.villageandvillagers.entity.VillageEntities;
import com.min01.villageandvillagers.item.VillageItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntitySpecialArrow extends AbstractArrow
{
	public static final EntityDataAccessor<Integer> ARROW_TYPE = SynchedEntityData.defineId(EntitySpecialArrow.class, EntityDataSerializers.INT);
	
	public int currentAmount;
	   
	public EntitySpecialArrow(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) 
	{
		super(p_36721_, p_36722_);
	}

	public EntitySpecialArrow(EntityType<? extends AbstractArrow> p_36711_, double p_36712_, double p_36713_, double p_36714_, Level p_36715_)
	{
		this(p_36711_, p_36715_);
		this.setPos(p_36712_, p_36713_, p_36714_);
	}

	public EntitySpecialArrow(EntityType<? extends AbstractArrow> p_36717_, LivingEntity p_36718_, Level p_36719_) 
	{
		this(p_36717_, p_36718_.getX(), p_36718_.getEyeY() - (double)0.1F, p_36718_.getZ(), p_36719_);
		this.setOwner(p_36718_);
		if(p_36718_ instanceof Player)
		{
			this.pickup = AbstractArrow.Pickup.ALLOWED;
		}
	}
	
	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(ARROW_TYPE, 0);
	}
	
	@Override
	protected void onHitEntity(EntityHitResult p_36757_)
	{
		if(this.getArrowType() == SpecialArrowType.ENDER)
		{
			if(this.getOwner() != null)
			{
				Vec3 pos = p_36757_.getLocation();
				Vec3 originalPos = this.getOwner().position();
				this.getOwner().teleportTo(pos.x, pos.y, pos.z);
				p_36757_.getEntity().teleportTo(originalPos.x, originalPos.y, originalPos.z);
			}
		}
		super.onHitEntity(p_36757_);
	}
	
	@Override
	protected void onHit(HitResult p_37260_) 
	{
		if(this.currentAmount < this.getArrowType().maxAmount)
		{
			if(this.getArrowType() == SpecialArrowType.MULTI)
			{
				for(int i = 0; i < 5; i++)
				{
					EntitySpecialArrow arrow = new EntitySpecialArrow(VillageEntities.SPECIAL_ARROW.get(), this.level);
					CompoundTag tag = this.saveWithoutId(new CompoundTag());
					arrow.load(tag);
					arrow.setUUID(UUID.randomUUID());
					arrow.setPos(this.position().add(0, 0.5, 0));
					arrow.setDeltaMovement(this.level.random.nextGaussian() * 0.2D, 0.4D, this.level.random.nextGaussian() * 0.2D);
					arrow.currentAmount = this.currentAmount + 1;
					this.level.addFreshEntity(arrow);
				}
			}
			this.currentAmount++;
		}
		super.onHit(p_37260_);
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_36755_) 
	{
		if(this.currentAmount < this.getArrowType().maxAmount)
		{
			if(this.getArrowType() == SpecialArrowType.BOUNCY)
			{
			    double x = this.getDeltaMovement().x;
			    double y = this.getDeltaMovement().y;
			    double z = this.getDeltaMovement().z;

				Direction direction = p_36755_.getDirection();
				
				if(direction == Direction.EAST) 
				{
					x = -x;
				}
				else if(direction == Direction.SOUTH) 
				{
					z = -z;
				}
				else if(direction == Direction.WEST) 
				{
					x = -x;
				}
				else if(direction == Direction.NORTH)
				{
					z = -z;
				}
				else if(direction == Direction.UP)
				{
					y = -y;
				}
				else if(direction == Direction.DOWN)
				{
					y = -y;
				}
				this.setDeltaMovement(x, y, z);
			}
			if(this.getArrowType() == SpecialArrowType.PIERCING)
			{
				BlockPos pos = p_36755_.getBlockPos();
				BlockState state = this.level.getBlockState(pos);
				if(!state.is(BlockTags.DRAGON_IMMUNE) && !state.is(BlockTags.WITHER_IMMUNE))
				{
					this.level.destroyBlock(pos, true, this);
				}
			}
			this.currentAmount++;
		}
		else
		{
			super.onHitBlock(p_36755_);
		}
	}

	@Override
	protected ItemStack getPickupItem() 
	{
		return this.getArrowType().getStack().get();
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_36772_) 
	{
		super.addAdditionalSaveData(p_36772_);
		p_36772_.putInt("ArrowType", this.getArrowType().ordinal());
		p_36772_.putInt("CurrentAmount", this.currentAmount);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_36761_) 
	{
		super.readAdditionalSaveData(p_36761_);
		this.setArrowType(SpecialArrowType.values()[p_36761_.getInt("ArrowType")]);
		this.currentAmount = p_36761_.getInt("CurrentAmount");
	}
	
	@Override
	protected Component getTypeName()
	{
		return Component.translatable("entity.villageandvillagers." + this.getArrowType().name);
	}
	
	public void setArrowType(SpecialArrowType type)
	{
		this.entityData.set(ARROW_TYPE, type.ordinal());
	}
	
	@Override
	public byte getPierceLevel()
	{
		if(this.getArrowType() == SpecialArrowType.PIERCING)
		{
			return (byte) this.getArrowType().maxAmount;
		}
		return super.getPierceLevel();
	}
	
	public SpecialArrowType getArrowType()
	{
		return SpecialArrowType.values()[this.entityData.get(ARROW_TYPE)];
	}
	
	public static enum SpecialArrowType
	{
		BOUNCY("bouncy_arrow", () -> VillageItems.BOUNCY_ARROW.get().getDefaultInstance(), 5, true),
		ENDER("ender_arrow", () -> VillageItems.ENDER_ARROW.get().getDefaultInstance(), 0, false),
		MULTI("multi_arrow", () -> VillageItems.MULTI_ARROW.get().getDefaultInstance(), 3, false),
		PIERCING("piercing_arrow", () -> VillageItems.PIERCING_ARROW.get().getDefaultInstance(), 5, true);
		
		private final String name;
		private final Supplier<ItemStack> stack;
		private final int maxAmount;
		private final boolean isInfinity;
		
		private SpecialArrowType(String name, Supplier<ItemStack> stack, int maxAmount, boolean isInfinity)
		{
			this.name = name;
			this.stack = stack;
			this.maxAmount = maxAmount;
			this.isInfinity = isInfinity;
		}
		
		public int getMaxAmount()
		{
			return this.maxAmount;
		}
		
		public Supplier<ItemStack> getStack() 
		{
			return this.stack;
		}
		
		public String getName() 
		{
			return this.name;
		}

		public boolean isInfinity()
		{
			return this.isInfinity;
		}
	}
}
