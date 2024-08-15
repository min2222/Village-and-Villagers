package com.min01.villageandvillagers.util;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class VillagerUtil
{
	public static Vec3 getGroundPos(BlockGetter pLevel, double pX, double startY, double pZ)
    {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos(pX, startY, pZ);
        do
        {
        	blockpos$mutable.move(Direction.DOWN);
        } 
        while((pLevel.getBlockState(blockpos$mutable).isAir() || pLevel.getBlockState(blockpos$mutable).getMaterial().isLiquid() || !pLevel.getBlockState(blockpos$mutable).isCollisionShapeFullBlock(pLevel, blockpos$mutable)) && blockpos$mutable.getY() > pLevel.getMinBuildHeight());

        BlockPos blockpos = blockpos$mutable;

        return Vec3.atCenterOf(blockpos);
    }
	
	public static Vec2 lookAt(Vec3 startPos, Vec3 pos)
	{
		Vec3 vec3 = startPos;
		double d0 = pos.x - vec3.x;
		double d1 = pos.y - vec3.y;
		double d2 = pos.z - vec3.z;
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		float xRot = Mth.wrapDegrees((float)(-(Mth.atan2(d1, d3) * (double)(180.0F / (float)Math.PI))));
		float yRot = Mth.wrapDegrees((float)(Mth.atan2(d2, d0) * (double)(180.0F / (float)Math.PI)) - 90.0F);
	    return new Vec2(xRot, yRot);
	}
	
	public static Vec3 fromToVector(Vec3 from, Vec3 to, float scale)
	{
		Vec3 motion = to.subtract(from).normalize();
		return motion.scale(scale);
	}
	
	public static float distanceTo(Vec3 pos1, Vec3 pos2)
	{
		float f = (float)(pos1.x - pos2.x);
		float f1 = (float)(pos1.y - pos2.y);
		float f2 = (float)(pos1.z - pos2.z);
		return Mth.sqrt(f * f + f1 * f1 + f2 * f2);
	}
	
	public static Vec3 getLookPos(Vec2 rotation, Vec3 position, double left, double up, double forwards) 
	{
		Vec2 vec2 = rotation;
		Vec3 vec3 = position;
		float f = Mth.cos((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f1 = Mth.sin((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f2 = Mth.cos(-vec2.x * ((float)Math.PI / 180.0F));
		float f3 = Mth.sin(-vec2.x * ((float)Math.PI / 180.0F));
		float f4 = Mth.cos((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		float f5 = Mth.sin((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		Vec3 vec31 = new Vec3((double)(f * f2), (double)f3, (double)(f1 * f2));
		Vec3 vec32 = new Vec3((double)(f * f4), (double)f5, (double)(f1 * f4));
		Vec3 vec33 = vec31.cross(vec32).scale(-1.0D);
		double d0 = vec31.x * forwards + vec32.x * up + vec33.x * left;
		double d1 = vec31.y * forwards + vec32.y * up + vec33.y * left;
		double d2 = vec31.z * forwards + vec32.z * up + vec33.z * left;
		return new Vec3(vec3.x + d0, vec3.y + d1, vec3.z + d2);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T getEntityByUUID(Level level, UUID uuid)
	{
		Method m = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) m.invoke(level);
			return (T) entities.get(uuid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void createLine(Level level, Vec3 targetPos, Vec3 pos, int maxDistance, Consumer<BlockPos> consumer)
	{
		double d0 = Math.min(targetPos.y, pos.y);
		double d1 = Math.max(targetPos.y, pos.y) + 1.0D;
		float f = (float)Mth.atan2(targetPos.z - pos.z, targetPos.x - pos.x);
		for(int l = 0; l < maxDistance; ++l)
		{
			double d2 = 1.25D * (double)(l + 1);
			int j = 1 * l;
			createLine(level, pos.x + (double)Mth.cos(f) * d2, pos.z + (double)Mth.sin(f) * d2, d0, d1, j, consumer);
		}
	}
	
	public static void createLine(Level level, double p_32673_, double p_32674_, double p_32675_, double p_32676_, int p_32678_, Consumer<BlockPos> consumer) 
	{
		BlockPos blockpos = new BlockPos(p_32673_, p_32676_, p_32674_);
		boolean flag = false;
		double d0 = 0.0D;

		do 
		{
			BlockPos blockpos1 = blockpos.below();
			BlockState blockstate = level.getBlockState(blockpos1);
			if(blockstate.isFaceSturdy(level, blockpos1, Direction.UP))
			{
				if(!level.isEmptyBlock(blockpos)) 
				{
					BlockState blockstate1 = level.getBlockState(blockpos);
					VoxelShape voxelshape = blockstate1.getCollisionShape(level, blockpos);
					if(!voxelshape.isEmpty())
					{
						d0 = voxelshape.max(Direction.Axis.Y);
					}
				}

				flag = true;
				break;
			}

			blockpos = blockpos.below();
		}
		while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

		if(flag)
		{
			consumer.accept(new BlockPos(p_32673_, (double)blockpos.getY() + d0, p_32674_));
		}
    }
}
