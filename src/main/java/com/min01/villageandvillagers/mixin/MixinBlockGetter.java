package com.min01.villageandvillagers.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.min01.villageandvillagers.entity.IClipPos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

@Mixin(BlockGetter.class)
public interface MixinBlockGetter extends BlockGetter
{
    @Override
    default BlockHitResult clip(ClipContext p_45548_)
    {
    	BlockHitResult hitResult = this.clipOriginal(p_45548_);
		Vec3 to = hitResult.getLocation();
		EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(Level.class.cast(this), null, p_45548_.getFrom(), to, new AABB(p_45548_.getFrom(), to), t -> t instanceof IClipPos);
		if(entityHit != null && entityHit.getEntity() instanceof IClipPos entity)
		{
			hitResult = new BlockHitResult(entity.getClipPos(), hitResult.getDirection(), hitResult.getBlockPos(), hitResult.isInside());
		}
    	return hitResult;
    }
    
    default BlockHitResult clipOriginal(ClipContext p_45548_) 
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
    	}, (p_151372_) ->
    	{
    		Vec3 vec3 = p_151372_.getFrom().subtract(p_151372_.getTo());
    		return BlockHitResult.miss(p_151372_.getTo(), Direction.getNearest(vec3.x, vec3.y, vec3.z), new BlockPos(p_151372_.getTo()));
    	});
    }
}
