package com.min01.villageandvillagers.util;

import java.lang.reflect.Method;
import java.util.UUID;
import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class VillagerUtil
{
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
