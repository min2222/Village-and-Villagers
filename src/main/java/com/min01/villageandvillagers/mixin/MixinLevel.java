package com.min01.villageandvillagers.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import com.min01.villageandvillagers.entity.IClipPos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

@Mixin(Level.class)
public abstract class MixinLevel implements LevelAccessor
{
	@Override
	public BlockHitResult clip(ClipContext ctx) 
	{
	    BlockHitResult hitResult = this.clipOriginal(ctx);
	    Vec3 from = ctx.getFrom();
	    Vec3 to = ctx.getTo();
	    AABB aabb = new AABB(from, to).inflate(1.0F);
	    List<Entity> entities = Level.class.cast(this).getEntitiesOfClass(Entity.class, aabb, t -> t instanceof IClipPos);
		for(Entity entity : entities)
		{
			if(entity instanceof IClipPos clip)
			{
				if(clip.shouldClip())
				{
					return new BlockHitResult(clip.getClipPos(from, to), hitResult.getDirection(), hitResult.getBlockPos(), hitResult.isInside());
				}
			}
		}
		return hitResult;
	}
	
	private BlockHitResult clipOriginal(ClipContext p_45548_)
	{
		return BlockGetter.traverseBlocks(p_45548_.getFrom(), p_45548_.getTo(), p_45548_, (p_151359_, p_151360_) -> 
		{
			BlockState blockstate = this.getBlockState(p_151360_);
			FluidState fluidstate = this.getFluidState(p_151360_);
			Vec3 vec3 = p_151359_.getFrom();
			Vec3 vec31 = p_151359_.getTo();
			VoxelShape voxelshape = p_151359_.getBlockShape(blockstate, this, p_151360_);
			BlockHitResult blockhitresult = this.clipWithInteractionOverride(vec3, vec31, p_151360_, voxelshape, blockstate);
			VoxelShape voxelshape1 = p_151359_.getFluidShape(fluidstate, this, p_151360_);
			BlockHitResult blockhitresult1 = voxelshape1.clip(vec3, vec31, p_151360_);
			double d0 = blockhitresult == null ? Double.MAX_VALUE : p_151359_.getFrom().distanceToSqr(blockhitresult.getLocation());
			double d1 = blockhitresult1 == null ? Double.MAX_VALUE : p_151359_.getFrom().distanceToSqr(blockhitresult1.getLocation());
			return d0 <= d1 ? blockhitresult : blockhitresult1;
		}, (p_275153_) ->
		{
			Vec3 vec3 = p_275153_.getFrom().subtract(p_275153_.getTo());
			return BlockHitResult.miss(p_275153_.getTo(), Direction.getNearest(vec3.x, vec3.y, vec3.z), BlockPos.containing(p_275153_.getTo()));
		});
	}
}
